package xyz.guqing.travelpath.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import xyz.guqing.travelpath.exception.CreekInternalException;
import xyz.guqing.travelpath.model.annotation.ControllerEndpoint;
import xyz.guqing.travelpath.service.ActionLogService;
import xyz.guqing.travelpath.utils.SecurityUserHelper;
import xyz.guqing.travelpath.utils.TravelPathUtils;

import java.lang.reflect.Method;

/**
 * @author guqing
 * @date 2020-6-1
 */
@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class ControllerEndpointAspect extends AbstractAspectSupport {

    private final ActionLogService violetActionLogService;

    @Pointcut("@annotation(xyz.guqing.travelpath.model.annotation.ControllerEndpoint)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws CreekInternalException {
        Object result;
        Method targetMethod = resolveMethod(point);
        ControllerEndpoint annotation = targetMethod.getAnnotation(ControllerEndpoint.class);
        String operation = annotation.operation();
        long start = System.currentTimeMillis();
        try {
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)) {
                String username = SecurityUserHelper.getCurrentUsername();
                String ip = TravelPathUtils.getRequestIpAddress();
                violetActionLogService.saveLog(point, targetMethod, ip, operation, username, start);
            }
            return result;
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
            String exceptionMessage = annotation.exceptionMessage();
            String message = throwable.getMessage();
            String error = TravelPathUtils.containChinese(message) ? exceptionMessage + "，" + message : exceptionMessage;
            throw new CreekInternalException(error);
        }
    }
}



