package xyz.guqing.travelpath.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.model.support.ResultEntity;
import xyz.guqing.travelpath.service.UserSettingOptionService;

import java.util.Map;

/**
 * @author guqing
 * @date 2020-12-23
 */
@RestController
@RequestMapping("/users/options")
@RequiredArgsConstructor
public class UserOptionController {
    private final UserSettingOptionService userSettingOptionService;

    @GetMapping("/route/weights")
    public ResultEntity<Map<String, Double>> listRoutWeight() {
        Map<String, Double> routeWeights = userSettingOptionService.listRouteWeightsMap();
        return ResultEntity.ok(routeWeights);
    }

    @PutMapping("/route/weights")
    public ResultEntity<String> updateRouteWeight(@RequestBody Map<String, String> weights) {
        userSettingOptionService.updateRouteWeights(weights);
        return ResultEntity.ok();
    }
}
