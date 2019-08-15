package xyz.guqing.travelpath.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.travelpath.entity.model.ActualBayonetPoint;
import xyz.guqing.travelpath.entity.model.ActualLayoutScheme;
import xyz.guqing.travelpath.entity.model.ActualLayoutSchemeExample;
import xyz.guqing.travelpath.entity.support.DeleteConstant;
import xyz.guqing.travelpath.entity.vo.ActualLayoutSchemeVO;
import xyz.guqing.travelpath.exception.ActualLayoutException;
import xyz.guqing.travelpath.mapper.ActualLayoutSchemeMapper;

import java.util.Date;
import java.util.List;

/**
 * 卡口实际布设方案Service
 *
 * @author guqing
 * @date 2019-08-15 9:58
 */
@Service
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
	public List<ActualBayonetPoint> getSchemePointsById(Long id) {
		return bayonetPointService.queryPointsByActualId(id);
	}

	/**
	 * 逻辑删除方案
	 * @param id 方案id
	 */
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
	private void updateDeleteStatus(Long id) {
		ActualLayoutScheme layoutScheme = new ActualLayoutScheme();
		layoutScheme.setId(id);
		layoutScheme.setModifyTime(new Date());
		layoutScheme.setDeleted(DeleteConstant.DELETED);
		//更新数据
		layoutSchemeMapper.updateByPrimaryKeySelective(layoutScheme);
	}

}
