package xyz.guqing.travelpath.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.travelpath.entity.model.PresetSchemeExample;
import xyz.guqing.travelpath.exception.PresetSchemeServiceException;
import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.entity.vo.PresetSchemeVO;
import xyz.guqing.travelpath.mapper.PresetSchemeMapper;

import java.util.ArrayList;
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
		List<Presetpoint> presetPoints = presetSchemeVO.getPresetpoints();

		PresetScheme presetScheme = getPresetScheme(presetSchemeVO, userId, presetPoints);
		presetSchemeMapper.insert(presetScheme);

		//批量保存预设卡口方案坐标点
		presetPointService.batchSavePresetPoint(presetPoints, presetSchemeVO.getId());

		// 返回给页面跟新列表
		return presetScheme;
	}

	/**
	 * 根据id查询预设卡口方案
	 * @param id 方案id
	 * @return 返回预设卡口i方案
	 */
	public PresetScheme getSchemeById(Long id) {
		return presetSchemeMapper.selectByPrimaryKey(id);
	}

	/**
	 *
	 * @param pageNum 页码
	 * @param pageSize 分页大小
	 * @param userId 用户id
	 * @return 返回预设卡口方案分页对象PageInfo
	 */
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
	 * 根据预设卡口方案id集合批量查询方案数据
	 * @param ids 预设卡口方案id集合
	 * @return 返回预设卡口方案Vo
	 */
	public List<PresetSchemeVO> listSchemeByIds(List<Long> ids) {
		List<PresetSchemeVO> presetSchemeVOList = new ArrayList<>();
		ids.forEach(preId -> {
			PresetSchemeVO presetSchemeVO = new PresetSchemeVO();

			PresetScheme presetScheme = presetSchemeMapper.selectByPrimaryKey(preId);
			// 拷贝属性
			BeanUtils.copyProperties(presetScheme, presetSchemeVO);

			//设置方案点集合
			List<Presetpoint> presetpoints = presetPointService.findListById(preId);
			presetSchemeVO.setPresetpoints(presetpoints);

			presetSchemeVOList.add(presetSchemeVO);
		});
		return presetSchemeVOList;
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
	public void updateScheme(PresetSchemeVO presetSchemeVO) {
		Long preId = presetSchemeVO.getId();

		// 构建预设卡口方案数据
		PresetScheme scheme = new PresetScheme();
		scheme.setDeleted(new Byte("0"));
		scheme.setId(presetSchemeVO.getId());
		scheme.setModifyTime(new Date());
		scheme.setName(presetSchemeVO.getName());
		scheme.setDescription(presetSchemeVO.getDescription());
		// 更新预设卡口方案
		this.presetSchemeMapper.updateByPrimaryKeySelective(scheme);

		// 删除方案坐标点集合
		presetPointService.deleteByPreId(preId);
		// 保存方案坐标点集合
		List<Presetpoint> presetPointList = presetSchemeVO.getPresetpoints();
		// 批量保存预设卡口方案坐标点
		presetPointService.batchSavePresetPoint(presetPointList, presetSchemeVO.getId());
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
	 * 批量逻辑删除
	 * @param ids 需要逻辑删除的方案id集合
	 */
	public void batchLogicalDeleted(List<Long> ids) {
		// 方法引用
		ids.forEach(this::updateDeleted);
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
