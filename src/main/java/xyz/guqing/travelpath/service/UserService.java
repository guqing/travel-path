package xyz.guqing.travelpath.service;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PermissionActionService actionService;

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
	public UserDTO getUserInfo(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Role role = roleService.getRoleById(user.getRoleId());
        System.out.println(role);
        List<Permission> permissions = permissionService.listPermissionByRoleId(role.getId());
        return userDtoConverter(user, role, permissions);
    }

    private UserDTO userDtoConverter(User user, Role role, List<Permission> permissions) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getNickname());
        userDTO.setUsername(user.getUsername());
        userDTO.setGender(user.getGender().intValue());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setStatus(user.getStatus().intValue());
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
