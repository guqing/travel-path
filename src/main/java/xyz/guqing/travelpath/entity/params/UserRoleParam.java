package xyz.guqing.travelpath.entity.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author guqing
 * @date 2019-12-6
 */
@Data
public class UserRoleParam {
    private Integer id;

    @NotNull
    private Integer roleId;

    @NotBlank(message="角色名称不能为空")
    private String roleName;

    private Integer status;
}
