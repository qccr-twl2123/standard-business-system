package com.arcsoft.facego;

import com.alibaba.fastjson.JSON;
import com.arcsoft.facego.facetech.jni.FDJNIFactory;
import com.arcsoft.facego.facetech.jni.FDServiceJNI;
import com.arcsoft.facego.facetech.jni.FRJNIFactory;
import com.arcsoft.facego.facetech.jni.FRServiceJNI;
import com.arcsoft.facego.facetech.model.DetectResult;
import com.arcsoft.facego.facetech.model.ExtractResult;
import com.arcsoft.facego.facetech.model.ImageInfo;
import com.arcsoft.facego.facetech.model.Rect;
import com.arcsoft.facego.facetech.model.response.DetectRes;
import com.arcsoft.facego.util.ConfigUtil;
import com.arcsoft.facego.util.ImageFileUtil;
import com.arcsoft.facego.util.InputStreamUtils;
import com.arcsoft.facego.util.base.Result;
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
