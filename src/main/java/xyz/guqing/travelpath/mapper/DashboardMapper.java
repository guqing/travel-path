package xyz.guqing.travelpath.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DashboardMapper {

	/**
	 * 通过用户id统计用户的预设卡口方案的数据条数
	 * @param userId 用户id
	 * @return 返回统计数量
	 */
	@Select("select count(*) from preset_scheme where userid = #{userId}")
	int countPresetScheme(Integer userId);

	/**
	 * 通过用户id统计用户的布设卡口方案数据条数
	 * @param userId 用户id
	 * @return 返回统计后的数据条数
	 */
	@Select("select count(*) from actual_layout_scheme where userid = #{userId}")
	int countActualScheme(Integer userId);

	/**
	 * 统计车辆途经卡口方案数据条数
	 * @param userId 用户id
	 * @return 返回查询得到的数量
	 */
	@Select("select count(*) from route_bayonet_scheme where userid = #{userId}")
	int countViaScheme(Integer userId);

	/**
	 * 统计用户生成的车辆轨迹总条数
	 * @param userId 用户id
	 * @return 返回查询到的数量
	 */
	@Select("select count(*) from routes where userid = #{userId}")
	int countRouteScheme(Integer userId);
}