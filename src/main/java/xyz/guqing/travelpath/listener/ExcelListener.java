package xyz.guqing.travelpath.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析监听器
 * 每解析一行会回调invoke()方法。
 * 整个excel解析结束会执行doAfterAllAnalysed()方法
 * 〈一句话功能简述〉<br>
 *
 * @author guqin
 * @date 2019-08-13 21:35
 */
public class ExcelListener extends AnalysisEventListener {
	/**
	 * 自定义用于暂时存储data。
	 * 可以通过实例获取该值
	 */
	private List<Object> dataList = new ArrayList<>();

	@Override
	public void invoke(Object object, AnalysisContext context) {
		System.out.println("当前行："+context.getCurrentRowNum());
		System.out.println(object);
		doSomething(object);
	}

	private void doSomething(Object object) {
		dataList.add(object);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		//解析结束销毁不用的资源
//		dataList.clear();
	}

	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}
}
