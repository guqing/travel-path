package xyz.guqing.travelpath.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.guqing.travelpath.entity.model.PermissionAction;

import java.util.Set;

/**
 * 权限具体功能url
 *
 * @author guqin
 * @date 2019-08-11 14:24
 */
@Mapper
public interface CustomPermissionActionMapper {

	@Select("select * from permission_action where p_id = #{permissionId}")
	public Set<PermissionAction> listActionByPid(Integer permissionId);

	@Select("SELECT p.* FROM role r LEFT JOIN role_permission rp on rp.role_id=r.id JOIN permission_action p on p.id=rp.permission_id WHERE r.id=#{roleId}")
	public Set<PermissionAction> listActionByRoleId(Integer roleId);
}
