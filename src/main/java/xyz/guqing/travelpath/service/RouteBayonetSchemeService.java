package xyz.guqing.travelpath.service;

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
import xyz.guqing.travelpath.entity.model.RouteBayonetPoint;
import xyz.guqing.travelpath.entity.model.RouteBayonetScheme;
import xyz.guqing.travelpath.entity.model.RouteBayonetSchemeExample;
import xyz.guqing.travelpath.entity.support.DeleteConstant;
import xyz.guqing.travelpath.entity.vo.RouteBayonetVO;
import xyz.guqing.travelpath.exception.RouteBayonetPointException;
import xyz.guqing.travelpath.exception.RouteBayonetSchemeException;
import xyz.guqing.travelpath.mapper.RouteBayonetSchemeMapper;

import java.util.Date;
import java.util.List;

/**
 * 车辆途径卡口方案业务逻辑层
 *
 * @author guqin
 * @date 2019-08-15 15:02
 */
@Service
@CacheConfig(cacheNames = "routeBayonetSchemeService")
public class RouteBayonetSchemeService {
	private final RouteBayonetSchemeMapper routeBayonetMapper;
	private final RouteBayonetPointService pointService;

	@Autowired
	public RouteBayonetSchemeService(RouteBayonetSchemeMapper routeBayonetMapper,
									 RouteBayonetPointService pointService) {
		this.routeBayonetMapper = routeBayonetMapper;
		this.pointService = pointService;
	}

	/**
	 * 查询方案列表基本信息
	 * @param current 当前页码
	 * @param pageSize 分页大小
	 * @param userId 用户id
	 * @return 返回方案列表集合
	 */
	public PageInfo<RouteBayonetScheme> querySchemeList(Integer current, Integer pageSize, Integer userId) {
		PageHelper.startPage(current, pageSize);
		RouteBayonetSchemeExample example = new RouteBayonetSchemeExample();
		RouteBayonetSchemeExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo(DeleteConstant.RETAIN);

		List<RouteBayonetScheme> routeBayonetSchemes = routeBayonetMapper.selectByExample(example);
		return new PageInfo<>(routeBayonetSchemes);
	}

	@Transactional(rollbackFor = RouteBayonetSchemeException.class)
	public void save(RouteBayonetVO routeBayonetVO) throws RouteBayonetPointException {
		// 保存方案基本信息
		RouteBayonetScheme routeScheme = getRouteScheme(routeBayonetVO);
		routeBayonetMapper.insert(routeScheme);

		// 保存方案坐标信息
		List<RouteBayonetPoint> routeBayonetPoint = routeBayonetVO.getBayonetPoints();
		pointService.batchSavePoints(routeBayonetPoint, routeScheme.getId());
	}

	private RouteBayonetScheme getRouteScheme(RouteBayonetVO routeBayonetVO) {
		RouteBayonetScheme routeBayonetScheme = new RouteBayonetScheme();
		BeanUtils.copyProperties(routeBayonetVO, routeBayonetScheme);
		routeBayonetScheme.setDeleted(DeleteConstant.RETAIN);
		routeBayonetScheme.setCreateTime(new Date());
		routeBayonetScheme.setModifyTime(new Date());
		List<RouteBayonetPoint> bayonetPoints = routeBayonetVO.getBayonetPoints();
		if(bayonetPoints != null) {
			routeBayonetScheme.setBayonetCount(bayonetPoints.size());
		}
		return routeBayonetScheme;
	}

	@Cacheable
	public List<RouteBayonetPoint> getPointById(Long id) {

		return pointService.getPointsByRid(id);
	}

	@Transactional(rollbackFor = RouteBayonetSchemeException.class)
	@CacheEvict
	public void logicalDelete(Long id) {
		updateDeleteStatus(id);
	}

	@Transactional(rollbackFor = RouteBayonetSchemeException.class)
	@CachePut
	public void updateDeleteStatus(Long id) {
		RouteBayonetScheme routeBayonetScheme = new RouteBayonetScheme();
		routeBayonetScheme.setId(id);
		routeBayonetScheme.setDeleted(DeleteConstant.DELETED);

		routeBayonetMapper.updateByPrimaryKeySelective(routeBayonetScheme);
	}

	/**
	 * 批量逻辑删除
	 * @param ids 方案id集合
	 */
	public void batchLogicalDelete(List<Long> ids) {
		ids.forEach(this::updateDeleteStatus);
	}

	/**
	 * 更新数据
	 * @param routeBayonetVO 数据vo对象
	 */
	@Transactional(rollbackFor = RouteBayonetSchemeException.class)
	@CachePut
	public void update(RouteBayonetVO routeBayonetVO) throws RouteBayonetPointException {
		RouteBayonetScheme routeBayonetScheme = getRouteScheme(routeBayonetVO);
		// 不需要创建时间
		routeBayonetScheme.setCreateTime(null);
		// 更新卡口方案基本信息
		routeBayonetMapper.updateByPrimaryKeySelective(routeBayonetScheme);

		//先根据方案id删除坐标数据集
		Long rid = routeBayonetVO.getId();
		pointService.deleteByRid(rid);
		// 在添加坐标数据集
		pointService.batchSavePoints(routeBayonetVO.getBayonetPoints(), rid);
	}
}
