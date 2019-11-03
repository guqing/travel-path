package xyz.guqing.travelpath.service;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import xyz.guqing.travelpath.entity.model.PresetSchemeExample;
import xyz.guqing.travelpath.entity.support.DeleteConstant;
import xyz.guqing.travelpath.entity.vo.PresetPointExcelVO;
import xyz.guqing.travelpath.entity.vo.PresetSchemeExcelVO;
import xyz.guqing.travelpath.exception.PresetSchemeServiceException;
import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.entity.vo.PresetSchemeVO;
import xyz.guqing.travelpath.listener.ExcelListener;
import xyz.guqing.travelpath.mapper.PresetSchemeMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 预设卡口方案service
 *
 * @author guqin
 * @date 2019-08-09 11:07
 */
@Service
@CacheConfig(cacheNames = "presetSchemeService")
public class PresetSchemeService {
	private PresetSchemeMapper presetSchemeMapper;
	private PresetPointService presetPointService;

	@Autowired
	public PresetSchemeService(PresetSchemeMapper presetSchemeMapper,
							   PresetPointService presetPointService) {
		this.presetSchemeMapper = presetSchemeMapper;
		this.presetPointService = presetPointService;
	}

	@Transactional(rollbackFor = PresetSchemeServiceException.class)
	public PresetScheme savePresetScheme(PresetSchemeVO presetSchemeVO, Integer userId) {
		List<Presetpoint> presetPoints = presetSchemeVO.getPresetpoints();

		PresetScheme presetScheme = getPresetScheme(presetSchemeVO, userId, presetPoints);
		presetSchemeMapper.insert(presetScheme);

		//批量保存预设卡口方案坐标点
		presetPointService.batchSavePresetPoint(presetPoints, presetScheme.getId());

		// 返回给页面跟新列表
		return presetScheme;
	}

	/**
	 * 根据id查询预设卡口方案
	 * @param id 方案id
	 * @return 返回预设卡口i方案
	 */
	@Cacheable(key = "#id", unless = "#result==null")
	public PresetScheme getSchemeById(Long id) {
		return presetSchemeMapper.selectByPrimaryKey(id);
	}

	/**
	 *
	 * @param pageNum 页码
	 * @param pageSize 分页大小
	 * @param userId 用户id
	 * @return 返回预设卡口方案分页对象PageInfo
	 */
	public PageInfo<PresetScheme> listSechemeByPage(Integer pageNum, Integer pageSize, Integer userId) {
		PageHelper.startPage(pageNum, pageSize);
		// 查询
		PresetSchemeExample example = new PresetSchemeExample();
		PresetSchemeExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo(DeleteConstant.RETAIN);
		criteria.andUseridEqualTo(userId);
		List<PresetScheme> presetSchemes = presetSchemeMapper.selectByExample(example);

		return new PageInfo<PresetScheme>(presetSchemes);
	}

	/**
	 * 根据预设卡口方案id集合批量查询方案数据
	 * @param ids 预设卡口方案id集合
	 * @return 返回预设卡口方案Vo
	 */
	public List<PresetSchemeVO> listSchemeByIds(List<Long> ids) {
		List<PresetSchemeVO> presetSchemeVoList = new ArrayList<>();
		ids.forEach(preId -> {
			PresetSchemeVO presetSchemeVO = new PresetSchemeVO();

			PresetScheme presetScheme = presetSchemeMapper.selectByPrimaryKey(preId);
			// 拷贝属性
			BeanUtils.copyProperties(presetScheme, presetSchemeVO);

			//设置方案点集合
			List<Presetpoint> presetpoints = presetPointService.findListById(preId);
			presetSchemeVO.setPresetpoints(presetpoints);

			presetSchemeVoList.add(presetSchemeVO);
		});
		return presetSchemeVoList;
	}

	/**
	 * 根据方案id查询方案详情坐标点数据集
	 * @param preId 预设卡口方案id
	 * @return 返回坐标点数据集合
	 */
	@Cacheable(unless = "#result==null")
	public List<Presetpoint> getPresetPointsByPreId(Long preId) {
		return this.presetPointService.findListById(preId);
	}

	/**
	 * 构建PresetScheme对象，使用已有属性填充
	 * @param presetSchemeVO 预设方案点Vo
	 * @param userId 用户id
	 * @param presetpoints 预设方案坐标点集合
	 * @return 填充完数据的PresetScheme对象
	 */
	private PresetScheme getPresetScheme(PresetSchemeVO presetSchemeVO, Integer userId, List<Presetpoint> presetpoints) {
		PresetScheme presetScheme = new PresetScheme();
		presetScheme.setUserid(userId);
		presetScheme.setBayonetCount(presetpoints.size());
		presetScheme.setName(presetSchemeVO.getName());
		presetScheme.setDescription(presetSchemeVO.getDescription());
		presetScheme.setCreateTime(new Date());
		presetScheme.setModifyTime(new Date());
		presetScheme.setDeleted(DeleteConstant.RETAIN);
		return presetScheme;
	}

