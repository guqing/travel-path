package xyz.guqing.travelpath.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.ActualBayonetPoint;
import xyz.guqing.travelpath.entity.model.ActualBayonetPointExample;

public interface ActualBayonetPointMapper {
    int countByExample(ActualBayonetPointExample example);

    int deleteByExample(ActualBayonetPointExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActualBayonetPoint record);

    int insertSelective(ActualBayonetPoint record);

    List<ActualBayonetPoint> selectByExample(ActualBayonetPointExample example);

    ActualBayonetPoint selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActualBayonetPoint record, @Param("example") ActualBayonetPointExample example);

    int updateByExample(@Param("record") ActualBayonetPoint record, @Param("example") ActualBayonetPointExample example);

    int updateByPrimaryKeySelective(ActualBayonetPoint record);

    int updateByPrimaryKey(ActualBayonetPoint record);
}