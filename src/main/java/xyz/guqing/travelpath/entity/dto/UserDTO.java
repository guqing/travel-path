package xyz.guqing.travelpath.entity.dto;

import lombok.Data;
import xyz.guqing.travelpath.entity.model.Role;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息dto
 *
 * @author guqin
 * @date 2019-08-11 9:29
 */
@Data
public class UserDTO implements Serializable {
	private Integer id;
	private String name;
	private String username;
	private String description;
	private Date birthday;
	private String mobile;
	private String email;
	private String avatar;
	private Integer status;
	private Integer gender;
	private String telephone;
	private String lastLoginIp;
	private Date lastLoginTime;
	private Date createTime;
	private String roleId;
	private RoleDTO role;
}
