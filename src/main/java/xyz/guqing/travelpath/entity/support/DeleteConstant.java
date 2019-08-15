package xyz.guqing.travelpath.entity.support;

/**
 * 数据库记录删除状态常量
 *
 * @author guqin
 * @date 2019-08-15 9:46
 */
public class DeleteConstant {
	/**
	 * 已删除状态，放入回收站
	 */
	public static final Byte DELETED = 1;

	/**
	 * 保留状态，也是默认状态，表示数据没有被放入回收站
	 */
	public static final Byte RETAIN = 0;
}
