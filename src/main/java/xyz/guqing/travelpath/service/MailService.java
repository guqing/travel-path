package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import xyz.guqing.travelpath.entity.dto.MailOption;
import xyz.guqing.travelpath.entity.model.Optional;
import xyz.guqing.travelpath.entity.support.MailOptionConvert;
import xyz.guqing.travelpath.exception.MailException;
import xyz.guqing.travelpath.mapper.MailOptionMapper;
import xyz.guqing.travelpath.utils.SendMailHelper;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;

/**
 * 电子邮件service<br>
 *
 * @author guqin
 * @date 2019-10-26 21:26
 */
@Service
public class MailService {
    private final TemplateEngine templateEngine;
    private final MailOptionConvert mailOptionConvert;
    private String mailFrom;
    @Autowired
    public MailService(MailOptionConvert mailOptionConvert,
                       TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.mailOptionConvert = mailOptionConvert;
    }

    /**
     * 通过查询数据库获取邮件参数构造JavaMailSender对象
     * @return 返回JavaMailSender对象
     */
    public JavaMailSender getMailSender() {
        MailOption mailOption = mailOptionConvert.getMailOption();
        mailFrom = mailOption.getUsername();
        SendMailHelper sendMailHelper = new SendMailHelper(mailOption);
        return sendMailHelper.getMailSender();
    }

    /**
     * 异步发送简单邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 邮件内容
     */
    @Async
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        JavaMailSender mailSender = getMailSender();
        try {
            //发件人
            mailMessage.setFrom(mailFrom);

            //收件人
            mailMessage.setTo(to);
            //标题
            mailMessage.setSubject(subject);
            //文本
            mailMessage.setText(content);

            mailSender.send(mailMessage);
        } catch (Exception e) {
            throw new MailException(e);
        }
    }

    /**
     * 通常我们使用邮件发送服务的时候，都会有一些固定的场景，比如重置密码、注册确认等,
     * 给每个用户发送的内容可能只有小部分是变化的。所以，很多时候我们会使用模板引擎来
     * 为各类邮件设置成模板，这样我们只需要在发送时去替换变化部分的参数即可。
     * 这个时候我们就需要使用MimeMessage来设置复杂一些的邮件内容
     */
    @Async
    public void sendTemplateMail(String to, String subject, String tempContext) {
        JavaMailSender mailSender = getMailSender();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            System.out.println("mailFrom:" + mailFrom);
            helper.setFrom(mailFrom);
            helper.setTo(to);
            helper.setSubject(subject);

            // 通过模板发送html邮件
            helper.setText(tempContext, true);

            // 发送邮件
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new MailException(e);
        }
    }

    /**
     * 发送注册激活邮件
     * @param userDTO 用户信息
     */
    public void sendRegisterMail(Map<String,Object> userDTO) {
        Context context = new Context();
        //定义模板数据
        context.setVariables(userDTO);
        //获取thymeleaf的html模板
        String tempContext = templateEngine.process("registerTemplate",context);
        Object email = userDTO.get("email");
        sendTemplateMail(email.toString(), "用户注册", tempContext);
    }
}
