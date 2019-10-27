package xyz.guqing.travelpath.entity.dto;

import lombok.Data;

/**
 * 邮件发送参数类<br>
 *
 * @author guqing
 * @date 2019-10-26 22:09
 */
@Data
public class MailOption {
    /**
     * 发件人邮箱的邮箱服务器地址
     */
    private String host;
    /**
     * 发件人邮箱的邮箱服务器端口号,默认465
     */
    private Integer port = 465;
    /**
     * 发件人的邮箱账号
     */
    private String username;

    /**
     * 发件人的邮箱密码
     */
    private String password;

    /**
     * 邮件发送延迟毫秒数，默认 5 秒
     */
    private Long timeout = 5000L;

    /**
     * 是否需要认证
     */
    private boolean auth = true;

    /**
     * 使用tls加密方式
     */
    private boolean tlsEnable;
}
