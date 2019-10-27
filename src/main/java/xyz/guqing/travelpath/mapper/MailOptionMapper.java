package xyz.guqing.travelpath.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.guqing.travelpath.entity.model.Optional;

import java.util.List;

/**
 * 邮件参数mapper<br>
 *
 * @author guqin
 * @date 2019-10-26 23:06
 */
@Mapper
public interface MailOptionMapper {
    /**
     * 查询邮件相关参数
     * @return 返回邮件参数类
     */
    @Select("SELECT * FROM public.optional where group_name='email'")
    List<Optional> getMailOption();
}
