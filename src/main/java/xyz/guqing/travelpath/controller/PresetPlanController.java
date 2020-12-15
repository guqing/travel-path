package xyz.guqing.travelpath.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.exception.NotFoundException;
import xyz.guqing.travelpath.model.dos.PresetPlanDO;
import xyz.guqing.travelpath.model.dto.PresetPlanDTO;
import xyz.guqing.travelpath.model.entity.PresetPlan;
import xyz.guqing.travelpath.model.params.PresetPlanParam;
import xyz.guqing.travelpath.model.support.PageInfo;
import xyz.guqing.travelpath.model.support.PageQuery;
import xyz.guqing.travelpath.model.support.ResultEntity;
import xyz.guqing.travelpath.service.PresetPlanService;

import javax.validation.Valid;
import java.util.List;

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
    public ResultEntity<PageInfo<PresetPlan>> list(String name, PageQuery pageQuery) {
        Page<PresetPlan> page = presetPlanService.listByPage(name, pageQuery);
        return ResultEntity.okList(page, presetPlan -> presetPlan);
    }

    @GetMapping("/{id:\\d+}")
    public ResultEntity getById(@PathVariable Long id) {
        PresetPlanDO presetPlanDO = presetPlanService.getDetailById(id);
        if(presetPlanDO == null) {
            throw new NotFoundException("数据不存在");
        }
        PresetPlanDTO presetPlanDTO = new PresetPlanDTO().convertFrom(presetPlanDO);
        return ResultEntity.ok(presetPlanDTO);
    }

    @PostMapping
    public ResultEntity createBy(@RequestBody @Valid PresetPlanParam presetPlanParam) {
        presetPlanService.createOrUpdate(presetPlanParam);
        return ResultEntity.ok();
    }

    @PutMapping("/{id:\\d+}")
    public ResultEntity updateBy(@PathVariable Long id,
                                 @RequestBody @Valid PresetPlanParam presetPlanParam) {
        presetPlanParam.setId(id);
        presetPlanService.createOrUpdate(presetPlanParam);
        return ResultEntity.ok();
    }

    @DeleteMapping
    public ResultEntity delete(@RequestBody List<Long> ids) {
        presetPlanService.removeByIds(ids);
        return ResultEntity.ok();
    }
}
