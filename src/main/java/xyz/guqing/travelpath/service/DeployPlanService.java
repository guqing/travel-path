package xyz.guqing.travelpath.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.Nullable;
import xyz.guqing.travelpath.model.entity.DeployPlan;
import xyz.guqing.travelpath.model.support.PageQuery;

/**
 * 布设卡口方案service
 * @author guqing
 * @date 2020-10-22
 */
public interface DeployPlanService extends IService<DeployPlan> {
    /**
     * 查询布设卡口方案
     * @param currentUserId 用户id
     * @param name 方案名称
     * @param pageQuery 分页参数
     * @return 返回方案数据
     */
    Page<DeployPlan> listByPage(Long currentUserId, @Nullable String name, PageQuery pageQuery);
}
