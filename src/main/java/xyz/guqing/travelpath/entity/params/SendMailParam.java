package xyz.guqing.travelpath.entity.params;

import lombok.Data;

/**
 * 发送邮件参数<br>
 *
 * @author guqin
 * @date 2019-11-02 14:40
 */
@Data
public class SendMailParam {
    /**
     * 收件人邮箱地址
     */
    private String to;
    /**
     * 主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
}
