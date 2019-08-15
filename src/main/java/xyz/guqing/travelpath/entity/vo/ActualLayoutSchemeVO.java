package xyz.guqing.travelpath.entity.vo;

import xyz.guqing.travelpath.entity.model.ActualBayonetPoint;

import java.util.List;

/**
 * 实际卡口布设方案VO,其中包含布设方案基本信息和
 * 坐标数据集
 *
 * @author guqing
 * @date 2019-08-15 10:31
 */
public class ActualLayoutSchemeVO {
	private Long id;
	private String name;
	private String description;
	private Long presetId;
	private Integer userid;
	private List<ActualBayonetPoint> bayonetPoints;

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

	public Long getPresetId() {
		return presetId;
	}

	public void setPresetId(Long presetId) {
		this.presetId = presetId;
	}

	public List<ActualBayonetPoint> getBayonetPoints() {
		return bayonetPoints;
	}

	public void setBayonetPoints(List<ActualBayonetPoint> bayonetPoints) {
		this.bayonetPoints = bayonetPoints;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "ActualLayoutSchemeVO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", presetId=" + presetId +
				", userid=" + userid +
				", bayonetPoints=" + bayonetPoints +
				'}';
	}
}
