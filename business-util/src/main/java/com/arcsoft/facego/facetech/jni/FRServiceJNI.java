package com.arcsoft.facego.facetech.jni;

import java.io.IOException;
import com.arcsoft.facego.facetech.config.ConstantErrorDef;
import com.arcsoft.facego.facetech.model.ExtractResult;
import com.arcsoft.facego.util.ConfigUtil;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import com.arcsoft.facego.facetech.model.CompareResult;
import com.arcsoft.facego.facetech.model.InitResult;
import com.arcsoft.facego.facetech.model.Rect;

public class FRServiceJNI {
	private final  static Logger log = Logger.getLogger(FRServiceJNI.class);
	static {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			System.loadLibrary("FSDKFRServiceJNI");
		} else {
			System.loadLibrary("frjni");
		}
	}

	private long engineAddr = 0;
	private long membuffAddr = 0;
	private boolean bIsAlive = false;
	ObjectMapper mapper = new ObjectMapper();

	private native String FSDK_FR_GetVersion(long engineaddr);
	private native String FSDK_FR_InitEngine(String appid, String sdkKey, int memsize);
	private native int FSDK_FR_UninitEngine(long engineaddr, long membuffaddr);
	private native byte[] FSDK_FR_ExtractFeature(long engineaddr, byte[] imagedata, int left, int top, int right, int bottom, int orient, int widht, int height);
	private native float FSDK_FR_PairMaching(long engineaddr, byte[] reffeature, byte[] probefeature);

	public FRServiceJNI() {
		this(ConfigUtil.getProperty("frappid"), ConfigUtil.getProperty("frsdkkey"), Integer.parseInt(ConfigUtil.getProperty("frmemsize")));
	}

	public FRServiceJNI(String appid, String sdkkey, int memsize) {
		String ret = FSDK_FR_InitEngine(appid, sdkkey, memsize);
		try {
			InitResult result = mapper.readValue(ret, InitResult.class);
			mapper.setSerializationInclusion(Inclusion.NON_EMPTY);
			if (result.getErrorcode() == 0) {
				engineAddr = result.getEngineaddr();
				membuffAddr = result.getMembufaddr();
			}
			result = null;
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

	}
	public ExtractResult extractFeature(byte[] imageData, Rect rect, int width, int height) {
		ExtractResult result = new ExtractResult();
		if (engineAddr == 0) {
			result.setErrorcode(ConstantErrorDef.ERROR_ENGINEHANDLE_NULL);
			result.setErrormsg("not found");
			return result;
		}
		result.feature = FSDK_FR_ExtractFeature(engineAddr, imageData, rect.left, rect.top, rect.right, rect.bottom, rect.orient, width, height);

		if (result.feature.length > 0) {
			result.setErrorcode(ConstantErrorDef.SUCCESS_CODE);
		} else {
			result.setErrorcode(ConstantErrorDef.ERROR_FR_EXTRACTFEAUTRE);
			result.setErrormsg("error");
		}

		return result;
	}


	public CompareResult compareFeatures(byte[] refFeature, byte[] probeFeature) {
		CompareResult result = new CompareResult();
		if (engineAddr == 0) {
			result.errorcode = (ConstantErrorDef.ERROR_ENGINEHANDLE_NULL);
			result.errormsg = "Engine handle is null";
			return result;
		}
		result.compareResult = FSDK_FR_PairMaching(engineAddr, refFeature, probeFeature);
		return result;
	}
	@Override
	protected void finalize() {
		if (engineAddr == 0) {
			return;
		}
		int result = FSDK_FR_UninitEngine(engineAddr, membuffAddr);
		if (result == 0) {
			bIsAlive = false;
		}
	}

	public String getVersion() {
		String version = FSDK_FR_GetVersion(engineAddr);
		return version;
	}
}
