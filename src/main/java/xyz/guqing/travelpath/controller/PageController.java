package xyz.guqing.travelpath.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用页面转发处理器
 *
 * @author guqin
 * @date 2019-08-08 11:01
 */
@Controller
@RequestMapping("/pages")
public class PageController {

	/**
	 * RESTful风格页面跳转方法
	 * @param pageName 页面路径名
	 * @return 跳转视图
	 */
	@RequestMapping("/{path}/{pageName}")
	public String pages(@PathVariable("path") String path, @PathVariable("pageName") String pageName) {
		return "pages/"+path+"/"+pageName;
	}

	/**
	 * index 页面的视图跳转方法
	 * @param indexPage index视图名
	 * @return 跳转视图
	 */
	@RequestMapping("/{indexPage}")
	public String index(@PathVariable("indexPage") String indexPage) {
		return indexPage;
	}
}
