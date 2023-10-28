package com.takeout.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class UploadUtil {
    public static String ALI_DOMAIN = "https://buckchtest.oss-cn-hangzhou.aliyuncs.com/";
    public static String END_POINT = "https://oss-cn-hangzhou.aliyuncs.com";
    public static String ASSESSKEY_ID = "LTAI5tDmoFuwoNe2R7JnD4DY";
    public static String ASSESSKEY_SECRET = "Wm0Nmuruo4OVaRhXVYx6FJaRX4gUG6";

    public static String uploadImage(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String suffix = "." + FilenameUtils.getExtension(filename);
        String newFilename = UUID.randomUUID().toString().replace("-", "") + suffix;

        OSS ossClient = new OSSClientBuilder().build(END_POINT, ASSESSKEY_ID, ASSESSKEY_SECRET);
        ossClient.putObject("buckchtest", newFilename, file.getInputStream());
        ossClient.shutdown();
        return ALI_DOMAIN + newFilename;
    }


}
