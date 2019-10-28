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

	private PresetpointMapper presetpointMapper;

	@Autowired
	public PresetPointService(PresetpointMapper presetpointMapper) {
		this.presetpointMapper = presetpointMapper;
	}

	/**
	 * 批量添加预设卡口方案坐标点
	 * @param presetPoints 预设卡口方案坐标点集合
	 * @param preId 预设卡口方案Id
	 */
	@Transactional(rollbackFor = PresetPointServiceException.class)
	public void batchSavePresetPoint(List<Presetpoint> presetPoints, Long preId) {
		presetPoints.forEach(presetPoint->{
			presetPoint.setCreateTime(new Date());
			presetPoint.setModifyTime(new Date());
			presetPoint.setPreid(preId);
			// 保存到数据库
			presetpointMapper.insert(presetPoint);
		});
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
