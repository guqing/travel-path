package xyz.guqing.travelpath.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.RouteBayonetPoint;
import xyz.guqing.travelpath.entity.model.RouteBayonetPointExample;

public interface RouteBayonetPointMapper {
    int countByExample(RouteBayonetPointExample example);

    int deleteByExample(RouteBayonetPointExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RouteBayonetPoint record);

    int insertSelective(RouteBayonetPoint record);

    List<RouteBayonetPoint> selectByExample(RouteBayonetPointExample example);

    RouteBayonetPoint selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RouteBayonetPoint record, @Param("example") RouteBayonetPointExample example);

    int updateByExample(@Param("record") RouteBayonetPoint record, @Param("example") RouteBayonetPointExample example);

    int updateByPrimaryKeySelective(RouteBayonetPoint record);

    int updateByPrimaryKey(RouteBayonetPoint record);
}