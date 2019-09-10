package xyz.guqing.travelpath.entity.dto;

/**
 * 权限DTO
 *
 * @author guqin
 * @date 2019-08-11 11:29
 */

import xyz.guqing.travelpath.entity.model.PermissionAction;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 'roleId': 'admin',
*       'permissionId': 'dashboard',
*       'permissionName': '仪表盘',
*       'actions': '[{"action":"add","defaultCheck":false,"describe":"新增"},{"action":"query","defaultCheck":false,"describe":"查询"},{"action":"get","defaultCheck":false,"describe":"详情"},{"action":"update","defaultCheck":false,"describe":"修改"},{"action":"delete","defaultCheck":false,"describe":"删除"}]',
*       'actionEntitySet': [{
*         'action': 'add',
*         'describe': '新增',
*         'defaultCheck': false
*       }, {
*         'action': 'query',
*         'describe': '查询',
*         'defaultCheck': false
*       }, {
 */
public class PermissionDTO {
	private Integer id;
	private String permissionId;
	private String permissionName;
	private String description;
	private Integer available;
	private String url;
	private String actions;
	private Set<PermissionAction> actionEntitySet;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public Set<PermissionAction> getActionEntitySet() {
		return actionEntitySet;
	}

	public void setActionEntitySet(Set<PermissionAction> actionEntitySet) {
		this.actionEntitySet = actionEntitySet;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}
}
