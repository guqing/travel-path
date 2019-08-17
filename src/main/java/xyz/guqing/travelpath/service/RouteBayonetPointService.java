package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.RouteBayonetPoint;
import xyz.guqing.travelpath.exception.RouteBayonetPointException;
import xyz.guqing.travelpath.mapper.RouteBayonetPointMapper;

import java.util.Date;
import java.util.List;

/**
 * 车辆途径卡口方案坐标点业务逻辑层
 *
 * @author guqin
 * @date 2019-08-15 15:02
 */
@Service
public class RouteBayonetPointService {
	private final RouteBayonetPointMapper pointMapper;

	@Autowired
	public RouteBayonetPointService(RouteBayonetPointMapper pointMapper) {
		this.pointMapper = pointMapper;
	}

	public void batchSavePoints(List<RouteBayonetPoint> routeBayonetPointList, Long routeId)
			throws RouteBayonetPointException {
		if(routeId == null) {
			throw new RouteBayonetPointException("途经卡口方案id不能为空，无法保存坐标点数据");
		}

		routeBayonetPointList.forEach(routeBayonetPoint -> {
			routeBayonetPoint.setRid(routeId);
			routeBayonetPoint.setCreateTime(new Date());
			routeBayonetPoint.setModifyTime(new Date());

			pointMapper.insert(routeBayonetPoint);
		});
	}
}
