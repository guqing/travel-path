package xyz.guqing.travelpath.model.dto;

import lombok.Data;
import xyz.guqing.travelpath.model.entity.Role;
import xyz.guqing.travelpath.model.support.OutputConverter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author guqing
 * @date 2020-06-04
 */
@Data
public class RoleDTO implements OutputConverter<RoleDTO, Role> {
    private Long id;
    private String roleName;
    private String remark;
    private LocalDateTime createTime;
    private Set<Long> menuIds;
}