	@Transactional(rollbackFor = PresetSchemeServiceException.class)
	@CacheEvict(key = "#presetSchemeVO.id")
	public void updateScheme(PresetSchemeVO presetSchemeVO) {
		Long preId = presetSchemeVO.getId();

		// 构建预设卡口方案数据
		PresetScheme scheme = new PresetScheme();
		scheme.setDeleted(DeleteConstant.RETAIN);
		scheme.setId(presetSchemeVO.getId());

		List<Presetpoint> presetpoints = presetSchemeVO.getPresetpoints();
		if(presetpoints != null) {
			// 更新数据量
			scheme.setBayonetCount(presetpoints.size());
		}

		scheme.setModifyTime(new Date());
		scheme.setName(presetSchemeVO.getName());
		scheme.setDescription(presetSchemeVO.getDescription());
		// 更新预设卡口方案
		this.presetSchemeMapper.updateByPrimaryKeySelective(scheme);

		// 删除方案坐标点集合
		presetPointService.deleteByPreId(preId);
		// 保存方案坐标点集合
		List<Presetpoint> presetPointList = presetSchemeVO.getPresetpoints();
		// 批量保存预设卡口方案坐标点
		presetPointService.batchSavePresetPoint(presetPointList, presetSchemeVO.getId());
	}

