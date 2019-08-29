package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.Role;
import xyz.guqing.travelpath.mapper.RoleMapper;

import java.util.List;

/**
 * 用户角色
 *
 * @author guqin
 * @date 2019-08-11 9:44
 */
@Service
@CacheConfig(cacheNames = "roleService")
public class RoleService {
	private RoleMapper roleMapper;
	@Autowired
	public RoleService(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	@Cacheable
	public Role getRoleById(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}
	
	public List<Role> listRole() {
		return roleMapper.selectByExample(null);
	}
}
