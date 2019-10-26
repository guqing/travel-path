package xyz.guqing.travelpath.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import xyz.guqing.travelpath.entity.dto.MailOption;

import java.util.Properties;

/**
 * 发送邮件的工具类，通过从数据库查询配置信息构建JavaMailSender<br>
 *
 * @author guqing
 * @date 2019-10-26 22:05
 */
public class SendMailHelper {
    /**
     * Spring的邮件工具类，实现了MailSender和JavaMailSender接口
     */
    private JavaMailSenderImpl mailSender;

    public SendMailHelper(MailOption mailOptions) {
        initializeConfiguration(mailOptions);
    }

    /**
     * 初始化邮件发送配置
     * @param mailOptions 邮件发送配置参数
     */
    private void initializeConfiguration(MailOption mailOptions) {
        //创建邮件发送服务器
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailOptions.getHost());
         mailSender.setPort(mailOptions.getPort());
        mailSender.setUsername(mailOptions.getUsername());
        mailSender.setPassword(mailOptions.getPassword());
        //加认证机制
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", mailOptions.isAuth());
        javaMailProperties.put("mail.smtp.starttls.enable", mailOptions.isTlsEnable());
        javaMailProperties.put("mail.smtp.timeout", mailOptions.getTimeout());
        mailSender.setJavaMailProperties(javaMailProperties);
    }

    /**
     * 获取创建好的的mailSender
     * @return 返回通过自定义配置创建好的maiLSender
     */
    public JavaMailSender getMailSender() {
        return this.mailSender;
    }
}
