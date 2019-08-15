package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.travelpath.entity.model.ActualBayonetPoint;
import xyz.guqing.travelpath.entity.model.ActualBayonetPointExample;
import xyz.guqing.travelpath.exception.ActualBayonetPointException;
import xyz.guqing.travelpath.mapper.ActualBayonetPointMapper;

import java.util.Date;
import java.util.List;

/**
 * 卡口实际布设方案的坐标点集合Service
 *
 * @author guqin
 * @date 2019-08-15 10:00
 */
@Service
public class ActualBayonetPointService {
	private final ActualBayonetPointMapper bayonetPointMapper;

	@Autowired
	public ActualBayonetPointService(ActualBayonetPointMapper bayonetPointMapper) {
		this.bayonetPointMapper = bayonetPointMapper;
	}

	@Transactional(rollbackFor = ActualBayonetPointException.class)
	public void batchSavePoints(List<ActualBayonetPoint> bayonetPoints, Long actualId) {
		bayonetPoints.forEach(bayonetPoint -> {
			bayonetPoint.setActualId(actualId);
			bayonetPoint.setCreateTime(new Date());
			bayonetPoint.setModifyTime(new Date());
			// 保存
			bayonetPointMapper.insert(bayonetPoint);
		});
	}

	/**
	 * 根据布设方案id查询方案详情，即所有坐标点数据
	 * @param actualId 布设卡口方案id
	 * @return 返回方案坐标点集合
	 */
	public List<ActualBayonetPoint> queryPointsByActualId(Long actualId) {
		ActualBayonetPointExample example = getExampleByEqualToActualId(actualId);
		return bayonetPointMapper.selectByExample(example);
	}

	@Transactional(rollbackFor = ActualBayonetPointException.class)
	public void deleteByActualId(Long actualId) {
		ActualBayonetPointExample example = getExampleByEqualToActualId(actualId);
		bayonetPointMapper.deleteByExample(example);
	}

	/**
	 * 根据布设卡口方案id创建查询条件
	 * riteria.andActualIdEqualTo(actualId);
	 * @param actualId 布设卡口方案id
	 * @return 返回查询条件example对象
	 */
	private ActualBayonetPointExample getExampleByEqualToActualId(Long actualId) {
		ActualBayonetPointExample example = new ActualBayonetPointExample();
		ActualBayonetPointExample.Criteria criteria = example.createCriteria();
		criteria.andActualIdEqualTo(actualId);
		return example;
	}
}
