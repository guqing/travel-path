package xyz.guqing.travelpath.entity.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 邮箱服务参数<br>
 *
 * @author guqin
 * @date 2019-11-02 14:11
 */
@Data
public class MailOptionsParam {
    @NotBlank(message = "SMPT地址不能为空")
    private String host;
    /**
     * 发件人邮箱的邮箱服务器端口号,默认465
     */
    @NotBlank(message = "SSL端口不能为空")
    private String port;

    /**
     * 发件人的邮箱账号
     */
    @NotBlank(message = "邮箱账号不能为空")
    private String username;

    /**
     * 发件人的邮箱密码
     */
    @NotBlank(message = "邮箱密码不能为空")
    private String password;
}
