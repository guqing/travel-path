package xyz.guqing.travelpath.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.guqing.travelpath.model.dos.PresetPlanDO;
import xyz.guqing.travelpath.model.entity.PresetPlan;

/**
 * <p>
 *  预设卡口方案表 Mapper 接口
 * </p>
 * @author guqing
 * @date 2020-10-19
 */
public interface PresetPlanMapper extends BaseMapper<PresetPlan> {
    /**
     * 根据id查询方案详情
     * @param id 预设卡口方案id
     * @return 返回详情do
     */
    PresetPlanDO getDetailById(Long id);
}
