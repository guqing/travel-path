package xyz.guqing.travelpath.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.entity.vo.PresetSchemeVO;
import xyz.guqing.travelpath.service.PresetPointService;
import xyz.guqing.travelpath.service.PresetSchemeService;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import java.util.Collections;
import java.util.List;

/**
 * 预设卡口方案处理
 *
 * @author guqin
 * @date 2019-08-09 10:59
 */
@RestController
@RequestMapping("/preset")
public class PresetSchemeController {
	private static final Logger logger = LoggerFactory.getLogger(PresetSchemeController.class);
	@Autowired
	private PresetSchemeService presetSchemeService;
	@Autowired
	private PresetPointService presetPointService;

	@PostMapping("/save")
	public Object createScheme(@RequestBody PresetSchemeVO presetSchemeVO) {
		Object error = validateParameter(presetSchemeVO);
		if (error != null) {
			return error;
		}

		MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
		Integer userId = user.getId();

		try {
			presetSchemeService.savePresetScheme(presetSchemeVO, userId);
			return Result.ok();
		} catch (Exception e) {
			logger.error("保存预设卡口方案信息出错，入口参数：{}，错误信息：{}",
					JSONObject.toJSONString(presetSchemeVO), e.getMessage());
			return Result.fail();
		}
	}

	@GetMapping("/get/{pageNum}/{pageSize}")
	public Object listSchemeByPage(@PathVariable("pageNum") Integer pageNum,
								   @PathVariable("pageSize") Integer pageSize) {
//		try {
//
//		} catch (Exception e) {
//
//		}
		return null;
	}

	@GetMapping("/get-scheme/{preId}")
	public Object getPresetPointScheme(@PathVariable("preId") Long preId) {
		try {
			List<Presetpoint> presetpointList = presetPointService.findListById(preId);
			return Result.okList(presetpointList);
		} catch (Exception e) {
			logger.error("根据方案Id获取卡口方案坐标点集合信息出错，入口参数：{}，错误信息：{}",
					preId, e.getMessage());
			return Result.fail();
		}
	}

	@GetMapping("/list-point/{id}")
	public Object findPresetPointList(@PathVariable("id") Long preId) {
		try {
			PresetScheme scheme = presetSchemeService.getSchemeById(preId);
			return Result.ok(scheme);
		} catch (Exception e) {
			logger.error("根据方案Id获取卡口方案信息出错，入口参数：{}，错误信息：{}",
					preId, e.getMessage());
			return Result.fail();
		}
	}

	/**
	 * 校验参数是否合法
	 *
	 * @param presetSchemeVO 预设卡口方案Vo
	 * @return 合法返回null，否则返回错误信息
	 */
	private Object validateParameter(PresetSchemeVO presetSchemeVO) {
		if (StringUtils.isBlank(presetSchemeVO.getName())) {
			return Result.badArgumentValue();
		}

		if (CollectionUtils.isEmpty(presetSchemeVO.getPresetpoints())) {
			return Result.badArgumentValue();
		}
		return null;
	}
}

