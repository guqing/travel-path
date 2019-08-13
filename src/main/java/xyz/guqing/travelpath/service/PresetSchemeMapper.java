package xyz.guqing.travelpath.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.model.PresetSchemeExample;

public interface PresetSchemeMapper {
    int countByExample(PresetSchemeExample example);

    int deleteByExample(PresetSchemeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PresetScheme record);

    int insertSelective(PresetScheme record);

    List<PresetScheme> selectByExample(PresetSchemeExample example);

    PresetScheme selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PresetScheme record, @Param("example") PresetSchemeExample example);

    int updateByExample(@Param("record") PresetScheme record, @Param("example") PresetSchemeExample example);

    int updateByPrimaryKeySelective(PresetScheme record);

    int updateByPrimaryKey(PresetScheme record);
}