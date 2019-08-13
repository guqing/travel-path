package xyz.guqing.travelpath.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.Steps;
import xyz.guqing.travelpath.entity.model.StepsExample;

public interface StepsMapper {
    int countByExample(StepsExample example);

    int deleteByExample(StepsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Steps record);

    int insertSelective(Steps record);

    List<Steps> selectByExampleWithBLOBs(StepsExample example);

    List<Steps> selectByExample(StepsExample example);

    Steps selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Steps record, @Param("example") StepsExample example);

    int updateByExampleWithBLOBs(@Param("record") Steps record, @Param("example") StepsExample example);

    int updateByExample(@Param("record") Steps record, @Param("example") StepsExample example);

    int updateByPrimaryKeySelective(Steps record);

    int updateByPrimaryKeyWithBLOBs(Steps record);

    int updateByPrimaryKey(Steps record);
}