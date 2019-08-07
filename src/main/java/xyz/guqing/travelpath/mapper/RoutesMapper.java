package xyz.guqing.travelpath.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.Routes;
import xyz.guqing.travelpath.entity.model.RoutesExample;

public interface RoutesMapper {
    int countByExample(RoutesExample example);

    int deleteByExample(RoutesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Routes record);

    int insertSelective(Routes record);

    List<Routes> selectByExampleWithBLOBs(RoutesExample example);

    List<Routes> selectByExample(RoutesExample example);

    Routes selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Routes record, @Param("example") RoutesExample example);

    int updateByExampleWithBLOBs(@Param("record") Routes record, @Param("example") RoutesExample example);

    int updateByExample(@Param("record") Routes record, @Param("example") RoutesExample example);

    int updateByPrimaryKeySelective(Routes record);

    int updateByPrimaryKeyWithBLOBs(Routes record);

    int updateByPrimaryKey(Routes record);
}