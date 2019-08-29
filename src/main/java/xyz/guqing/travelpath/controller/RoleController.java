package xyz.guqing.travelpath.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.travelpath.entity.model.Role;
import xyz.guqing.travelpath.service.RoleService;
import xyz.guqing.travelpath.utils.Result;

import java.util.List;

/**
 * 角色管理controller
 *
 * @author guqin
 * @date 2019-08-29 23:08
 */
@RestController
@RequestMapping("/role")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	private RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("/list")
	public Object listRole() {
		try {
			List<Role> roles = roleService.listRole();
			return Result.okList(roles);
		} catch (Exception e) {
			logger.error("查询角色列表失败，错误信息：{}",e.getMessage());
			return Result.fail();
		}
	}
}
