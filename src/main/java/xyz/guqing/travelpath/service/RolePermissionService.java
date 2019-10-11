package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.RolePermission;
import xyz.guqing.travelpath.entity.model.RolePermissionExample;
import xyz.guqing.travelpath.mapper.RolePermissionMapper;

import java.util.List;

/**
 * 角色相关的权限关联信息<br>
 *
 * @author guqing
 * @date 2019-10-11 19:19
 */
@Service
public class RolePermissionService {
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    public RolePermissionService(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    public void saveByBatch(Integer roleId, List<Long> permissionIds) {
        for (Long permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }
    }

    public void deleteByRoleId(Integer roleId) {
        RolePermissionExample example = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);

        rolePermissionMapper.deleteByExample(example);
    }
}
