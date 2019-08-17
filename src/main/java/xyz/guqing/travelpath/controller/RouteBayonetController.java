package xyz.guqing.travelpath.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.RouteBayonetPoint;
import xyz.guqing.travelpath.entity.model.RouteBayonetScheme;
import xyz.guqing.travelpath.entity.vo.RouteBayonetVO;
import xyz.guqing.travelpath.service.RouteBayonetSchemeService;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import java.util.List;

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

	@PostMapping("/save")
	public Object save(@RequestBody RouteBayonetVO routeBayonetVO) {
		MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
		Integer userId = user.getId();
		routeBayonetVO.setUserId(userId);
		Object error = validateRouteBayonet(routeBayonetVO);
		if(error != null) {
			return error;
		}
		try {
			routeBayonetService.save(routeBayonetVO);
			return Result.ok();
		}catch (Exception e) {
			logger.error("保存布设卡口方案信息出错，入口参数：{}，错误信息：{}",
					JSONObject.toJSONString(routeBayonetVO), e.getMessage());
			return Result.fail();
		}
	}

	@GetMapping("/get/{id}")
	public Object getById(@PathVariable("id") Long id) {
		try {
			List<RouteBayonetPoint> routeBayonetPoints = routeBayonetService.getPointById(id);
			return Result.okList(routeBayonetPoints);
		} catch (Exception e) {
			logger.error("查询方案坐标点详情失败，入口参数：{}，错误信息：{}",id, e.getMessage());
		    return Result.fail();
		}
	}

	private Object validateRouteBayonet(RouteBayonetVO routeBayonetVO) {
		if(StringUtils.isBlank(routeBayonetVO.getName())) {
			return Result.badArgument();
		}
		if(routeBayonetVO.getUserId() == null) {
			return Result.badArgument();
		}
		if(routeBayonetVO.getActualId() == null) {
			return Result.badArgument();
		}
		if(StringUtils.isBlank(routeBayonetVO.getCarNumber())) {
			return Result.badArgument();
		}

		return null;
	}

}
