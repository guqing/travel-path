package xyz.guqing.travelpath.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import xyz.guqing.travelpath.entity.properties.SystemProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 附件上传Service<br>
 *
 * @author guqing
 * @date 2019-10-19 15:40
 */
@Service
public class AttachmentService {
    private static final Logger logger = LoggerFactory.getLogger(AttachmentService.class);
    private final static String UPLOAD_SUB_DIR = "upload/";
    private final SystemProperties myProperties;

    @Autowired
    public AttachmentService(SystemProperties myProperties) {
        this.myProperties = myProperties;
    }

    public String uploadFile(MultipartFile file) {
        Assert.notNull(file, "Multipart file must not be null");

        String filename = file.getOriginalFilename();

        Assert.notNull(filename, "file name can not be null");
        // 保存文件到本地
        String relativePath = getRelativePath(filename);
        try {
            File pathFile = getPathFile(relativePath);
            file.transferTo(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("上传附件图片失败，附件名称:{}, 附件类型:{}", filename, file.getContentType());
        }

        return relativePath;
    }

    private File getPathFile(@NonNull String relativePath) throws IOException {
        String path = myProperties.getWorkDir() + relativePath;

        Path target = Paths.get(path);
        if(!Files.exists(target)) {
            Files.createDirectories(target);
        }
        // 构造新的保存目录
        return target.toFile();
    }

    /**
     * 根据原始文件名生成hash打散后存储到磁盘的相对路径
     * @param originalFilename 原始文件名
     * @return 存储到磁盘的相对路径,完成路径=workDir + relativePath
     */
    private String getRelativePath(@NonNull String originalFilename) {
        int hashcode = originalFilename.hashCode();
        // 0--15
        int dir1 = hashcode & 0xf;
        // 0-15
        int dir2 = (hashcode & 0xf0) >> 4;

        // 拼接文件保存基目录
        int startIndex = originalFilename.lastIndexOf(".");
        String fileSuffix = originalFilename.substring(startIndex, originalFilename.length());
        String localFileName = System.currentTimeMillis() + fileSuffix;
        return UPLOAD_SUB_DIR + dir1 + "/" + dir2 + "/" + localFileName;
    }
}
