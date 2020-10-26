package xyz.guqing.travelpath.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.mapper.DeployNodeMapper;
import xyz.guqing.travelpath.model.entity.DeployPlanNode;
import xyz.guqing.travelpath.service.DeployNodeService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guqing
 * @date 2020-10-26
 */
@Service
public class DeployNodeServiceImpl extends ServiceImpl<DeployNodeMapper, DeployPlanNode> implements DeployNodeService {
    @Override
    public void createOrUpdate(@NonNull Long planId, @NonNull List<Long> nodeIds) {
        LambdaQueryWrapper<DeployPlanNode> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DeployPlanNode::getDeployId, planId);
        remove(queryWrapper);

        List<DeployPlanNode> collect = nodeIds.stream().map(nodeId -> {
            DeployPlanNode deployPlanNode = new DeployPlanNode();
            deployPlanNode.setPresetNodeId(nodeId);
            deployPlanNode.setDeployId(planId);
            return deployPlanNode;
        }).collect(Collectors.toList());
        saveBatch(collect);
    }
}
