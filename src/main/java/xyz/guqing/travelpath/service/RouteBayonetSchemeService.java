package xyz.guqing.travelpath.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.RouteBayonetScheme;
import xyz.guqing.travelpath.entity.model.RouteBayonetSchemeExample;
import xyz.guqing.travelpath.entity.support.DeleteConstant;
import xyz.guqing.travelpath.entity.vo.RouteBayonetVO;
import xyz.guqing.travelpath.mapper.RouteBayonetSchemeMapper;

import java.util.List;

/**
 * 车辆途径卡口方案业务逻辑层
 *
 * @author guqin
 * @date 2019-08-15 15:02
 */
@Service
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
}
