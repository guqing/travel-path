package xyz.guqing.travelpath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import xyz.guqing.travelpath.exception.MailException;

import javax.mail.internet.MimeMessage;

/**
 * 电子邮件service<br>
 *
 * @author guqin
 * @date 2019-10-26 21:26
 */
@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMailForRegister(String from, String to, String subject, String message) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //发件人
            helper.setFrom(from);

            //收件人
            helper.setTo(to);
            //标题
            helper.setSubject(subject);
            //文本
            helper.setText(message);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new MailException(e);
        }
    }
}
