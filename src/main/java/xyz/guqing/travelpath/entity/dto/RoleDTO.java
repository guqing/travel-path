package xyz.guqing.travelpath.entity.dto;

import xyz.guqing.travelpath.entity.model.Permission;

import java.util.Date;
import java.util.List;

/**
 * 角色DTO
 *
 * @author guqin
 * @date 2019-08-11 11:21
 */
public class RoleDTO {
	private Integer id;

	private String description;

	private String name;

	private String available;

	private Date createTime;

	private Date modifyTime;

	private List<PermissionDTO> permissions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<PermissionDTO> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionDTO> permissions) {
		this.permissions = permissions;
	}
}
