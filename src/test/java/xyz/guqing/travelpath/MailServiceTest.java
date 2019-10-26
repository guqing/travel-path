package xyz.guqing.travelpath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.guqing.travelpath.service.MailService;

/**
 * 邮件service测试类<br>
 *
 * @author guqin
 * @date 2019-10-26 23:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    public void testGetMailSender() {
        JavaMailSender mailSender = mailService.getMailSender();
        System.out.println(mailSender);
    }
}
