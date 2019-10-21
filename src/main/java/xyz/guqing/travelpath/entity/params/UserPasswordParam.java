package xyz.guqing.travelpath.entity.params;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 用户修改密码参数<br>
 *
 * @author guqing
 * @date 2019-10-21 0:01
 */
@Data
public class UserPasswordParam {

    @NotBlank(message="用户id不能为空")
    private Integer id;

    @NotBlank(message="原始密码不能为空")
    private String oldPassword;

    @Size(max=16, min=6, message="密码长度必须在6-16字符之间")
    private String newPassword;
}
