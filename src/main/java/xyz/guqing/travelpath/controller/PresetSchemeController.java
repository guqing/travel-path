package xyz.guqing.travelpath.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.vo.PresetPointExcelVO;
import xyz.guqing.travelpath.entity.vo.PresetSchemeExcelVO;
import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.entity.vo.PresetSchemeVO;
import xyz.guqing.travelpath.listener.ExcelListener;
import xyz.guqing.travelpath.service.PresetPointService;
import xyz.guqing.travelpath.service.PresetSchemeService;
import xyz.guqing.travelpath.utils.Result;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

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
			PresetScheme presetScheme = presetSchemeService.savePresetScheme(presetSchemeVO, userId);
			return Result.ok(presetScheme);
		} catch (Exception e) {
			logger.error("保存预设卡口方案信息出错，入口参数：{}，错误信息：{}",
					JSONObject.toJSONString(presetSchemeVO), e.getMessage());
			return Result.fail();
		}
	}

	@GetMapping("/list")
	public Object listSchemeByPage(@RequestParam(value="current",defaultValue = "1") Integer pageNo,
								   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
		try {
			MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
			Integer userId = user.getId();
			PageInfo<PresetScheme> presetSchemes = presetSchemeService.listSechemeByPage(pageNo, pageSize, userId);
			return Result.okList(presetSchemes);
		} catch (Exception e) {
			logger.error("分页查询预设卡口方案失败，入口参数：pageNum={},pageSize={}，错误信息：{}",
					pageNo, pageSize, e.getMessage());
			return Result.fail();
		}
	}

	@GetMapping("/getScheme/{preId}")
	public Object getPresetPointScheme(@PathVariable("preId") Long preId) {
		try {
			List<Presetpoint> presetPointList = presetPointService.findListById(preId);
			return Result.okList(presetPointList);
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

	@PutMapping("/update")
	public Object updateScheme(@RequestBody PresetSchemeVO presetSchemeVO) {
		if(presetSchemeVO.getId() == null) {
			return Result.badArgument();
		}
		try {
			presetSchemeService.updateScheme(presetSchemeVO);
			return Result.ok();
		} catch (Exception e) {
			logger.error("更新卡口方案信息出错，入口参数：{}，错误信息：{}",
					presetSchemeVO, e.getMessage());
			return Result.fail();
		}
	}

	/**
	 * 逻辑删除
	 * @return
	 */
	@PostMapping("/trash/{id}")
	public Object throwTrash(@PathVariable("id") Long id) {
		try {
			presetSchemeService.logicalDeleted(id);
			return Result.ok();
		} catch (Exception e) {
			logger.error("逻辑删除卡口方案出错，入口参数：{}，错误信息：{}",
					id, e.getMessage());
			return Result.fail();
		}
	}

	/**
	 * 批量逻辑删除
	 * @return 返回删除结果信息
	 */
	@PostMapping("/batch-trash")
	public Object batchTrash(@RequestBody List<Long> ids) {
		try {
			presetSchemeService.batchLogicalDeleted(ids);
			return Result.ok();
		} catch (Exception e) {
			logger.error("批量逻辑删除卡口方案出错，入口参数：{}，错误信息：{}",
					JSONArray.toJSONString(ids), e.getMessage());
			return Result.fail();
		}
	}

	@PostMapping("/download")
	public void downloadSchemeWithPoints(@RequestBody List<Long> ids, HttpServletResponse response) {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename=PresetSchemeWithPoint.xlsx");

		try {
			ServletOutputStream outputStream = response.getOutputStream();
			List<PresetSchemeVO> presetSchemeVoList = presetSchemeService.listSchemeByIds(ids);

			// 写sheet1
			ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
			Sheet schemeSheet = new Sheet(1, 1, PresetSchemeExcelVO.class);
			schemeSheet.setSheetName("预设卡口方案");
			List<PresetSchemeExcelVO> presetSchemeExcelVoList = transferToSchemeExcelList(presetSchemeVoList);
			writer.write(presetSchemeExcelVoList, schemeSheet);

			// 写sheet2
			List<PresetPointExcelVO> presetPointExcelVoList = transferToPresetPointExcelVO(presetSchemeVoList);
			Sheet presetPointSheet = new Sheet(2, 1, PresetPointExcelVO.class);
			presetPointSheet.setSheetName("预设卡口坐标点");
			presetPointSheet.setAutoWidth(true);
			writer.write(presetPointExcelVoList, presetPointSheet);

			writer.finish();
			outputStream.flush();
		} catch (Exception e) {
			logger.error("批量下载卡口方案数据出错，入口参数：{}，错误信息：{}",
					JSONArray.toJSONString(ids), e.getMessage());
		}
	}

	/**
	 * 预选卡口方案数据Excel文件上传
	 * <li>1. 创建excel对应的实体对象
	 * <li>2. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器
	 * <li>3. 直接读即可
	 */
	@PostMapping("/upload")
	public Object upload(MultipartFile file) {
		try {
			MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();
			Integer userId = user.getId();

			// 异步执行任务
			return new Callable<Object>() {
				@Override
				public Object call() throws Exception {
					// 读取并保存excel数据
					presetSchemeService.saveUploadSchemeRecord(file, userId);
					return Result.ok();
				}
			};
		} catch (Exception e) {
			logger.error("上传卡口方案数据出错，错误信息：{}", e.getMessage());
			return Result.fail();
		}
	}

	/**
	 * @param presetSchemeVoList 预设卡口方案VO集合
	 * @return 适合导出Excel的presetSchemeExcelVOList
	 */
	private List<PresetSchemeExcelVO> transferToSchemeExcelList(List<PresetSchemeVO> presetSchemeVoList) {
		List<PresetSchemeExcelVO> preSetSchemeExcelList = new ArrayList<>();
		presetSchemeVoList.forEach(presetSchemeVO -> {
			PresetSchemeExcelVO presetSchemeExcelVO = new PresetSchemeExcelVO();
			BeanUtils.copyProperties(presetSchemeVO, presetSchemeExcelVO);

			preSetSchemeExcelList.add(presetSchemeExcelVO);
		});
		return preSetSchemeExcelList;
	}

	/**
	 * @param presetSchemeVoList 预设卡口方案VO集合
	 * @return 适合导出Excel的PresetPointExcelVOList
	 */
	private List<PresetPointExcelVO> transferToPresetPointExcelVO(List<PresetSchemeVO> presetSchemeVoList) {
		List<PresetPointExcelVO> presetPointExcelVoList = new ArrayList<>();
		presetSchemeVoList.forEach(presetSchemeVO -> {
			List<Presetpoint> preSetPointList = presetSchemeVO.getPresetpoints();
			preSetPointList.forEach(preSetPoint -> {
				PresetPointExcelVO presetPointExcelVO = new PresetPointExcelVO();
				BeanUtils.copyProperties(preSetPoint, presetPointExcelVO);

				presetPointExcelVoList.add(presetPointExcelVO);
			});
		});
		return presetPointExcelVoList;
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
