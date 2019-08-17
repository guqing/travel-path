package xyz.guqing.travelpath.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.RouteBayonetScheme;
import xyz.guqing.travelpath.entity.vo.RouteBayonetVO;
import xyz.guqing.travelpath.service.RouteBayonetSchemeService;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

/**
 * 车辆途径卡口方案controller
 *	 list: '/via/list',
 *   save: '/via/save',
 *   getScheme: '/via/get',
 *   trash: '/via/trash',
 *   batchTrash: '/via/batch-trash',
 *   update: '/via/update',
 *   downloadScheme: '/via/download',
 *   uploadScheme: '/via/upload'
 * @author guqin
 * @date 2019-08-15 14:57
 */
@RestController
@RequestMapping("/via")
public class RouteBayonetController {
	private static final Logger logger = LoggerFactory.getLogger(RouteBayonetController.class);

	private final RouteBayonetSchemeService routeBayonetService;

	@Autowired
	public RouteBayonetController(RouteBayonetSchemeService routeBayonetService) {
		this.routeBayonetService = routeBayonetService;
	}

	@GetMapping("/list")
	public Object listScheme(@RequestParam(defaultValue = "1") Integer current,
							 @RequestParam(defaultValue = "10") Integer pageSize) {
		try {
			MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
			Integer userId = user.getId();
			PageInfo<RouteBayonetScheme> routeBayonetVO = routeBayonetService.querySchemeList(current, pageSize, userId);

			return Result.okList(routeBayonetVO);
		} catch (Exception e) {
			logger.error("查询途经卡口方案列表失败，入口参数：current:{}, pageSize:{}，错误信息：{}",
					current, pageSize, e.getMessage());
			return Result.fail();
		}

	}

}
