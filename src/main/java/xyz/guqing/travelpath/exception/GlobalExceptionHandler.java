package xyz.guqing.travelpath.exception;

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
	@ExceptionHandler(Exception.class)
	public Object handleException(Exception e) {
		return Result.fail();
	}
}
