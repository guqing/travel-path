package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.PermissionAction;
import xyz.guqing.travelpath.mapper.CustomPermissionActionMapper;

import java.util.List;
import java.util.Set;

/**
 * 用户权限的ActionService
 *
 * @author guqin
 * @date 2019-08-11 14:28
 */
@Service
@CacheConfig(cacheNames = "permissionActionService")
public class PermissionActionService {
	private CustomPermissionActionMapper actionMapper;
	@Autowired
	public PermissionActionService(CustomPermissionActionMapper actionMapper) {
		this.actionMapper = actionMapper;
	}

	@Cacheable
	public Set<PermissionAction> listActionByRoleId(Integer roleId) {
		return actionMapper.listActionByRoleId(roleId);
	}

}
