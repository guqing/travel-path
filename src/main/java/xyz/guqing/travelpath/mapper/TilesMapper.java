package xyz.guqing.travelpath.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.Tiles;
import xyz.guqing.travelpath.entity.model.TilesExample;

public interface TilesMapper {
    int countByExample(TilesExample example);

    int deleteByExample(TilesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tiles record);

    int insertSelective(Tiles record);

    List<Tiles> selectByExampleWithBLOBs(TilesExample example);

    List<Tiles> selectByExample(TilesExample example);

    Tiles selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Tiles record, @Param("example") TilesExample example);

    int updateByExampleWithBLOBs(@Param("record") Tiles record, @Param("example") TilesExample example);

    int updateByExample(@Param("record") Tiles record, @Param("example") TilesExample example);

    int updateByPrimaryKeySelective(Tiles record);

    int updateByPrimaryKeyWithBLOBs(Tiles record);

    int updateByPrimaryKey(Tiles record);
}