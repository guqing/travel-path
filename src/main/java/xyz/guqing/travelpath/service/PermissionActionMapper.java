package xyz.guqing.travelpath.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.PermissionAction;
import xyz.guqing.travelpath.entity.model.PermissionActionExample;

public interface PermissionActionMapper {
    int countByExample(PermissionActionExample example);

    int deleteByExample(PermissionActionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionAction record);

    int insertSelective(PermissionAction record);

    List<PermissionAction> selectByExample(PermissionActionExample example);

    PermissionAction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermissionAction record, @Param("example") PermissionActionExample example);

    int updateByExample(@Param("record") PermissionAction record, @Param("example") PermissionActionExample example);

    int updateByPrimaryKeySelective(PermissionAction record);

    int updateByPrimaryKey(PermissionAction record);
}