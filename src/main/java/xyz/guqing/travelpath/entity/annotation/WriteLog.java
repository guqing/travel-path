package xyz.guqing.travelpath.entity.annotation;

import xyz.guqing.travelpath.entity.support.LogType;
import xyz.guqing.travelpath.entity.support.LogTypeConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 记录日志的自定义注解
 *
 * @author guqin
 * @date 2019-08-30 13:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface WriteLog {
	String value() default "";
	LogType type() default LogType.INSERT;
}
