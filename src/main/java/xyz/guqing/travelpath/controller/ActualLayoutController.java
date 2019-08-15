package xyz.guqing.travelpath.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.travelpath.entity.model.ActualLayoutScheme;
import xyz.guqing.travelpath.service.ActualBayonetPointService;
import xyz.guqing.travelpath.service.ActualLayoutService;
import xyz.guqing.travelpath.utils.Result;

/**
 * 卡口实际布设方案Controller
 *	list: '/actual/list',
 *  save: '/actual/save',
 *  getScheme: '/actual/getScheme',
 *  trash: '/actual/trash',
 *  batchTrash: '/actual/batch-trash',
 *  update: '/actual/update',
 *  downloadScheme: '/actual/download',
 *  uploadScheme: '/actual/upload'
 * @author guqin
 * @date 2019-08-15 9:56
 */
@RestController
@RequestMapping("/actual")
public class ActualLayoutController {
	private static final Logger logger = LoggerFactory.getLogger(ActualLayoutController.class);
	private final ActualLayoutService layoutService;

	@Autowired
	public ActualLayoutController(ActualLayoutService layoutService) {
		this.layoutService = layoutService;
	}

	@GetMapping("/list")
	public Object listByPage(@RequestParam(defaultValue = "1") Integer current,
							 @RequestParam(defaultValue = "10") Integer pageSize) {
		try {
			PageInfo<ActualLayoutScheme> pageInfo = layoutService.listByPage(current, pageSize);
			return Result.okList(pageInfo);
		} catch (Exception e) {
			logger.error("分页查询布设卡口方案数据出错，入口参数：current:{}，" +
					"pageSize:{},错误信息：{}", current, pageSize, e.getMessage());
			return Result.fail();
		}
	}
}
