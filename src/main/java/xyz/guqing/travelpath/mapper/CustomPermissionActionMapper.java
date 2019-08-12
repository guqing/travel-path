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
}
