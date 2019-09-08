package xyz.guqing.travelpath.service;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.dto.PermissionDTO;
import xyz.guqing.travelpath.entity.model.Permission;
import xyz.guqing.travelpath.entity.model.PermissionAction;
import xyz.guqing.travelpath.mapper.CustomPermissionMapper;

import java.util.*;

/**
 * 用户权限
 *
 * @author guqin
 * @date 2019-08-11 10:03
 */
@Service
@CacheConfig(cacheNames = "permissionService")
public class PermissionService {
	private CustomPermissionMapper customPermissionMapper;
	private PermissionActionService actionService;
	@Autowired
	public PermissionService(CustomPermissionMapper customPermissionMapper,
							 PermissionActionService actionService) {
		this.customPermissionMapper = customPermissionMapper;
		this.actionService = actionService;
	}

	/**
	 * 根据角色Id查询用户权限信息
	 * @param roleId 角色Id
	 * @return 用户权限集合
	 */
	public List<PermissionDTO> listPermissionByRoleId(Integer roleId) {
		// 根据角色查询权限的action
		Set<PermissionAction> permissionActions = actionService.listActionByRoleId(roleId);
		// 使用权限的action以权限id为键，权限的action集合为值构建一个map，目的是查询permission对象
		Map<Integer, Set<PermissionAction>> permissionActionMap = getPermissionActionMap(permissionActions);
		// 使用permissionActionMap逆向构建权限对象
		return getPermissionDtoList(permissionActionMap);
	}

	public Set<PermissionAction> listActionsByRoleId(Integer roleId) {
		// 根据角色查询权限的action
		return actionService.listActionByRoleId(roleId);
	}

	/**
	 * 根据permission和actions对象构建一个PermissionDTO对象
	 * @param permission 用户权限
	 * @param actions 权限的具体url等信息的action对象
	 * @return 返回权限的DTO对象
	 */
	private PermissionDTO getPermissionDTO(Permission permission, Set<PermissionAction> actions) {
		if(permission == null) {
			return null;
		}
		PermissionDTO permissionDTO = new PermissionDTO();
		permissionDTO.setId(permission.getId());
		permissionDTO.setPermissionId(permission.getPermissionId());
		permissionDTO.setDescription(permission.getDescription());
		permissionDTO.setPermissionName(permission.getPermissionName());
		//设置action
		permissionDTO.setActions(JSONArray.toJSONString(actions));
		permissionDTO.setActionEntitySet(actions);
		return permissionDTO;
	}

	/**
	 * 根据权限的id对权限的action进行分组，键是权限id,值为权限的action集合
	 * @param permissionActions 根据角色id查询出的权限action集合
	 * @return 返回以权限id为键，权限的action集合为值的map
	 */
	private Map<Integer, Set<PermissionAction>> getPermissionActionMap(Set<PermissionAction> permissionActions) {
		Map<Integer, Set<PermissionAction>> permissionActionMap = new HashMap<>(16);
		permissionActions.forEach(action -> {
			Set<PermissionAction> permissionAction = permissionActionMap.get(action.getpId());
			if(permissionAction != null) {
				permissionAction.add(action);
			} else {
				Set<PermissionAction> actionSet = new HashSet<>();
				actionSet.add(action);
				// 以pid做键放到Map中
				permissionActionMap.put(action.getpId(), actionSet);
			}
		});
		return permissionActionMap;
	}

	/**
	 * 使用存储着以权限id作键，权限action集合做值的map构建一个PermissionDTO集合
	 * 主要目的是实现基于url的细粒度权限控制，所需首先查询出的是权限url集合但是返回
	 * 的是根据菜单功能分组的权限list集合，所以需要进行一些转换以实现此功能
	 * - 菜单功能名称permission
	 * ---该菜单下包含的所有url及其他信息即Set<actions>
	 * @param permissionActionMap 键是权限id,值为权限action集合的map
	 * @return 返回以菜单分组的权限集合
	 */
	private List<PermissionDTO> getPermissionDtoList(Map<Integer, Set<PermissionAction>> permissionActionMap) {
		// 遍历map
		List<PermissionDTO> permissions = new ArrayList<>();
		for (Map.Entry<Integer, Set<PermissionAction>> entry : permissionActionMap.entrySet()) {
			// 查询permission
			Integer permissionId = entry.getKey();
			Set<PermissionAction> actions = entry.getValue();
			Permission permission = customPermissionMapper.selectById(permissionId);
			PermissionDTO permissionDTO = getPermissionDTO(permission, actions);
			if(permissionDTO != null) {
				permissions.add(permissionDTO);
			}
		}
		return permissions;
	}
}
