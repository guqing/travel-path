package xyz.guqing.travelpath.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.travelpath.model.dos.PresetPlanDO;
import xyz.guqing.travelpath.model.dto.PresetPlanDTO;
import xyz.guqing.travelpath.model.entity.PresetPlan;
import xyz.guqing.travelpath.model.support.PageQuery;
import xyz.guqing.travelpath.model.support.ResultEntity;
import xyz.guqing.travelpath.service.PresetPlanService;

/**
 * @author guqing
 * @date 2020-10-19
 */
@RestController
@RequestMapping("/preset")
public class PresetPlanController {
    private final PresetPlanService presetPlanService;

    public PresetPlanController(PresetPlanService presetPlanService) {
        this.presetPlanService = presetPlanService;
    }

    @GetMapping("/list")
    public ResultEntity list(String name, PageQuery pageQuery) {
        Page<PresetPlan> page = presetPlanService.listByPage(name, pageQuery);
        return ResultEntity.ok(page);
    }

    @GetMapping("/{id:\\d+}")
    public ResultEntity getById(@PathVariable Long id) {
        PresetPlanDO presetPlanDO = presetPlanService.getDetailById(id);
        PresetPlanDTO presetPlanDTO = new PresetPlanDTO().convertFrom(presetPlanDO);
        return ResultEntity.ok(presetPlanDTO);
    }
}
