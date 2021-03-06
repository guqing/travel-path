package xyz.guqing.travelpath.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.guqing.travelpath.model.dos.RoleDO;
import xyz.guqing.travelpath.model.entity.Role;

import java.util.Optional;

/**
 * @author guqing
 * @date 2020-06-03
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据id查询角色详情包括角色关联菜单
     * @param id 角色id
     * @return 返回角色详情
     */
    Optional<RoleDO> findById(Long id);
}
