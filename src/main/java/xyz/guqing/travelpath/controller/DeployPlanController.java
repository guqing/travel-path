package xyz.guqing.travelpath.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.model.entity.DeployPlan;
import xyz.guqing.travelpath.model.support.PageQuery;
import xyz.guqing.travelpath.model.support.ResultEntity;
import xyz.guqing.travelpath.service.DeployPlanService;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import java.util.List;

/**
 * @author guqing
 * @date 2020-10-22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/deploy")
public class DeployPlanController {
    private final DeployPlanService deployPlanService;

    @GetMapping("/list")
    public ResultEntity list(String name, PageQuery pageQuery) {
        Long currentUserId = SecurityUserHelper.getCurrentUserId();
        Page<DeployPlan> page = deployPlanService.listByPage(currentUserId, name, pageQuery);
        return ResultEntity.okList(page, deployPlan -> deployPlan);
    }

    @GetMapping("/{id:\\d+}")
    public ResultEntity getById(@PathVariable Long id) {
        return ResultEntity.ok();
    }

    @PostMapping("/create-update")
    public ResultEntity createOrUpdate() {
        return ResultEntity.ok();
    }

    @DeleteMapping
    public ResultEntity delete(@RequestBody List<Long> ids) {
        return ResultEntity.ok();
    }
}
