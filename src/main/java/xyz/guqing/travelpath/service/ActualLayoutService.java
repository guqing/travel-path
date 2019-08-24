package xyz.guqing.travelpath.service;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import xyz.guqing.travelpath.entity.model.ActualBayonetPoint;
import xyz.guqing.travelpath.entity.model.ActualLayoutScheme;
import xyz.guqing.travelpath.entity.model.ActualLayoutSchemeExample;
import xyz.guqing.travelpath.entity.support.DeleteConstant;
import xyz.guqing.travelpath.entity.vo.ActualLayoutExcelVO;
import xyz.guqing.travelpath.entity.vo.ActualLayoutSchemeVO;
import xyz.guqing.travelpath.entity.vo.ActualPointExcelVO;
import xyz.guqing.travelpath.exception.ActualLayoutException;
import xyz.guqing.travelpath.listener.ExcelListener;
import xyz.guqing.travelpath.mapper.ActualLayoutSchemeMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 卡口实际布设方案Service
 *
 * @author guqing
 * @date 2019-08-15 9:58
 */
@Service
@CacheConfig(cacheNames = "actualLayoutService")
public class ActualLayoutService {
	private final ActualLayoutSchemeMapper layoutSchemeMapper;
	private final ActualBayonetPointService bayonetPointService;

	@Autowired
	public ActualLayoutService(ActualLayoutSchemeMapper layoutSchemeMapper,
							   ActualBayonetPointService bayonetPointService) {
		this.layoutSchemeMapper = layoutSchemeMapper;
		this.bayonetPointService = bayonetPointService;
	}

	/**
	 * 分页查询卡口布设方案数据
	 * @param current 当前页，即页码
	 * @param pageSize 分页大小，即每一页多少条数据
	 * @return 返回分页查询结果pageInfo对象
	 */
	public PageInfo<ActualLayoutScheme> listByPage(Integer current, Integer pageSize) {
		PageHelper.startPage(current, pageSize);

		ActualLayoutSchemeExample example = new ActualLayoutSchemeExample();
		ActualLayoutSchemeExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo(DeleteConstant.RETAIN);
		List<ActualLayoutScheme> actualLayoutSchemes = layoutSchemeMapper.selectByExample(example);

		return new PageInfo<>(actualLayoutSchemes);
	}


	@Transactional(rollbackFor = ActualLayoutException.class)
	public void save(ActualLayoutSchemeVO actualLayoutSchemeVO) {
		// 保存方案名称描述等基本信息
		ActualLayoutScheme layoutScheme = getLayoutScheme(actualLayoutSchemeVO);
		layoutSchemeMapper.insert(layoutScheme);

		// 保存方案下的坐标点数据
		Long schemeId = layoutScheme.getId();
		bayonetPointService.batchSavePoints(actualLayoutSchemeVO.getBayonetPoints(), schemeId);
	}

	private ActualLayoutScheme getLayoutScheme(ActualLayoutSchemeVO actualLayoutSchemeVO) {
		ActualLayoutScheme layoutScheme = new ActualLayoutScheme();
		BeanUtils.copyProperties(actualLayoutSchemeVO, layoutScheme);
		List<ActualBayonetPoint> bayonetPoints = actualLayoutSchemeVO.getBayonetPoints();
		if(bayonetPoints != null) {
			layoutScheme.setBayonetCount(bayonetPoints.size());
		}
		layoutScheme.setDeleted(DeleteConstant.RETAIN);
		layoutScheme.setCreateTime(new Date());
		layoutScheme.setModifyTime(new Date());

		return layoutScheme;
	}

	/**
	 * 根据方案id查询详情
	 * @param id 布设口卡口方案id
	 * @return 返回查询到的方案id对象集合
	 */
	@Cacheable
	public List<ActualBayonetPoint> getSchemePointsById(Long id) {
		return bayonetPointService.queryPointsByActualId(id);
	}

	/**
	 * 逻辑删除方案
	 * @param id 方案id
	 */
	@CachePut
	public void logicalDelete(Long id) {
		// 更新删除状态
		updateDeleteStatus(id);
	}

	/**
	 * 批量逻辑删除
	 * @param ids 方案id集合
	 */
	public void batchLogicalDelete(List<Long> ids) {
		ids.forEach(this::updateDeleteStatus);
	}

	/**
	 * 根据方案id更新删除状态，逻辑删除
	 * @param id 布设卡口方案id
	 */
	@Transactional(rollbackFor = ActualLayoutException.class)
	public void updateDeleteStatus(Long id) {
		ActualLayoutScheme layoutScheme = new ActualLayoutScheme();
		layoutScheme.setId(id);
		layoutScheme.setModifyTime(new Date());
		layoutScheme.setDeleted(DeleteConstant.DELETED);
		//更新数据
		layoutSchemeMapper.updateByPrimaryKeySelective(layoutScheme);
	}

	/**
	 * 根据布设卡口方案id删除数据
	 * @param id 布设卡口方案id
	 */
	@Transactional(rollbackFor = ActualLayoutException.class)
	@CacheEvict
	public void deleteById(Long id) {
		layoutSchemeMapper.deleteByPrimaryKey(id);
		bayonetPointService.deleteByActualId(id);
	}

