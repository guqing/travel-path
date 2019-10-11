package xyz.guqing.travelpath.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.entity.dto.RoleDTO;
import xyz.guqing.travelpath.entity.model.Role;
import xyz.guqing.travelpath.service.RoleService;
import xyz.guqing.travelpath.utils.Result;

import java.util.List;

/**
 * 角色管理controller
 *
 * @author guqing
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
	public Object listRole(@RequestParam(defaultValue = "1") Integer current,
						   @RequestParam(defaultValue = "10") Integer pageSize) {
		PageInfo<RoleDTO> roles = roleService.listRole(current, pageSize);
		return Result.okList(roles);
	}

	@PostMapping("/save")
	public Object save(@RequestBody RoleDTO role) {
		roleService.save(role);
		return Result.ok();
	}

	@PutMapping("/update")
	public Object update(@RequestBody RoleDTO role) {
		if(role.getId() == null) {
			return Result.badArgument();
		}

		roleService.update(role);
		return Result.ok();
	}

	@PostMapping("/delete/{id}")
	public Object delete(@PathVariable("id") Integer id) {
		roleService.delete(id);
		return Result.ok();
	}
}
