package xyz.guqing.travelpath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.guqing.travelpath.service.AttachmentService;
import xyz.guqing.travelpath.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 附件相关的controller<br>
 *
 * @author guqing
 * @date 2019-10-19 15:19
 */
@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    private AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/uploadImage")
    public Object uploadImage(MultipartFile file, HttpServletRequest request) {
        String fileUrl = attachmentService.uploadFile(file);

        // 拼接成网络地址
        String attachmentUrl =  getServerPath(request) + fileUrl;
        return Result.ok(attachmentUrl);
    }

    private String getServerPath(HttpServletRequest request) {
        // 静态资源端口号8082
        return request.getScheme() + "://"+request.getServerName()+":" +
                "8082" + request.getContextPath() + "/";
    }
}
