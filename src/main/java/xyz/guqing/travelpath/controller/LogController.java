package xyz.guqing.travelpath.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.Log;
import xyz.guqing.travelpath.service.LogService;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

/**
 * 日志处理
 *
 * @author guqin
 * @date 2019-08-30 15:34
 */
@RestController
@RequestMapping("/log")
public class LogController {
	private LogService logService;

	@Autowired
	public LogController(LogService logService) {
		this.logService = logService;
	}

	@GetMapping("/list")
	public Object listByPage(Integer current, Integer pageSize) {
		MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
		Integer userId = user.getId();
		PageInfo<Log> pageInfo = logService.listLatestLogByPage(current, pageSize, userId);
		return Result.okList(pageInfo);
	}
}
