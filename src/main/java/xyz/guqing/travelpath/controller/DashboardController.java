package xyz.guqing.travelpath.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.service.DashboardService;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import java.util.Map;

/**
 * 首页仪表盘Controller
 *
 * @author guqin
 * @date 2019-08-30 11:25
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	private DashboardService dashboardService;

	@Autowired
	public DashboardController(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	@GetMapping("/overview")
	public Object countOverview() {
		try {
			MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
			Integer userId = user.getId();
			Map<String, Integer> countMap =  dashboardService.countOverview(userId);
			return Result.ok(countMap);
		} catch (Exception e) {
			logger.error("统计数据概述面板记录出错，错误信息：{}", e.getMessage());
			return Result.fail();
		}
	}
}
