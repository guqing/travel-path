package xyz.guqing.travelpath.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.Nullable;
import xyz.guqing.travelpath.model.entity.PresetPlan;
import xyz.guqing.travelpath.model.support.PageQuery;

/**
 * <p>
 *  预设卡口方案service
 * </p>
 * @author guqing
 * @date 2020-10-19
 */
public interface PresetPlanService extends IService<PresetPlan> {
    /**
     * 分页查询方案列表
     * @param name 方案名称
     * @param pageQuery 查询条件
     * @return 返回分页结果
     */
    Page<PresetPlan> listByPage(@Nullable String name, PageQuery pageQuery);
}
