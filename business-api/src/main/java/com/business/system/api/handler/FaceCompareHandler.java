package com.business.system.api.handler;

import com.business.system.facetech.jni.FRJNIFactory;
import com.business.system.facetech.jni.FRServiceJNI;
import com.business.system.facetech.model.CompareResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class FaceCompareHandler implements Runnable{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private byte[] target;
    private byte[] source;
    private CountDownLatch countDownLatch;
    private List<Float> similarList;

    public FaceCompareHandler(byte[] target, byte[] source, CountDownLatch countDownLatch, List<Float> similarList) {
        this.target = target;
        this.source = source;
        this.countDownLatch = countDownLatch;
        this.similarList = similarList;
    }

    @Override
    public void run() {
        FRServiceJNI frServiceJNI;
        try{
            frServiceJNI = FRJNIFactory.borrowObject();
            CompareResult compareResult = frServiceJNI.compareFeatures(target, source);
            if(compareResult != null){
                similarList.add(compareResult.getCompareresult());
            }
            FRJNIFactory.returnObject(frServiceJNI);
            countDownLatch.countDown();
        }catch (Exception e){
            logger.error("frServiceJNI compare exception",e);
        }
    }
}
