package xyz.guqing.travelpath.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.guqing.travelpath.utils.Result;

/**
 * 全局的异常处理类
 *
 * @author guqin
 * @date 2019-08-30 15:05
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(Exception.class)
	public Object handleException(Exception e) {
		e.printStackTrace();
		logger.error("全局异常拦截，错误信息：{}", e.getMessage());
		return Result.fail();
	}
}
