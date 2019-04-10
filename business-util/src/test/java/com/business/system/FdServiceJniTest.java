package com.business.system;

import com.alibaba.fastjson.JSON;
import com.business.system.facetech.jni.FDJNIFactory;
import com.business.system.facetech.jni.FDServiceJNI;
import com.business.system.facetech.jni.FRJNIFactory;
import com.business.system.facetech.jni.FRServiceJNI;
import com.business.system.facetech.model.DetectResult;
import com.business.system.facetech.model.ExtractResult;
import com.business.system.facetech.model.ImageInfo;
import com.business.system.facetech.model.Rect;
import com.business.system.util.ImageFileUtil;
import org.springframework.util.ResourceUtils;
import java.io.File;

public class FdServiceJniTest {

    public static void main(String[] args) {
        detectFace();
    }

    public static void detectFace(){
        try {
            File file = ResourceUtils.getFile("classpath:m2.jpg");
            ImageInfo info = new ImageInfo();
            info = ImageFileUtil.getRGBData(file);
            if(info != null) {
                FDServiceJNI fdServiceJNI = FDJNIFactory.borrowObject();
                DetectResult detectResult = fdServiceJNI.detectFace(info.rgbData,info.width, info.height);
                Rect rect = detectResult.getRcface()[0];
                FDJNIFactory.returnObject(fdServiceJNI);
                System.out.println("result:"+JSON.toJSONString(detectResult));

                FRServiceJNI frServiceJNI = FRJNIFactory.borrowObject();
                ExtractResult extractResult = frServiceJNI.extractFeature(info.rgbData,rect,info.width, info.height);
                System.out.println("feature:"+JSON.toJSONString(extractResult));
                System.out.println(String.valueOf(extractResult.getFeature()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
