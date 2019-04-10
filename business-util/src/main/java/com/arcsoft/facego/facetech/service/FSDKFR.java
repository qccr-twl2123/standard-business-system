/*
 * Copyright 2018 ArcSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.arcsoft.facego.facetech.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;

import com.arcsoft.facego.facetech.jni.FRJNIFactory;
import com.arcsoft.facego.facetech.jni.FRServiceJNI;
import com.arcsoft.facego.facetech.model.CompareResult;
import com.arcsoft.facego.facetech.model.ExtractResult;
import com.arcsoft.facego.facetech.model.Rect;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @Description FR SDK 对外相关方法类，此类中获取的相关引擎数据为引擎池中的对象
 * 
 */
public class FSDKFR {

	static Logger log = Logger.getLogger(FSDKFR.class);
	static BASE64Decoder decoder = new BASE64Decoder();
	static BASE64Encoder encoder = new BASE64Encoder();

	/**
	 * 比较两个图片中的人脸相似度
	 * 
	 * @param reffeatrure
	 *            - 第一个图片
	 * @param probefeature
	 *            - 第二个图片
	 * @return
	 */
	public static CompareResult faceCompareWithFeature(byte[] reffeatrure, byte[] probefeature) {
		// fr matchpair
		CompareResult result = new CompareResult();
		try {
			FRServiceJNI jni = FRJNIFactory.borrowObject();
			if (reffeatrure.length == 0 || probefeature.length == 0) {
				result.setErrorcode(0);
				result.setErrormsg("比较失败，比较的feature为空.");
				return result;
			}
			result = jni.compareFeatures(reffeatrure, probefeature);
			FRJNIFactory.returnObject(jni);
		} catch (Exception e) {
			log.error("FSDKFR-faceCompareWithFeature:"+e.getStackTrace());
			result.setErrorcode(0);
			result.setErrormsg("比较时发生错误，比较失败");
		}

		return result;
	}

	/**
	 * 比较两个图片中的人脸相似度
	 *
	 * @return
	 */
	public static CompareResult faceCompareWithFeature(ArrayList<byte[]> reffeature, byte[] probefeature) {
		// fr matchpair
		CompareResult result = new CompareResult();
		try {
			ArrayList<CompareResult> scores = new ArrayList<>();
			// 得到引擎对象
			FRServiceJNI jni = FRJNIFactory.borrowObject();
			for (int i = 0; i < reffeature.size(); i++) {
				result = jni.compareFeatures(reffeature.get(i), probefeature);
				scores.add(result);
			}
			FRJNIFactory.returnObject(jni);
			// 将比较后的结果进行降序排列
			Collections.sort(scores);
			// 返回第一个数据
			result = scores.get(0);
		} catch (Exception e) {
			log.error("FSDKFR-faceCompareWithFeature:"+e.getStackTrace());
			result.setErrorcode(0);
			result.setErrormsg("比较时发生错误，比较失败");
		}

		return result;
	}

	/**
	 * 比较两个图片中的人脸相似度
	 * 
	 * @param reffeatrure
	 *            - 图片数据
	 * @param probefeature
	 *            - 图片数据
	 * @return CompareResult - 比较结果
	 */
	public static CompareResult faceCompareWithFeature(byte[] reffeatrure, String probefeature) {
		// fr matchpair
		byte[] profea = null;
		try {
			profea = decoder.decodeBuffer(probefeature);
		} catch (IOException e) {
			log.error(e.getStackTrace());
		}
		CompareResult result = new CompareResult();
		try {
			FRServiceJNI jni = FRJNIFactory.borrowObject();
			result = jni.compareFeatures(reffeatrure, profea);
			FRJNIFactory.returnObject(jni);
		} catch (Exception e) {
			log.error("FSDKFR-faceCompareWithFeature:"+e.getStackTrace());
			result.setErrorcode(0);
			result.setErrormsg("比较时发生错误，比较失败");
		}
		return result;
	}

	/**
	 * 根据图片和坐标提取Feature
	 * 
	 * @param image
	 *            - 图片数据
	 * @param rect
	 *            - 人脸坐标
	 * @param width
	 *            - 图片宽度
	 * @param height
	 *            - 图片高度
	 * @return 图片提取结果
	 */
	public static ExtractResult extractFeature(byte[] image, Rect rect, int width, int height) {
		ExtractResult result = new ExtractResult();
		try {
			// 获取FR引擎对象
			FRServiceJNI jni = FRJNIFactory.borrowObject();
			// 提取Feature
			result = jni.extractFeature(image, rect, width, height);
			FRJNIFactory.returnObject(jni);
		} catch (Exception e) {
			log.error("FSDKFR-extractFeature:"+e.getStackTrace());
			result.setErrorcode(0);
			result.setErrormsg("提取feature失败");
		}
		return result;
	}

}
