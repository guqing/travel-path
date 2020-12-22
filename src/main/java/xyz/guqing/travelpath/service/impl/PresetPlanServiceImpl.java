package xyz.guqing.travelpath.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.travelpath.mapper.PresetPlanMapper;
import xyz.guqing.travelpath.model.dos.PresetPlanDO;
import xyz.guqing.travelpath.model.entity.PresetNode;
import xyz.guqing.travelpath.model.entity.PresetPlan;
import xyz.guqing.travelpath.model.params.PresetPlanParam;
import xyz.guqing.travelpath.model.support.PageQuery;
import xyz.guqing.travelpath.service.PresetNodeService;
import xyz.guqing.travelpath.service.PresetPlanService;
import xyz.guqing.travelpath.utils.PageUtils;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import java.util.List;

/**
 * <p>
 * 预设卡口方案service实现
 * </p>
 * @author guqing
 * @date 2020-10-19
 */
@Service
@AllArgsConstructor
public class PresetPlanServiceImpl extends ServiceImpl<PresetPlanMapper, PresetPlan> implements PresetPlanService {
    private final PresetNodeService presetNodeService;

    @Override
    public Page<PresetPlan> listByPage(String name, PageQuery pageQuery) {
        LambdaQueryWrapper<PresetPlan> queryWrapper = Wrappers.lambdaQuery();
        if(StringUtils.isNotBlank(name)) {
            return page(PageUtils.convert(pageQuery), queryWrapper);
        }
        queryWrapper.eq(PresetPlan::getUserId, SecurityUserHelper.getCurrentUserId());
        return page(PageUtils.convert(pageQuery), queryWrapper);
    }

    @Override
    public PresetPlanDO getDetailById(Long id) {
        return this.baseMapper.findDetailById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrUpdate(PresetPlanParam presetPlanParam) {
        PresetPlan presetPlan = presetPlanParam.convertTo();

        Long currentUserId = SecurityUserHelper.getCurrentUserId();
        presetPlan.setUserId(currentUserId);

        saveOrUpdate(presetPlan);

        List<PresetNode> checkpoints = presetPlanParam.getCheckpoints();
        presetNodeService.createOrUpdate(presetPlan.getId(), checkpoints);
    }
}
