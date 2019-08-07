package xyz.guqing.travelpath.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.RouteBayonetScheme;
import xyz.guqing.travelpath.entity.model.RouteBayonetSchemeExample;

public interface RouteBayonetSchemeMapper {
    int countByExample(RouteBayonetSchemeExample example);

    int deleteByExample(RouteBayonetSchemeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RouteBayonetScheme record);

    int insertSelective(RouteBayonetScheme record);

    List<RouteBayonetScheme> selectByExample(RouteBayonetSchemeExample example);

    RouteBayonetScheme selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RouteBayonetScheme record, @Param("example") RouteBayonetSchemeExample example);

    int updateByExample(@Param("record") RouteBayonetScheme record, @Param("example") RouteBayonetSchemeExample example);

    int updateByPrimaryKeySelective(RouteBayonetScheme record);

    int updateByPrimaryKey(RouteBayonetScheme record);
}