package xyz.guqing.travelpath.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.mapper.DeployNodeMapper;
import xyz.guqing.travelpath.model.entity.DeployPlanNode;
import xyz.guqing.travelpath.service.DeployNodeService;

import java.util.List;

/**
 * @author guqing
 * @date 2020-10-26
 */
@Service
public class DeployNodeServiceImpl extends ServiceImpl<DeployNodeMapper, DeployPlanNode> implements DeployNodeService {

    @Override
    public void createOrUpdate(Long deployId, List<DeployPlanNode> checkpoints) {
        LambdaQueryWrapper<DeployPlanNode> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DeployPlanNode::getDeployId, deployId);
        remove(queryWrapper);

        checkpoints.forEach(deployPlanNode -> {
            deployPlanNode.setDeployId(deployId);
        });
        saveBatch(checkpoints);
    }
}
