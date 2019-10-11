package xyz.guqing.travelpath.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.dto.PermissionDTO;
import xyz.guqing.travelpath.entity.dto.RoleDTO;
import xyz.guqing.travelpath.entity.model.Role;
import xyz.guqing.travelpath.mapper.RoleMapper;

import java.util.ArrayList;
import java.util.Date;
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
	private PermissionService permissionService;
	private RolePermissionService rolePermissionService;

	@Autowired
	public RoleService(RoleMapper roleMapper,
					   PermissionService permissionService,
					   RolePermissionService rolePermissionService) {
		this.roleMapper = roleMapper;
		this.permissionService = permissionService;
		this.rolePermissionService = rolePermissionService;
	}

	public void save(RoleDTO role) {
		// 保存角色信息
		Role record = new Role();
		BeanUtils.copyProperties(role, record);
		record.setCreateTime(new Date());
		record.setModifyTime(new Date());
		roleMapper.insertSelective(record);

		// 保存角色相关的权限
		rolePermissionService.saveByBatch(record.getId(), role.getPermissionIds());
	}

	public void update(RoleDTO role) {
		// 更新角色信息
		Role record = new Role();
		BeanUtils.copyProperties(role, record);
		record.setCreateTime(null);
		record.setModifyTime(new Date());
		roleMapper.updateByPrimaryKeySelective(record);

		// 删除角色相关权限信息
		rolePermissionService.deleteByRoleId(role.getId());

		// 保存角色相关权限信息
		rolePermissionService.saveByBatch(record.getId(), role.getPermissionIds());
	}

	@Cacheable
	public Role getRoleById(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	public PageInfo<RoleDTO> listRole(Integer current, Integer pageSize) {
		PageHelper.startPage(current, pageSize);
		List<Role> roles = roleMapper.selectByExample(null);

		// 根绝角色查询权限
		List<RoleDTO> roleList = new ArrayList<>();
		for (Role role : roles) {
			List<PermissionDTO> permissions = permissionService.listPermissionByRoleId(role.getId());

			RoleDTO roleDTO = new RoleDTO();
			BeanUtils.copyProperties(role, roleDTO);
			roleDTO.setPermissions(permissions);
			roleList.add(roleDTO);
		}

		return new PageInfo<>(roleList);
	}

	public void delete(Integer id) {
		// 删除角色信息
		roleMapper.deleteByPrimaryKey(id);
		// 删除角色权限关联信息
		rolePermissionService.deleteByRoleId(id);
	}
}
