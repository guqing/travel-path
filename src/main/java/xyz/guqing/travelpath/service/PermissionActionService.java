package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.PermissionAction;
import xyz.guqing.travelpath.mapper.CustomPermissionActionMapper;

import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author guqin
 * @date 2019-08-11 14:28
 */
@Service
public class PermissionActionService {
	@Autowired
	private CustomPermissionActionMapper actionMapper;

	public Set<PermissionAction> listPermissionAction(Integer permissionId) {
		return actionMapper.listActionByPid(permissionId);
	}
}
