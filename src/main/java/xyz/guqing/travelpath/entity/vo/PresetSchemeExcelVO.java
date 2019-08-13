package xyz.guqing.travelpath.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 预选卡口方案Excel导入导出VO
 *
 * @author guqing
 * @date 2019-08-13 19:20
 */
public class PresetSchemeExcelVO extends BaseRowModel {
	@ExcelProperty(value = "ID", index = 0)
	private Long id;
	@ExcelProperty(value = "方案名称(name)", index = 1)
	private String name;
	@ExcelProperty(value = "方案描述(description)", index = 2)
	private String description;

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

	@Override
	public String toString() {
		return "PresetSchemeExcelDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