	/**
	 * 更新方案信息，涉及两张表，方案基本信息表个坐标数据表
	 * @param actualLayoutSchemeVO 布设卡口方案VO
	 */
	@Transactional(rollbackFor = ActualLayoutException.class)
	@CachePut
	public void update(ActualLayoutSchemeVO actualLayoutSchemeVO) {
		ActualLayoutScheme layoutScheme = getLayoutScheme(actualLayoutSchemeVO);
		// 不需要创建时间
		layoutScheme.setCreateTime(null);
		// 更新卡口方案基本信息
		layoutSchemeMapper.updateByPrimaryKeySelective(layoutScheme);

		//先根据方案id删除坐标数据集
		Long actualId = actualLayoutSchemeVO.getId();
		bayonetPointService.deleteByActualId(actualId);
		// 在添加坐标数据集
		bayonetPointService.batchSavePoints(actualLayoutSchemeVO.getBayonetPoints(), actualId);
	}

	/**
	 * 根据方案id集合批量查询布设卡口方案数据
	 * @param ids 方案id
	 * @return 返回方案基本信息和坐标点集合的Vo集合
	 */
	public List<ActualLayoutSchemeVO> listSchemeByIds(List<Long> ids) {
		List<ActualLayoutSchemeVO> actualLayoutSchemeVoList = new ArrayList<>();
		ids.forEach(id -> {
			ActualLayoutSchemeVO actualLayoutSchemeVO = new ActualLayoutSchemeVO();

			// 查询
			ActualLayoutScheme layoutScheme = layoutSchemeMapper.selectByPrimaryKey(id);
			List<ActualBayonetPoint> bayonetPoints = bayonetPointService.queryPointsByActualId(id);

			// 拷贝属性
			BeanUtils.copyProperties(layoutScheme, actualLayoutSchemeVO);
			actualLayoutSchemeVO.setBayonetPoints(bayonetPoints);
			// 添加到集合
			actualLayoutSchemeVoList.add(actualLayoutSchemeVO);
		});

		return actualLayoutSchemeVoList;
	}

	public void saveUploadExcelRecord(MultipartFile file, Integer userId) throws IOException {
		InputStream inputStream = file.getInputStream();
		// 解析每行结果在listener中处理，并得到ExcelReader
		ExcelListener listener = new ExcelListener();
		ExcelReader excelReader = new ExcelReader(inputStream, null, listener);

		// 读取并保存excel中的方案数据到数据库中
		Map<Long, ActualLayoutScheme> actualSchemeMapList = readSchemeMapListFromExcel(listener, excelReader,userId);

		// 保存方案的坐标点集合数据
		Map<Long, List<ActualBayonetPoint>> pointMapListFromExcel = getPointMapListFromExcel(listener, excelReader);

		// 先保存方案数据
		for (Map.Entry<Long, ActualLayoutScheme> entry : actualSchemeMapList.entrySet()) {
			// 根据preId取方案坐标点数据的条数count,为方案坐标点数据赋值
			ActualLayoutScheme actualLayoutScheme = entry.getValue();
			List<ActualBayonetPoint> bayonetPointList = pointMapListFromExcel.get(entry.getKey());
			if(bayonetPointList != null) {
				actualLayoutScheme.setBayonetCount(bayonetPointList.size());
			}
			// 保存方案数据
			layoutSchemeMapper.insert(actualLayoutScheme);

			if(bayonetPointList != null) {
				// 保存方案坐标点数据
				bayonetPointService.batchSavePoints(bayonetPointList, actualLayoutScheme.getId());
			}
		}
	}

	private Map<Long, List<ActualBayonetPoint>> getPointMapListFromExcel(ExcelListener listener, ExcelReader excelReader) {
		// 先清空listener中的List容器，安全起见防止脏数据
		listener.getDataList().clear();

		Map<Long, List<ActualBayonetPoint>> bayonetPointMapList = new HashMap<>(8);

		// 读取sheet2的数据
		excelReader.read(new Sheet(2, 1, ActualPointExcelVO.class));
		List<Object> actualPointExcelList = listener.getDataList();

		for(Object actualPointExcelVo : actualPointExcelList) {
			ActualBayonetPoint actualPoint = new ActualBayonetPoint();
			BeanUtils.copyProperties(actualPointExcelVo, actualPoint);

			// 获取map得到List
			Long actualId = actualPoint.getActualId();
			if (actualId == null) {
				// preId不能为空，如果为空就跳过
				continue;
			}

			// 根据preId从map中取出List
			List<ActualBayonetPoint> actualPointList = bayonetPointMapList.get(actualId);
			if (actualPointList != null) {
				actualPointList.add(actualPoint);
			} else {
				// 创建
				List<ActualBayonetPoint> newActualPointList = new ArrayList<>();
				newActualPointList.add(actualPoint);
				bayonetPointMapList.put(actualId, newActualPointList);
			}
		}
		return bayonetPointMapList;
	}

	private Map<Long, ActualLayoutScheme> readSchemeMapListFromExcel(ExcelListener listener, ExcelReader excelReader, Integer userId) {
		// 先清空listener中的List容器，安全起见防止脏数据
		listener.getDataList().clear();

		// 读取sheet1的数据
		excelReader.read(new Sheet(1, 1, ActualLayoutExcelVO.class));
		List<Object> actualSchemeExcelList = listener.getDataList();

		Map<Long, ActualLayoutScheme> actualSchemeMap = new HashMap<>();
		actualSchemeExcelList.forEach(actualSchemeExcelVO -> {
			ActualLayoutScheme actualScheme = new ActualLayoutScheme();
			BeanUtils.copyProperties(actualSchemeExcelVO, actualScheme);
			actualScheme.setCreateTime(new Date());
			actualScheme.setModifyTime(new Date());
			actualScheme.setDeleted(DeleteConstant.RETAIN);
			actualScheme.setUserid(userId);

			actualSchemeMap.put(actualScheme.getId(), actualScheme);
		});

		return actualSchemeMap;
	}
}
