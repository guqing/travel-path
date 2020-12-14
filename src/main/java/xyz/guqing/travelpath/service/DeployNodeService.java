package xyz.guqing.travelpath.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.guqing.travelpath.model.entity.DeployPlanNode;

import java.util.List;

/**
 * @author guqing
 * @date 2020-10-26
 */
public interface DeployNodeService extends IService<DeployPlanNode> {
    /**
     * 保存或更新布设方案卡口点
     * @param deployId 布设卡口方案id
     * @param checkpoints 卡口点坐标
     */
    void createOrUpdate(Long deployId, List<DeployPlanNode> checkpoints);
}
