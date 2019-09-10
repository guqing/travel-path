package xyz.guqing.travelpath.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.travelpath.entity.dto.PermissionDTO;
import xyz.guqing.travelpath.service.PermissionService;
import xyz.guqing.travelpath.utils.Result;

/**
 * 权限controller
 *
 * @author guqing
 * @date 2019-09-10 11:31
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
	private PermissionService permissionService;
	@Autowired
	public PermissionController(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@GetMapping("/list")
	public Object listPermission(@RequestParam(defaultValue = "1") Integer current,
								 @RequestParam(defaultValue = "10") Integer pageSize) {
		PageInfo<PermissionDTO> permissionsPageInfo = permissionService.listAllPermission(current, pageSize);
		return Result.okList(permissionsPageInfo);
	}
}
