package xyz.guqing.travelpath.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 预选卡口方案坐标点集合Excel导入导出VO
 *
 * @author guqing
 * @date 2019-08-13 19:20
 */
public class PresetPointExcelVO extends BaseRowModel {
	@ExcelProperty(value = "ID", index = 0)
	private Long id;
	@ExcelProperty(value = "纬度(Lat)", index = 1)
	private String lat;
	@ExcelProperty(value = "经度(Lng)", index = 2)
	private String lng;
	@ExcelProperty(value = "所属预选卡口方案(preId)", index = 3)
	private Long preId;

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

	public Long getPreId() {
		return preId;
	}

	public void setPreId(Long preId) {
		this.preId = preId;
	}

	@Override
	public String toString() {
		return "PresetPointExcelDTO{" +
				"id=" + id +
				", lat='" + lat + '\'' +
				", lng='" + lng + '\'' +
				", preId=" + preId +
				'}';
	}
}
