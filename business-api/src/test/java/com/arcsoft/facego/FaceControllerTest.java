package com.arcsoft.facego;

import com.arcsoft.facego.api.controller.FaceController;
import com.arcsoft.facego.api.model.req.CompareFeatureReq;
import com.arcsoft.facego.api.service.PersonImageService;
import com.arcsoft.facego.common.entity.PersonImage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaceControllerTest {

    @Autowired
    private FaceController faceController;

    @Test
    public void compareFeature(){
        try {
            File faceImage = ResourceUtils.getFile("classpath:m1.png");
            FileInputStream inputStream = new FileInputStream(faceImage);
            MultipartFile multipartFile = new MockMultipartFile(faceImage.getName(), inputStream);
            CompareFeatureReq compareFeatureReq = new CompareFeatureReq();
            compareFeatureReq.setFaceFile(multipartFile);
            compareFeatureReq.setDoorCode("c1");
            compareFeatureReq.setSimilar(0.8);
            compareFeatureReq.setTopN(10);
            faceController.compareFeature(compareFeatureReq);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
