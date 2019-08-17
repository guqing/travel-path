package xyz.guqing.travelpath.exception;

/**
 * 车辆途径卡口坐标点信息自定义异常类
 *
 * @author guqin
 * @date 2019-08-15 15:00
 */
public class RouteBayonetPointException extends Exception {
	private String message;
	public RouteBayonetPointException() {
		super();
	}
	public RouteBayonetPointException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
