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
	@ExcelProperty(value = "方案名称(name)", index = 1)
	private String name;
	@ExcelProperty(value = "方案描述(description)", index = 2)
	private String description;
	@ExcelProperty(value="所属预设卡口方案(presetId)", index = 3)
	private Long presetId;

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

	@Override
	public String toString() {
		return "ActualLayoutExcelVO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", presetId=" + presetId +
				'}';
	}
}
