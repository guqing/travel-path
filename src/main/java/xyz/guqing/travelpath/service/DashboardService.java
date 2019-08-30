package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.mapper.DashboardMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页仪表盘Service
 *
 * @author guqin
 * @date 2019-08-30 11:26
 */
@Service
public class DashboardService {
	private DashboardMapper dashboardMapper;

	@Autowired
	public DashboardService(DashboardMapper dashboardMapper) {
		this.dashboardMapper = dashboardMapper;
	}

	public Map<String, Integer> countOverview(Integer userId) {
		int presetCount = dashboardMapper.countPresetScheme(userId);
		int actualCount = dashboardMapper.countActualScheme(userId);
		int viaCount = dashboardMapper.countViaScheme(userId);
		int routeCount = dashboardMapper.countRouteScheme(userId);

		return getOverviewCountMap(presetCount,actualCount,viaCount,routeCount);
	}

	private Map<String, Integer> getOverviewCountMap(int presetCount, int actualCount, int viaCount, int routeCount) {
		Map<String, Integer> countMap = new HashMap<>();
		countMap.put("presetCount", presetCount);
		countMap.put("actualCount", actualCount);
		countMap.put("viaCount", viaCount);
		countMap.put("routeCount", routeCount);
		return countMap;
	}
}
