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
	@Select("select count(*) from preset_scheme where userid = #{userId} and deleted=0")
	int countPresetScheme(Integer userId);

	/**
	 * 通过用户id统计用户的布设卡口方案数据条数
	 * @param userId 用户id
	 * @return 返回统计后的数据条数
	 */
	@Select("select count(*) from actual_layout_scheme where userid = #{userId} and deleted=0")
	int countActualScheme(Integer userId);

	/**
	 * 统计车辆途经卡口方案数据条数
	 * @param userId 用户id
	 * @return 返回查询得到的数量
	 */
	@Select("select count(*) from route_bayonet_scheme where user_id = #{userId} and deleted=0")
	int countViaScheme(Integer userId);

	/**
	 * 统计用户生成的车辆轨迹总条数
	 * @param userId 用户id
	 * @return 返回查询到的数量
	 */
	@Select("select count(*) from routes where userid = #{userId} and deleted=0")
	int countRouteScheme(Integer userId);

	/**
	 * 统计所有用户数量
	 * @return 返回用户数量
	 */
	@Select("select count(*) from user")
	int countUser();

	/**
	 * 统计所有角色数量
	 * @return 返回角色数量
	 */
	@Select("select count(*) from role")
	int countRole();

	/**
	 * 统计权限资源数量
	 * @return 返回权限资源数量
	 */
	@Select("select count(*) from permission_action")
	int countPermission();

}
