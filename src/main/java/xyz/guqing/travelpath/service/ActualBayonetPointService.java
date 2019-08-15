package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.travelpath.entity.model.ActualBayonetPoint;
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
}
