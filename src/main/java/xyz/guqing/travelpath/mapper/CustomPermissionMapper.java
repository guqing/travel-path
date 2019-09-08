package xyz.guqing.travelpath.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.guqing.travelpath.entity.model.Permission;

import java.util.List;


@Mapper
public interface CustomPermissionMapper {

    /**
     * 根据角色id查询用户权限信息
     * @param roleId 角色id
     * @return 返回用户权限集合
     */
    @Select("SELECT p.* FROM role r LEFT JOIN role_permission rp on rp.role_id=r.id JOIN permission p on p.id=rp.permission_id WHERE r.id=#{roleId}")
    List<Permission> queryPermissionByRoleIds(@Param("roleId") Integer roleId);

    @Select("SELECT p.* FROM permission p where id=#{permissionId}")
    Permission selectById(Integer permissionId);
}
