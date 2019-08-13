package xyz.guqing.travelpath.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.entity.model.PresetpointExample;

public interface PresetpointMapper {
    int countByExample(PresetpointExample example);

    int deleteByExample(PresetpointExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Presetpoint record);

    int insertSelective(Presetpoint record);

    List<Presetpoint> selectByExample(PresetpointExample example);

    Presetpoint selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Presetpoint record, @Param("example") PresetpointExample example);

    int updateByExample(@Param("record") Presetpoint record, @Param("example") PresetpointExample example);

    int updateByPrimaryKeySelective(Presetpoint record);

    int updateByPrimaryKey(Presetpoint record);
}