package xyz.guqing.travelpath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xyz.guqing.travelpath.entity.dto.MailOption;
import xyz.guqing.travelpath.entity.params.MailOptionsParam;
import xyz.guqing.travelpath.entity.params.SendMailParam;
import xyz.guqing.travelpath.service.MailService;
import xyz.guqing.travelpath.utils.Result;

import javax.validation.Valid;

/**
 * 邮件服务controller<br>
 *
 * @author guqin
 * @date 2019-11-02 13:36
 */
@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/get")
    public Object getMailOptions() {
        MailOption mailOptions = mailService.getMailOptions();
        return Result.ok(mailOptions);
    }

    @PostMapping("/save")
    public Object saveOptions(@RequestBody @Valid MailOptionsParam mailParam, BindingResult result) {
        if(result.hasErrors()) {
            return Result.fail();
        }
        mailService.saveOptions(mailParam);

        return Result.ok();
    }

    @PostMapping("/sendMail")
    public Object sendMail(@RequestBody SendMailParam mailParam) {
        try {
            mailService.sendSimpleMail(mailParam.getTo(), mailParam.getSubject(), mailParam.getSubject());
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            // 这里需要捕获异常，将信息返回给前台，否则不知道发送测试邮件出了什么错误
            return Result.fail(-1, e.getMessage());
        }
    }
}
