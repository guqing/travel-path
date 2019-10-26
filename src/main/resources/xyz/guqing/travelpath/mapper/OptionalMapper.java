package xyz.guqing.travelpath.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.entity.model.Optional;
import xyz.guqing.travelpath.entity.model.OptionalExample;

public interface OptionalMapper {
    long countByExample(OptionalExample example);

    int deleteByExample(OptionalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Optional record);

    int insertSelective(Optional record);

    List<Optional> selectByExample(OptionalExample example);

    Optional selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Optional record, @Param("example") OptionalExample example);

    int updateByExample(@Param("record") Optional record, @Param("example") OptionalExample example);

    int updateByPrimaryKeySelective(Optional record);

    int updateByPrimaryKey(Optional record);
}