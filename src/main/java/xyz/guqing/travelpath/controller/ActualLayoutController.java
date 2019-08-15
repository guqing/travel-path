package xyz.guqing.travelpath.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.ActualLayoutScheme;
import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.vo.ActualLayoutSchemeVO;
import xyz.guqing.travelpath.service.ActualLayoutService;
import xyz.guqing.travelpath.service.PresetSchemeService;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

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

	@PostMapping("/save")
	public Object saveActualScheme(@RequestBody ActualLayoutSchemeVO actualLayoutSchemeVO) {
		MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
		Integer userId = user.getId();
		actualLayoutSchemeVO.setUserid(userId);
		Object error = validateSchemeVO(actualLayoutSchemeVO);
		if(error != null) {
			return error;
		}

		try {
			layoutService.save(actualLayoutSchemeVO);
			return Result.ok();
		} catch (Exception e) {
			logger.error("新增布设卡口方案数据出错，入口参数：current:{}，pageSize:{},错误信息：{}",
					JSONObject.toJSONString(actualLayoutSchemeVO), e.getMessage());
			return Result.fail();
		}
	}

	/**
	 * @param layoutSchemeVO 包含基本信息和坐标点数据集
	 * @return 校验参数是否合法，合法返回null,不合法返回
	 * (401, 参数不对)的错误提示
	 */
	private Object validateSchemeVO(ActualLayoutSchemeVO layoutSchemeVO) {
		if(StringUtils.isBlank(layoutSchemeVO.getName())) {
			return Result.badArgument();
		}
		if(layoutSchemeVO.getUserid() == null) {
			return Result.badArgument();
		}
		if(layoutSchemeVO.getPresetId() == null) {
			return Result.badArgument();
		}
		return null;
	}
}
