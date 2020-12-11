package xyz.guqing.travelpath.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.mapper.DeployPlanMapper;
import xyz.guqing.travelpath.model.dos.DeployPlanDO;
import xyz.guqing.travelpath.model.entity.DeployPlan;
import xyz.guqing.travelpath.model.entity.DeployPlanNode;
import xyz.guqing.travelpath.model.params.DeployPlanParam;
import xyz.guqing.travelpath.model.support.PageQuery;
import xyz.guqing.travelpath.service.DeployNodeService;
import xyz.guqing.travelpath.service.DeployPlanService;
import xyz.guqing.travelpath.utils.PageUtils;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import java.util.List;

/**
 * @author guqing
 * @date 2020-10-22
 */
@Service
public class DeployPlanServiceImpl extends ServiceImpl<DeployPlanMapper, DeployPlan> implements DeployPlanService {
    private final DeployNodeService deployNodeService;

    public DeployPlanServiceImpl(DeployNodeService deployNodeService) {
        this.deployNodeService = deployNodeService;
    }

    @Override
    public Page<DeployPlan> listByPage(Long currentUserId, String name, PageQuery pageQuery) {
        LambdaQueryWrapper<DeployPlan> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DeployPlan::getUserId, currentUserId);
        if(StringUtils.isNotBlank(name)) {
            queryWrapper.like(DeployPlan::getName, name);
        }
        return page(PageUtils.convert(pageQuery), queryWrapper);
    }

    @Override
    public DeployPlanDO getDetailById(Long id) {
        return this.baseMapper.findDetailById(id);
    }

    @Override
    public void createOrUpdate(DeployPlanParam deployPlanParam) {
        DeployPlan deployPlan = deployPlanParam.convertTo();

        Long currentUserId = SecurityUserHelper.getCurrentUserId();
        deployPlan.setUserId(currentUserId);
        saveOrUpdate(deployPlan);

        List<DeployPlanNode> checkpoints = deployPlanParam.getCheckpoints();
        checkpoints.forEach(deployPlanNode -> {
            deployPlanNode.setDeployId(deployPlan.getId());
        });
        deployNodeService.saveBatch(checkpoints);
    }
}