	private void deleteById(Long id) {
		presetSchemeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 逻辑删除
	 * @param id 方案id
	 */
	public void logicalDeleted(Long id) {
		this.updateDeleted(id, DeleteConstant.DELETED);
	}

	/**
	 * 批量逻辑删除
	 * @param ids 需要逻辑删除的方案id集合
	 */
	public void batchLogicalDeleted(List<Long> ids) {
		// 方法引用
		ids.forEach(id -> {
			updateDeleted(id, DeleteConstant.DELETED);
		});
	}

	/**
	 * 更新deleted字段
	 * @param id 方案id
	 */
	@Transactional(rollbackFor = PresetSchemeServiceException.class)
	@CacheEvict
	public void updateDeleted(Long id, Byte status) {
		PresetScheme presetScheme = new PresetScheme();
		presetScheme.setId(id);
		presetScheme.setDeleted(status);
		presetSchemeMapper.updateByPrimaryKeySelective(presetScheme);
	}

	/**
	 * 真正的删除
	 * @param id 方案id
	 */
	@Transactional(rollbackFor = PresetSchemeServiceException.class)
	@CacheEvict
	public void sureDeleteById(Long id) {
		this.deleteById(id);
		presetPointService.deleteByPreId(id);
	}

	/**
	 * 保存从Excel上传的数据
	 * @param file excel文件流对象
	 * @param userId 用户id
	 * @throws IOException 从文件流获取InputStream的IO异常
	 */
	@Transactional(rollbackFor = PresetSchemeServiceException.class)
	public void saveUploadSchemeRecord(MultipartFile file, Integer userId) throws IOException {
		InputStream inputStream = file.getInputStream();
		// 解析每行结果在listener中处理，并得到ExcelReader
		ExcelListener listener = new ExcelListener();
		ExcelReader excelReader = new ExcelReader(inputStream, null, listener);

		// 读取并保存excel中的方案数据到数据库中
		Map<Long, PresetScheme> presetSchemeMapList = readSchemeMapListFromExcel(listener, excelReader,userId);

		// 保存方案的坐标点集合数据
		Map<Long, List<Presetpoint>> pointMapListFromExcel = getPointMapListFromExcel(listener, excelReader);

		// 先保存方案数据
		for (Map.Entry<Long, PresetScheme> entry : presetSchemeMapList.entrySet()) {
			// 根据preId取方案坐标点数据的条数count,为方案坐标点数据赋值
			PresetScheme presetScheme = entry.getValue();
			List<Presetpoint> presetpointList = pointMapListFromExcel.get(entry.getKey());
			if(presetpointList != null) {
				presetScheme.setBayonetCount(presetpointList.size());
			}
			// 保存方案数据
			presetSchemeMapper.insert(presetScheme);

			if(presetpointList != null) {
				// 保存方案坐标点数据
				presetPointService.batchSavePresetPoint(presetpointList, presetScheme.getId());
			}
		}
	}

	/**
	 * 从上传的Excel读取预设卡口方案数据,以execl表中填写的id
	 * 作为key,读取到的数据作为value存储到map中返回
	 * 预设卡口方案和预设卡口方案坐标数据在同一个excel的不同sheet中
	 * 存储，保存到数据库时批量的方案数据需要和批量的方案坐标数据关联依靠
	 * 保存预设卡口方案到数据库后返回的id值，而excel中的方案数据要和方案
	 * 对应的坐标点数据形成关联就需要靠用户填写的方案id相关联，而用户填写
	 * 的方案id并不是真正存储到数据库时的id,所以需要以用户填写的方案id作为键
	 * 数据作为值存储到map形成对应关系，等把预设卡口方案数据存储到数据库后返回
	 * 真正的方案id时在根据旧id去除对应的方案坐标点数据集合重新赋值对应的方案id即preId
	 * 而后在保存方案对象的坐标点数据集到数据库中。
	 * @param listener 读取excel的监听器
	 * @param excelReader excel读取器对象
	 * @param userId 用户id
	 * @return 返回插入到数据库的方案id集合
	 */
	private Map<Long, PresetScheme> readSchemeMapListFromExcel(ExcelListener listener, ExcelReader excelReader, Integer userId) {
		// 先清空listener中的List容器，安全起见防止脏数据
		listener.getDataList().clear();

		// 读取sheet1的数据
		excelReader.read(new Sheet(1, 1, PresetSchemeExcelVO.class));
		List<Object> presetSchemeExcelList = listener.getDataList();

		Map<Long, PresetScheme> presetSchemeMap = new HashMap<>();
		presetSchemeExcelList.forEach(presetSchemeExcelVO -> {
			PresetScheme presetScheme = new PresetScheme();
			BeanUtils.copyProperties(presetSchemeExcelVO, presetScheme);
			presetScheme.setCreateTime(new Date());
			presetScheme.setModifyTime(new Date());
			presetScheme.setDeleted(DeleteConstant.RETAIN);
			presetScheme.setUserid(userId);

			presetSchemeMap.put(presetScheme.getId(), presetScheme);
		});

		return presetSchemeMap;
	}

	/**
	 * 从上传的excel文件流中读取方案的坐标点数据集
	 * 并与用户填写的对应方案id形成map返回
	 * @param listener excel读取监听器
	 * @param excelReader excel读取对象
	 * @return 返回用户填写的方案id与从excel中读取的数据形成对应关系的Map集合
	 */
	private Map<Long, List<Presetpoint>> getPointMapListFromExcel(ExcelListener listener,ExcelReader excelReader) {
		// 先清空listener中的List容器，安全起见防止脏数据
		listener.getDataList().clear();

		Map<Long, List<Presetpoint>> preSetPointMapListByPreId = new HashMap<>(8);

		// 读取sheet2的数据
		excelReader.read(new Sheet(2, 1, PresetPointExcelVO.class));
		List<Object> presetPointExcelList = listener.getDataList();

		for(Object presetPointExcelVo : presetPointExcelList) {
			Presetpoint presetpoint = new Presetpoint();
			BeanUtils.copyProperties(presetPointExcelVo, presetpoint);

			// 获取map得到List
			Long preId = presetpoint.getPreid();
			if (preId == null) {
				// preId不能为空，如果为空就跳过
				continue;
			}

			// 根据preId从map中取出List
			List<Presetpoint> preSetPointList = preSetPointMapListByPreId.get(preId);
			if (preSetPointList != null) {
				preSetPointList.add(presetpoint);
			} else {
				// 创建
				List<Presetpoint> newPreSetPointList = new ArrayList<>();
				newPreSetPointList.add(presetpoint);
				preSetPointMapListByPreId.put(preId, newPreSetPointList);
			}
		}

		return preSetPointMapListByPreId;
	}

	/**
	 * 分页查询回收站中的预选卡口方案
	 * @param current 当前页码
	 * @param pageSize 页大小
	 * @param userId 用户id
	 * @return 返回回收站中的预选卡口方案
	 */
	public PageInfo<PresetScheme> findTrashByPage(Integer current, Integer pageSize, Integer userId) {
		PageHelper.startPage(current, pageSize);

		PresetSchemeExample example = new PresetSchemeExample();
		PresetSchemeExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andDeletedEqualTo(DeleteConstant.DELETED);

		List<PresetScheme> presetSchemes = presetSchemeMapper.selectByExample(example);
		return new PageInfo<>(presetSchemes);
	}

	/**
	 * 批量彻底删除数据
	 * @param ids id集合
	 */
	public void batchSureDelete(List<Long> ids) {
		for(Long id : ids) {
			this.sureDeleteById(id);
		}
	}
}
