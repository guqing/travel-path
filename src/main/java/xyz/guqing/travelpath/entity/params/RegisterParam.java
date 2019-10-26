package xyz.guqing.travelpath.entity.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 用户注册表单参数<br>
 *
 * @author guqing
 * @date 2019-10-26 17:31
 */
@Data
public class RegisterParam {
    @NotBlank(message="用户名不能为空")
    private String username;

    @Size(max=16, min=6, message="密码长度必须在6-16字符之间")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\\\.[a-zA-Z0-9_-]+)+$", message = "邮箱地址格式不正确")
    private String email;
}
