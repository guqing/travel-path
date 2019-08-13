package xyz.guqing.travelpath.service;
import	java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.entity.model.PresetpointExample;
import xyz.guqing.travelpath.exception.PresetPointServiceException;
import xyz.guqing.travelpath.mapper.PresetpointMapper;

import java.util.Date;
import java.util.List;

/**
 * 卡口预设方案中点的service
 *
 * @author guqin
 * @date 2019-08-09 11:00
 */
@Service
public class PresetPointService {
	@Autowired
	private PresetpointMapper presetpointMapper;

	/**
	 * 添加卡口预设方案坐标点数据
	 * @param presetpoint 预设卡口坐标点模型对象
	 */
	@Transactional(rollbackFor = PresetPointServiceException.class)
	public void savePresetPoint(Presetpoint presetpoint) {
		presetpoint.setCreateTime(new Date());
		presetpoint.setModifyTime(new Date());
		presetpointMapper.insert(presetpoint);
	}

	/**
	 * 根据Id获取
	 * @param preId 预设卡口方案Id
	 * @return 返回查询到的预设卡口方案坐标点对象集合
	 */
	public List<Presetpoint> findListById(Long preId) {
		PresetpointExample example = new PresetpointExample();
		PresetpointExample.Criteria criteria = example.createCriteria();
		criteria.andPreidEqualTo(preId);

		return presetpointMapper.selectByExample(example);
	}

	/**
	 * 根据方案preid删除方案坐标点记录
	 * @param peId 方案id
	 */
	public void deleteByPreId(Long peId) {
		PresetpointExample example = new PresetpointExample();
		PresetpointExample.Criteria criteria = example.createCriteria();
		criteria.andPreidEqualTo(peId);
		presetpointMapper.deleteByExample(example);
	}
}
