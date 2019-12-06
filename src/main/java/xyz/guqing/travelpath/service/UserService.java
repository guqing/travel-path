package xyz.guqing.travelpath.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import xyz.guqing.travelpath.entity.dto.PermissionDTO;
import xyz.guqing.travelpath.entity.dto.RoleDTO;
import xyz.guqing.travelpath.entity.dto.UserDTO;
import xyz.guqing.travelpath.entity.model.*;
import xyz.guqing.travelpath.entity.params.RegisterParam;
import xyz.guqing.travelpath.entity.params.UserParam;
import xyz.guqing.travelpath.entity.params.UserRoleParam;
import xyz.guqing.travelpath.entity.support.DeleteConstant;
import xyz.guqing.travelpath.entity.support.UserStatusConstant;
import xyz.guqing.travelpath.exception.UserServiceException;
import xyz.guqing.travelpath.mapper.UserMapper;

import java.util.*;
import java.util.Optional;

/**
 * @author guqing
 * @date 2019/8/9
 */
@Service
@CacheConfig(cacheNames = "userService")
public class UserService {
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final MailService mailService;

    @Autowired
    public UserService(UserMapper userMapper,
                       RoleService roleService,
                       PermissionService permissionService,
                       MailService mailService) {
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.mailService = mailService;
    }

    @Cacheable(key = "#username", unless = "#result==null")
    public User getUserByUsername(String username, Integer loginType){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andDeletedEqualTo(UserStatusConstant.NORMAL);
        criteria.andStatusEqualTo(UserStatusConstant.NORMAL);
        if(loginType == 0) {
            criteria.andEmailEqualTo(username);
        } else {
            criteria.andUsernameEqualTo(username);
        }
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)){
            return null;
        }
        return userList.get(0);
    }

    /**
     * 获取用户信息
     * @param userId 用户id
     * @return 用户信息DTO对象
     */
    @Cacheable(unless = "#result==null")
	public UserDTO getUserInfo(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Role role = roleService.getRoleById(user.getRoleId());
        List<PermissionDTO> permissionDtoList = permissionService.listPermissionByRoleId(role.getId());
        return userDtoConverter(user, role, permissionDtoList);
    }

    /**
     * 更新用户的最后登录时间
     * @param userId 用户id
     * @param ip 用户登录ip
     */
    public void updateLoginTime(Integer userId, String ip) {
        User user = new User();
        user.setId(userId);
        user.setLastLoginTime(new Date());
        user.setLastLoginIp(ip);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 查询用户列表
     * @return 返回用户DTO列表
     */
    public PageInfo<UserDTO> listUser(Integer current, Integer pageSize) {
        PageHelper.startPage(current, pageSize);
        List<UserDTO> userList = new ArrayList<> ();

        List<User> users = userMapper.selectByExample(null);
        users.forEach(user -> {
            // 查询角色,权限
            Role role = roleService.getRoleById(user.getRoleId());
            List<PermissionDTO> permissions = permissionService.listPermissionByRoleId(role.getId());
            UserDTO userDTO = userDtoConverter(user, role, permissions);
            userList.add(userDTO);
        });

        return new PageInfo<>(userList);
    }

    /**
     * 根据用户id查询用户的基本信息
     * @param userId 用户id
     * @return 返回用户基本信息不包含权限和角色
     */
    public UserDTO getBaseUserInfo(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setName(user.getNickname());
        userDTO.setTelephone(user.getMobile());
        userDTO.setLastLoginIp(null);
        return userDTO;
    }

    /**
     * 更新用户信息,需要根据id删除该key的缓存
     * @param userParam 用户信息参数
     */
    @CacheEvict(key = "#userParam.id")
    @Transactional(rollbackFor = UserServiceException.class)
    public void updateUserInfo(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setMobile(userParam.getTelephone());
        user.setNickname(userParam.getName());
        user.setModifyTime(new Date());

        userMapper.updateByPrimaryKeySelective(user);
    }


    private UserDTO userDtoConverter(User user, Role role, List<PermissionDTO> permissions) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setName(user.getNickname());
        userDTO.setTelephone(user.getMobile());
        userDTO.setRoleId(role.getName());

        //设置角色和权限
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        roleDTO.setPermissions(permissions);
        userDTO.setRole(roleDTO);
        return userDTO;
    }

    /**
     * 根据id和密码查询用户，存在返回true，否则返回false
     * @param id 用户id
     * @param oldPassword 用户的密码
     * @return 存在返回true，否则返回false
     */
    public boolean isExistsWithIdAndPassword(Integer id, String oldPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userMapper.selectByPrimaryKey(id);
        Optional<String> password = Optional.ofNullable(user).map(User::getPassword);
        Assert.isTrue(password.isPresent(), "用户不存在");

        return bCryptPasswordEncoder.matches(oldPassword, password.get());
    }

    /**
     * 修改密码
     * @param id 用户id
     * @param newPassword 新密码
     */
    @CacheEvict(key = "#id")
    public void updatePassword(Integer id, String newPassword) {
        User user = new User();
        user.setId(id);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(encodePassword);
        userMapper.updateByPrimaryKeySelective(user);
    }

    public boolean checkUserExistsByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return !users.isEmpty();
    }

    /**
     * 用户注册:
     * 1.保存用户到数据库
     * 2.发送激活邮件
     * @param model 用户信息
     * @param serverPath 服务器基地址,发送邮件中的激活链接需要
     */
    @Transactional(rollbackFor = UserServiceException.class)
    public void register(RegisterParam model, String serverPath) {
        User user = new User();
        // 补全用户信息
        BeanUtils.copyProperties(model, user);
        user.setModifyTime(new Date());
        user.setCreateTime(new Date());
        user.setDeleted(UserStatusConstant.NORMAL);
        user.setStatus(UserStatusConstant.UNACTIVE);
        // 设置默认角色和id，默认未普通用户
        Role role = roleService.findDefaultRole();
        user.setRoleId(role.getId());
        user.setRoleName(role.getName());

        // 对密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode(model.getPassword());
        user.setPassword(encodePassword);

        // 保存到数据库
        userMapper.insertSelective(user);

        Integer id = user.getId();

        // 构建发送邮件所需的数据对象
        Map<String, Object> userDTO = new HashMap<>();
        userDTO.put("id", id);
        userDTO.put("email", user.getEmail());
        userDTO.put("username", user.getUsername());
        String activateUrl = serverPath + "user/activate?username=" + user.getUsername();
        userDTO.put("activateUrl", activateUrl);
        userDTO.put("createTime", new Date());
        // 发送激活邮件
        mailService.sendRegisterMail(userDTO);
    }

    /**
     * 根据用户名激活账户
     * @param username 用户名
     */
    public void activateAccount(String username) {
        // 修改用户账号状态
        User user = new User();
        user.setStatus(UserStatusConstant.NORMAL);

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);

        userMapper.updateByExampleSelective(user, example);
    }

    public void updateUserRole(UserRoleParam userRoleParam) {
        User user = new User();
        user.setId(userRoleParam.getId());
        user.setRoleId(userRoleParam.getRoleId());
        user.setRoleName(user.getRoleName());
        user.setStatus(userRoleParam.getStatus());
        userMapper.updateByPrimaryKeySelective(user);
    }
}
