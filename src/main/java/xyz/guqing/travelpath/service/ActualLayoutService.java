package xyz.guqing.travelpath.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.entity.model.ActualLayoutScheme;
import xyz.guqing.travelpath.entity.model.ActualLayoutSchemeExample;
import xyz.guqing.travelpath.entity.support.DeleteConstant;
import xyz.guqing.travelpath.mapper.ActualLayoutSchemeMapper;

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

	
}
