package xyz.guqing.travelpath.service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.guqing.travelpath.entity.dto.PermissionDTO;
import xyz.guqing.travelpath.entity.dto.RoleDTO;
import xyz.guqing.travelpath.entity.dto.UserDTO;
import xyz.guqing.travelpath.entity.model.*;
import xyz.guqing.travelpath.mapper.UserMapper;

import java.util.*;

/**
 * @author guqing
 * @date 2019/8/9
 */
@Service
@CacheConfig(cacheNames = "userService")
public class UserService {
    private UserMapper userMapper;
    private RoleService roleService;
    private PermissionService permissionService;
    private PermissionActionService actionService;

    @Autowired
    public UserService(UserMapper userMapper,
                       RoleService roleService,
                       PermissionService permissionService,
                       PermissionActionService actionService) {
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.actionService = actionService;
    }

    @Cacheable
    public User getUserByUsername(String username){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
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
    @Cacheable
	public UserDTO getUserInfo(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Role role = roleService.getRoleById(user.getRoleId());
        List<Permission> permissions = permissionService.listPermissionByRoleId(role.getId());
        return userDtoConverter(user, role, permissions);
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
            List<Permission> permissions = permissionService.listPermissionByRoleId(role.getId());
            UserDTO userDTO = userDtoConverter(user, role, permissions);
            userList.add(userDTO);
        });

        return new PageInfo<>(userList);
    }

    private UserDTO userDtoConverter(User user, Role role, List<Permission> permissions) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getNickname());
        userDTO.setUsername(user.getUsername());
        userDTO.setGender(user.getGender());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setStatus(user.getStatus());
        userDTO.setLastLoginIp(user.getLastLoginIp());
        userDTO.setLastLoginTime(user.getLastLoginTime());
        userDTO.setTelephone(user.getMobile());
        userDTO.setCreateTime(user.getModifyTime());
        userDTO.setRoleId(role.getName());

        //设置角色和权限
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        roleDTO.setPermissions(permissionDtoConverter(permissions));
        userDTO.setRole(roleDTO);
        return userDTO;
    }

    private List<PermissionDTO> permissionDtoConverter(List<Permission> permissions) {
	    List<PermissionDTO> permissionDtoList = new ArrayList<>();
        permissions.forEach(permission -> {
            Set<PermissionAction> permissionActions = actionService.listPermissionAction(permission.getId());
            PermissionDTO permissionDTO = new PermissionDTO();
            permissionDTO.setId(permission.getId());
            permissionDTO.setPermissionId(permission.getPermissionId());
            permissionDTO.setDescription(permission.getDescription());
            permissionDTO.setPermissionName(permission.getPermissionName());
            //设置action
            permissionDTO.setActions(JSONArray.toJSONString(permissionActions));
            permissionDTO.setActionEntitySet(permissionActions);
            //添加到集合中
            permissionDtoList.add(permissionDTO);
        });

        return permissionDtoList;
    }
}
