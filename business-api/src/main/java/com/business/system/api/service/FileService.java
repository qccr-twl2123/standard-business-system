package com.business.system.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FileService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public byte[] getBytes(MultipartFile multipartFile){
        byte[] reffea = null;
        try {
            reffea = multipartFile.getBytes();
        } catch (IOException e) {
            logger.error("read feature byte:" + e.getStackTrace());
        }
        return reffea;
    }

}
