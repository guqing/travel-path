package xyz.guqing.travelpath.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.mapper.PresetPlanMapper;
import xyz.guqing.travelpath.model.dos.PresetPlanDO;
import xyz.guqing.travelpath.model.dto.PresetPlanDTO;
import xyz.guqing.travelpath.model.entity.PresetPlan;
import xyz.guqing.travelpath.model.support.PageQuery;
import xyz.guqing.travelpath.service.PresetPlanService;
import xyz.guqing.travelpath.utils.PageUtils;

/**
 * <p>
 * 预设卡口方案service实现
 * </p>
 * @author guqing
 * @date 2020-10-19
 */
@Service
public class PresetPlanServiceImpl extends ServiceImpl<PresetPlanMapper, PresetPlan> implements PresetPlanService {
    @Override
    public Page<PresetPlan> listByPage(String name, PageQuery pageQuery) {
        if(StringUtils.isNotBlank(name)) {
            LambdaQueryWrapper<PresetPlan> queryWrapper = Wrappers.lambdaQuery();
            return page(PageUtils.convert(pageQuery), queryWrapper);
        }
        return page(PageUtils.convert(pageQuery));
    }

    @Override
    public PresetPlanDO getDetailById(Long id) {
        return this.baseMapper.getDetailById(id);
    }
}
