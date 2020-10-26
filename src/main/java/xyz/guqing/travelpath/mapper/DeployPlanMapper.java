package xyz.guqing.travelpath.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.guqing.travelpath.model.dos.DeployPlanDO;
import xyz.guqing.travelpath.model.entity.DeployPlan;

/**
 * @author guqing
 * @date 2020-10-22
 */
public interface DeployPlanMapper extends BaseMapper<DeployPlan> {
    /**
     * 根据id查询方案详情
     * @param id 预设卡口方案id
     * @return 返回详情do
     */
    DeployPlanDO findDetailById(Long id);
}
