package xyz.guqing.travelpath.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.guqing.travelpath.model.bo.CurrentUser;
import xyz.guqing.travelpath.model.dos.UserDO;
import xyz.guqing.travelpath.model.entity.User;
import xyz.guqing.travelpath.model.params.UserQuery;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author guqing
 * @since 2020-05-21
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 返回用户信息的Optional对象
     */
    Optional<CurrentUser> findByUsername(String username);
    /**
     * 根据条件查询用户信息
     * @param userQuery 查询条件
     * @param page 分页，必须放在第一位自动分页
     * @return 返回查询结果，查询不到数据返回{@code null}
     */
    Page<UserDO> findUserBy(Page<UserDO> page, @Param("userQuery") UserQuery userQuery);
}
