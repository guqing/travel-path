package xyz.guqing.travelpath.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.travelpath.model.dto.ActionLogDTO;
import xyz.guqing.travelpath.model.entity.ActionLog;
import xyz.guqing.travelpath.model.params.ActionLogQuery;
import xyz.guqing.travelpath.model.support.PageInfo;
import xyz.guqing.travelpath.model.support.PageQuery;
import xyz.guqing.travelpath.model.support.ResultEntity;
import xyz.guqing.travelpath.service.ActionLogService;

/**
 * @author guqing
 * @date 2020-07-11
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/log/action")
public class ActionLogController {
    private final ActionLogService actionLogService;

    @GetMapping
    public ResultEntity<PageInfo<ActionLogDTO>> list(ActionLogQuery actionLogQuery, PageQuery pageQuery) {
        IPage<ActionLog> actionLogPage = actionLogService.listBy(actionLogQuery, pageQuery);
        return ResultEntity.okList(actionLogPage, actionLog -> new ActionLogDTO().convertFrom(actionLog));
    }

    @GetMapping("/user")
    public ResultEntity<PageInfo<ActionLogDTO>> listByUser(PageQuery pageQuery) {
        IPage<ActionLog> actionLogPage = actionLogService.listByUser(pageQuery);
        return ResultEntity.okList(actionLogPage, actionLog -> new ActionLogDTO().convertFrom(actionLog));
    }
}
