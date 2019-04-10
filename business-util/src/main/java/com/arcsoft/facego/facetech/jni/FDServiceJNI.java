package com.arcsoft.facego.facetech.jni;
import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.arcsoft.facego.facetech.config.ConstantErrorDef;
import com.arcsoft.facego.facetech.model.DetectResult;
import com.arcsoft.facego.util.ConfigUtil;
import org.apache.log4j.Logger;
import com.arcsoft.facego.facetech.model.InitResult;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FDServiceJNI {
    private final static Logger log = Logger.getLogger(FDServiceJNI.class);
    private long engineAddress;
    private long memBuffAddress;
    private ObjectMapper mapper = new ObjectMapper();

    static {
		String os = System.getProperty("os.name");
		if(os.toLowerCase().startsWith("win")) {
			System.loadLibrary("FSDKFDServiceJNI");
		} else {
			System.loadLibrary("fdjni");
		}
	}
    public FDServiceJNI() {
        this(ConfigUtil.getProperty("fdappid"), ConfigUtil.getProperty("fdsdkkey"),46,
                5, 16,
                5);
        mapper.setSerializationInclusion(Include.NON_EMPTY);
    }

	private native String FSDK_FD_GetVersion();
	private native String FSDK_FD_InitEngine(String appId, String sdkKey, int memSize, int orientPriority, int scanle, int maxFaceNum);
    private native long FSDK_FD_FaceDetect(long engineaddr, byte[] imagedata, int width, int height,
                                           DetectResult result);
    private native int FSDK_FD_UninitEngine(long engineAddress, long memBuffAddress);

    public String FSDK_FD_GetVersion_Ext() {
        return FSDK_FD_GetVersion();
    }
	public FDServiceJNI(String appID, String sdkKey, int memSize, int orientPriority, int nscanle, int maxFaceNum) {
		log.debug("Init Params: " + appID + "," + sdkKey + "," + memSize + "," + orientPriority + ", " + nscanle + "," + maxFaceNum);
		String result = FSDK_FD_InitEngine(appID, sdkKey, memSize, orientPriority, nscanle, maxFaceNum);
		try {
			InitResult initResult = mapper.readValue(result, InitResult.class);
			this.engineAddress = initResult.getEngineaddr();
			this.memBuffAddress = initResult.getMembufaddr();
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}


	public DetectResult detectFace(byte[] imageData, int width, int height) {
		DetectResult detectRes = new DetectResult();
		if (engineAddress == 0) {
			detectRes.setErrorcode(0);
			detectRes.setErrormsg("未找到引擎");
			return detectRes;
		}
        DetectResult detectResult = new DetectResult();
		if (engineAddress == 0){
			detectResult.errorcode = ConstantErrorDef.ERROR_ENGINEHANDLE_NULL;
			detectResult.errormsg = "engine not found";
			return detectResult;
		}
		FSDK_FD_FaceDetect(engineAddress, imageData, width, height, detectResult);
        System.out.println(JSON.toJSONString(detectResult));
		return detectResult;
	}
	private int FSDK_FD_UninitEngine() {
		if (engineAddress == 0) {
			return ConstantErrorDef.ERROR_ENGINEHANDLE_NULL;
		}
		int result = FSDK_FD_UninitEngine(engineAddress, memBuffAddress);
		return result;
	}

}
