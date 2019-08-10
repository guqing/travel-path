package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	public void savePresetScheme(PresetSchemeVO presetSchemeVO, Integer userId) {
		List<Presetpoint> presetpoints = presetSchemeVO.getPresetpoints();

		PresetScheme presetScheme = getPresetScheme(presetSchemeVO, userId, presetpoints);
		presetSchemeMapper.insert(presetScheme);

		//添加方案标记点
		presetpoints.forEach(presetpoint->{
			presetpoint.setPreid(presetScheme.getId());
			presetPointService.savePresetPoint(presetpoint);
		});
	}


	public PresetScheme getSchemeById(Long id) {
		return presetSchemeMapper.selectByPrimaryKey(id);
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
}
