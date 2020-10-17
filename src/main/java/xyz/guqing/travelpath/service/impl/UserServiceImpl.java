package xyz.guqing.travelpath.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.travelpath.exception.BadArgumentException;
import xyz.guqing.travelpath.exception.NotFoundException;
import xyz.guqing.travelpath.mapper.UserMapper;
import xyz.guqing.travelpath.mapper.UserRoleMapper;
import xyz.guqing.travelpath.model.bo.CurrentUser;
import xyz.guqing.travelpath.model.constant.CreekConstant;
import xyz.guqing.travelpath.model.dos.UserDO;
import xyz.guqing.travelpath.model.dto.UserDTO;
import xyz.guqing.travelpath.model.dto.UserInfoDTO;
import xyz.guqing.travelpath.model.entity.User;
import xyz.guqing.travelpath.model.entity.UserRole;
import xyz.guqing.travelpath.model.enums.GenderEnum;
import xyz.guqing.travelpath.model.enums.UserStatusEnum;
import xyz.guqing.travelpath.model.params.UserParam;
import xyz.guqing.travelpath.model.params.UserQuery;
import xyz.guqing.travelpath.model.support.PageInfo;
import xyz.guqing.travelpath.model.support.PageQuery;
import xyz.guqing.travelpath.service.MenuService;
import xyz.guqing.travelpath.service.RoleService;
import xyz.guqing.travelpath.service.UserService;
import xyz.guqing.travelpath.utils.PageUtils;
import xyz.guqing.travelpath.utils.ServiceUtils;
import xyz.guqing.travelpath.utils.TravelPathUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author guqing
 * @since 2020-05-21
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final RoleService roleService;
    private final MenuService menuService;
    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PageInfo<UserDTO> listByPage(UserQuery userQuery, PageQuery pageQuery) {
        // 查询
        Page<UserDO> userPage = this.baseMapper.findUserBy(PageUtils.convert(pageQuery), userQuery);
        List<UserDTO> userDtoList = ServiceUtils.convertToList(userPage.getRecords(), user -> {
            UserDTO userDTO = new UserDTO().convertFrom(user);
            userDTO.setRoleIds(TravelPathUtils.commaSeparatedToList(user.getRoleId()));
            userDTO.setRoleNames(TravelPathUtils.commaSeparatedToList(user.getRoleName()));
            return userDTO;
        });
        return PageInfo.convertFrom(userPage, userDtoList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(UserParam userParam) {
        User user = userParam.convertTo();
        user.setNickname(user.getUsername());
        user.setStatus(UserStatusEnum.NORMAL.getValue());
        user.setGender(GenderEnum.MALE.name());

        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //保存用户信息
        save(user);

        // 保存用户角色
        roleService.saveUserRoles(user.getId(), userParam.getRoleIds());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserParam userParam) {
        User user = userParam.convertTo();
        // 加密密码
        String password = user.getPassword();
        if(password != null) {
            user.setPassword(passwordEncoder.encode(password));
        }

        this.updateById(user);

        // 更新用户角色,先删除在插入
        LambdaQueryWrapper<UserRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserRole::getUserId, user.getId());
        userRoleMapper.delete(queryWrapper);
        List<Long> roleIds = userParam.getRoleIds();
        // 插入
        roleIds.forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleMapper.insert(userRole);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAvatar(String username, String avatar) {
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate(User.class);
        updateWrapper.set(User::getAvatar, avatar)
                .eq(User::getUsername, username);
        update(updateWrapper);
    }

    @Override
    public boolean isPresentByUsername(String username) {
        // 不为空即存在返回true
        return Objects.nonNull(getByUsername(username));
    }

    @Override
    public boolean isPresentByEmail(String email) {
        Optional<User> userOptional = getByEmail(email);
        return userOptional.isPresent();
    }

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getUsername, username);
        return getOne(queryWrapper);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getEmail, email);
        return Optional.ofNullable(getOne(queryWrapper));
    }

    @Override
    public UserInfoDTO getUserInfo(String username) {
        CurrentUser currentUser = loadUserByUsername(username);
        UserInfoDTO userInfoDTO = convertTo(currentUser);
        String permissions = menuService.findUserPermissions(username);
        List<String> permissionList = TravelPathUtils.commaSeparatedToList(permissions);
        userInfoDTO.setPermissions(permissionList);
        return userInfoDTO;
    }

    @Override
    public void updateLastLoginTime(String username, LocalDateTime loginTime) {
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.set(User::getLastLoginTime, loginTime).eq(User::getUsername, username);
        update(updateWrapper);
    }

    private UserInfoDTO convertTo(CurrentUser currentUser) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(currentUser,userInfoDTO);
        userInfoDTO.setRoleIds(TravelPathUtils.commaSeparatedToList(currentUser.getRoleId()));
        userInfoDTO.setRoleNames(TravelPathUtils.commaSeparatedToList(currentUser.getRoleName()));
        return userInfoDTO;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) {
        Optional<CurrentUser> userOptional = baseMapper.findByUsername(username);
        return userOptional.orElseThrow(() -> new NotFoundException("用户不存在"));
    }

    @Override
    public boolean isCorrectByPassword(String username, String password) {
        User user = getByUsername(username);
        // 直接匹配，无需先加密
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(String username) {
        User user = getByUsername(username);
        if(user == null) {
            throw new NotFoundException("用户不存在");
        }
        String defaultPassword = passwordEncoder.encode(CreekConstant.DEFAULT_PASSWORD);
        user.setPassword(defaultPassword);

        updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(String username, UserStatusEnum status) {
        User user = getByUsername(username);
        if(user == null) {
            throw new NotFoundException("用户不存在");
        }
        user.setStatus(status.getValue());
        updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByUserNames(List<String> usernames) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(User::getUsername, usernames);
        remove(queryWrapper);
    }

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        boolean correctByPassword = isCorrectByPassword(username, oldPassword);
        if(!correctByPassword) {
            throw new BadArgumentException("原始密码不正确");
        }
        // 修改密码
        String encodedPassword = passwordEncoder.encode(newPassword);
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.set(User::getPassword, encodedPassword)
                .eq(User::getUsername, username);
        update(updateWrapper);
    }
}
