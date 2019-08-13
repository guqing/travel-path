package xyz.guqing.travelpath.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.travelpath.entity.model.PresetSchemeExample;
import xyz.guqing.travelpath.exception.PresetSchemeServiceException;
import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.entity.vo.PresetSchemeVO;
import xyz.guqing.travelpath.mapper.PresetSchemeMapper;

import java.util.Date;
import java.util.List;

/**
 * 预设卡口方案service
 *
 * @author guqin
 * @date 2019-08-09 11:07
 */
@Service
public class PresetSchemeService {
	@Autowired
	private PresetSchemeMapper presetSchemeMapper;

	@Autowired
	private PresetPointService presetPointService;

	@Transactional(rollbackFor = PresetSchemeServiceException.class)
	public PresetScheme savePresetScheme(PresetSchemeVO presetSchemeVO, Integer userId) {
		List<Presetpoint> presetpoints = presetSchemeVO.getPresetpoints();

		PresetScheme presetScheme = getPresetScheme(presetSchemeVO, userId, presetpoints);
		presetSchemeMapper.insert(presetScheme);

		//添加方案标记点
		presetpoints.forEach(presetpoint->{
			presetpoint.setPreid(presetScheme.getId());
			presetPointService.savePresetPoint(presetpoint);
		});
		// 返回给页面跟新列表
		return presetScheme;
	}


	public PresetScheme getSchemeById(Long id) {
		return presetSchemeMapper.selectByPrimaryKey(id);
	}

	public PageInfo<PresetScheme> listSechemeByPage(Integer pageNum, Integer pageSize, Integer userId) {
		PageHelper.startPage(pageNum, pageSize);
		// 查询
		PresetSchemeExample example = new PresetSchemeExample();
		PresetSchemeExample.Criteria criteria = example.createCriteria();
		criteria.andDeletedEqualTo(new Byte("0"));
		criteria.andUseridEqualTo(userId);
		List<PresetScheme> presetSchemes = presetSchemeMapper.selectByExample(example);

		return new PageInfo<PresetScheme>(presetSchemes);
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
		presetScheme.setDeleted(new Byte("0"));
		return presetScheme;
	}

	@Transactional(rollbackFor = PresetSchemeServiceException.class)
	public void updateScheme(PresetSchemeVO presetSchemeVO,Integer userId) {
		//先删除方案在保存
		Long preId = presetSchemeVO.getId();
		this.deleteById(preId);
		//删除方案坐标点集合
		presetPointService.deleteByPreId(preId);

		this.savePresetScheme(presetSchemeVO,userId);
	}

	@Transactional(rollbackFor = PresetSchemeServiceException.class)
	public void deleteById(Long id) {
		presetSchemeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 逻辑删除
	 * @param id 方案id
	 */
	public void logicalDeleted(Long id) {
		this.updateDeleted(id);
	}


	/**
	 * 更新deleted字段
	 * @param id 方案id
	 */
	@Transactional(rollbackFor = PresetSchemeServiceException.class)
	public void updateDeleted(Long id) {
		PresetScheme presetScheme = new PresetScheme();
		presetScheme.setId(id);
		presetScheme.setDeleted(new Byte("1"));
		presetSchemeMapper.updateByPrimaryKeySelective(presetScheme);
	}

	/**
	 * 真正的删除
	 * @param id 方案id
	 */
	@Transactional(rollbackFor = PresetSchemeServiceException.class)
	public void sureDeleteById(Long id) {
		this.deleteById(id);
		presetPointService.deleteByPreId(id);
	}
}
