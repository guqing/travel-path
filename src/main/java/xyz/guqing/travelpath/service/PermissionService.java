package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.Permission;
import xyz.guqing.travelpath.mapper.CustomPermissionMapper;

import java.util.List;

/**
 * 用户权限
 *
 * @author guqin
 * @date 2019-08-11 10:03
 */
@Service
public class PermissionService {
	@Autowired
	private CustomPermissionMapper customPermissionMapper;

	/**
	 * 根据角色Id查询用户权限信息
	 * @param roleId 角色Id
	 * @return 用户权限集合
	 */
	public List<Permission> listPermissionByRoleId(Integer roleId) {
		return customPermissionMapper.queryPermissionByRoleIds(roleId);
	}
}
