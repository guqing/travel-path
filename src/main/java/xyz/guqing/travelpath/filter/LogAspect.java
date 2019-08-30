package xyz.guqing.travelpath.filter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.guqing.travelpath.entity.annotation.WriteLog;
import xyz.guqing.travelpath.entity.dto.MyUserDetails;
import xyz.guqing.travelpath.entity.model.Log;
import xyz.guqing.travelpath.entity.support.LogTypeConstant;
import xyz.guqing.travelpath.service.LogService;
import xyz.guqing.travelpath.utils.SecurityUserHelper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * @author guqin
 * @date 2019-08-30 13:27
 */
@Aspect
@Component
public class LogAspect {
	private HttpServletRequest request;
	private LogService logService;
	@Autowired
	public LogAspect(HttpServletRequest request, LogService logService) {
		this.request = request;
		this.logService = logService;
	}


	@Pointcut("@annotation(xyz.guqing.travelpath.entity.annotation.WriteLog)")
	public void log() {}

	/**
	 *后置通知,在所有标注@WriteLog的地方切入
	 */
	@After("log()")
	public void doAfterAdvice(JoinPoint joinPoint){
		MethodSignature ms=(MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		// 记录日志信息
		insertLogInfo(method);
	}

	private void insertLogInfo(Method method) {
		Log log = getLog(method);
		logService.saveLog(log);
	}

	private Log getLog(Method method) {
		MyUserDetails user = (MyUserDetails) SecurityUserHelper.getCurrentPrincipal();

		// 获取操作名(@log(name="内容"))
		WriteLog annotation = method.getAnnotation(WriteLog.class);
		WriteLog.LogType type = annotation.type();

		//获取request对象
		String ip = request.getRemoteAddr();

		Log log = new Log();
		log.setUserId(user.getId());
		log.setUsername(user.getUsername());
		log.setMethodName(method.getName());
		log.setName(type.getName());
		log.setContent(annotation.value());
		log.setIp(ip);
		log.setType(type.getType());
		return log;
	}
}
