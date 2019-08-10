package xyz.guqing.travelpath.entity.vo;

import xyz.guqing.travelpath.entity.model.PresetScheme;
import xyz.guqing.travelpath.entity.model.Presetpoint;
import xyz.guqing.travelpath.service.PresetPointService;

import java.io.Serializable;
import java.util.List;

/**
 * 预设卡口方案vo
 *
 * @author guqin
 * @date 2019-08-09 11:09
 */
public class PresetSchemeVO implements Serializable {
	private Long id;
	private String name;
	private String description;
	private List<Presetpoint> presetpoints;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Presetpoint> getPresetpoints() {
		return presetpoints;
	}

	public void setPresetpoints(List<Presetpoint> presetpoints) {
		this.presetpoints = presetpoints;
	}

	@Override
	public String toString() {
		return "PresetSchemeVO{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", presetpoints=" + presetpoints +
				'}';
	}
}
