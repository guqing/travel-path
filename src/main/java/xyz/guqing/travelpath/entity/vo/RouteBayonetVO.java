package xyz.guqing.travelpath.entity.vo;

import xyz.guqing.travelpath.entity.model.RouteBayonetPoint;

import java.util.List;

/**
 * 车辆途径卡口方案VO
 *
 * @author guqin
 * @date 2019-08-17 10:18
 */
public class RouteBayonetVO {
	private Long id;
	private String name;
	private String description;
	private String carNumber;
	private Long actualId;
	private Integer userId;
	private Integer bayonetCount;
	private List<RouteBayonetPoint> bayonetPoints;

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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Long getActualId() {
		return actualId;
	}

	public void setActualId(Long actualId) {
		this.actualId = actualId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<RouteBayonetPoint> getBayonetPoints() {
		return bayonetPoints;
	}

	public void setBayonetPoints(List<RouteBayonetPoint> bayonetPoints) {
		this.bayonetPoints = bayonetPoints;
	}

	public Integer getBayonetCount() {
		return bayonetCount;
	}

	public void setBayonetCount(Integer bayonetCount) {
		this.bayonetCount = bayonetCount;
	}

	@Override
	public String toString() {
		return "RouteBayonetVO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", carNumber='" + carNumber + '\'' +
				", actualId=" + actualId +
				", userId=" + userId +
				", bayonetCount=" + bayonetCount +
				", bayonetPoints=" + bayonetPoints +
				'}';
	}
}
