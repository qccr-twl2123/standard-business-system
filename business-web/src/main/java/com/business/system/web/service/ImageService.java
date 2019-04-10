package com.business.system.web.service;

import com.business.system.common.oss.OSSFactory;
import com.business.system.util.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @Auther: xierongli
 * @Date: 2019-03-27 10:47
 * @Description: 图片服务
 */
@Component
public class ImageService {

    public String  uploadImage( String imageUrl){
        String url = null;
        try {
            InputStream input = new URL(imageUrl).openStream();
            url = OSSFactory.build(Constants.CloudService.ALIYUN.getValue()).uploadSuffix(input, ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    public String uploadImage(MultipartFile multipartFile){
        String url = null;
        try {
            url = OSSFactory.build(Constants.CloudService.ALIYUN.getValue()).uploadSuffix(multipartFile.getInputStream(), ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
