package xyz.guqing.travelpath.model.params;

import lombok.Data;
import xyz.guqing.travelpath.model.support.PageQuery;

/**
 * @author guqing
 * @date 2020-06-05
 */
@Data
public class RoleQuery {
    private Long id;
    private String roleName;
    private String remark;
    private String createTime;
    private PageQuery pageQuery = new PageQuery();
}
