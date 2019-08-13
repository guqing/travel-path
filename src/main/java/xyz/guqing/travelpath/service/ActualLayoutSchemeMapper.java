package xyz.guqing.travelpath.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.ActualLayoutScheme;
import xyz.guqing.travelpath.entity.model.ActualLayoutSchemeExample;

public interface ActualLayoutSchemeMapper {
    int countByExample(ActualLayoutSchemeExample example);

    int deleteByExample(ActualLayoutSchemeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActualLayoutScheme record);

    int insertSelective(ActualLayoutScheme record);

    List<ActualLayoutScheme> selectByExample(ActualLayoutSchemeExample example);

    ActualLayoutScheme selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActualLayoutScheme record, @Param("example") ActualLayoutSchemeExample example);

    int updateByExample(@Param("record") ActualLayoutScheme record, @Param("example") ActualLayoutSchemeExample example);

    int updateByPrimaryKeySelective(ActualLayoutScheme record);

    int updateByPrimaryKey(ActualLayoutScheme record);
}