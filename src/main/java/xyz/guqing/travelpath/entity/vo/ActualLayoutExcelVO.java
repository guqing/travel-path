package xyz.guqing.travelpath.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 布设卡口方案Excel数据VO
 *
 * @author guqin
 * @date 2019-08-16 16:18
 */
public class ActualLayoutExcelVO extends BaseRowModel {
	@ExcelProperty(value = "ID", index = 0)
	private Long id;
	@ExcelProperty(value = "纬度(Lat)", index = 1)
	private String lat;
	@ExcelProperty(value = "经度(Lng)", index = 2)
	private String lng;
	@ExcelProperty(value = "所属布设卡口方案(actualId)", index = 3)
	private Long actualId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public Long getActualId() {
		return actualId;
	}

	public void setActualId(Long actualId) {
		this.actualId = actualId;
	}

	@Override
	public String toString() {
		return "ActualLayoutExcelVO{" +
				"id=" + id +
				", lat='" + lat + '\'' +
				", lng='" + lng + '\'' +
				", actualId=" + actualId +
				'}';
	}
}
