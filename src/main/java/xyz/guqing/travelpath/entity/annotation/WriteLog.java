package xyz.guqing.travelpath.entity.annotation;

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

	enum LogType {
		/**
		 * 日志类型为新增
		 */
		INSERT(LogTypeConstant.INSERT, "新增"),
		/**
		 * 日志类型为更新
		 */
		UPDATE(LogTypeConstant.UPDATE, "更新"),
		/**
		 * 日志类型为删除
		 */
		DELETE(LogTypeConstant.DELETE, "删除"),
		/**
		 * 日志类型为用户登录
		 */
		LOGIN(LogTypeConstant.LOGIN, "登录");

		private int type;
		private String name;
		private LogType(int type, String name){
			this.type = type;
			this.name = name;
		}
		public int getType() {
			return type;
		}
		public String getName() {
			return name;
		}
	}
}
