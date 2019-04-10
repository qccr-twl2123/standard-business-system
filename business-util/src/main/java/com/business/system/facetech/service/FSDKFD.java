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
package com.business.system.facetech.service;

import org.apache.log4j.Logger;

import com.business.system.facetech.jni.FDJNIFactory;
import com.business.system.facetech.jni.FDServiceJNI;
import com.business.system.facetech.model.DetectResult;
import com.business.system.facetech.model.ImageInfo;
import com.business.system.facetech.model.Rect;

/**
 * @Description FD SDK相关对外方法类，此类中获取的相关引擎数据为引擎池中的对象
 *
 */
public class FSDKFD {
	private static Logger log = Logger.getLogger(FSDKFD.class);

	/**
	 * 根据图片返回检测到的人脸信息列表
	 * @param image  - 图片流
	 * @return
	 */
	public static DetectResult faceDetect(ImageInfo image) {
		DetectResult result = new DetectResult();
		try {
			// 获取FD引擎
			FDServiceJNI jni = FDJNIFactory.borrowObject();
			//根据图片数据获取图片中的人脸信息列表     默认检测出最多的人脸请看JNI封装中的数值
			result = jni.detectFace(image.rgbData, image.width, image.height);
			//将放回结果添加到result中
			FDJNIFactory.returnObject(jni);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getStackTrace());
			result.setErrorcode(0);
			result.setErrormsg("提取人脸信息错误");
		}
		return result;
	}

	/**
	 * 获取图片中最大的人脸信息数据，返回的是一个人脸信息数据
	 * @param
	 * @return
	 */
	public static DetectResult maxFaceDetect(ImageInfo image) {
		//图片中所有的人脸信息
		DetectResult fdResult = faceDetect(image);
		//返回信息的载体
		DetectResult result = new DetectResult();
		//设置返回状态码和信息
		result.setErrorcode(fdResult.getErrorcode());
		result.setErrormsg(fdResult.getErrormsg());
		//设置最大人脸载体
		Rect [] rect = new Rect[1];
		//get max face rect
		if (fdResult.getNface() >= 1) {
			//从多个人脸信息中获取最大的人脸信息
			int maxwidth = 0;
			int maxheight = 0;
			int maxindex = 0;
			Rect[] facerects = fdResult.getRcface();
			for (int i = 0; i < fdResult.getNface(); i++) {
				Rect tmprect = facerects[i];
				int tmpwidth = tmprect.right - tmprect.left;
				int tmpheight = tmprect.bottom - tmprect.top;
				maxwidth = Math.max(maxwidth, tmpwidth);
				maxheight = Math.max(maxheight, tmpheight);
				if (maxwidth == tmpwidth || maxheight == tmpheight) {
					maxindex = i;
				}
			}
			rect[0] = new Rect();
			rect[0].left = facerects[maxindex].left;
			rect[0].right = facerects[maxindex].right;
			rect[0].top = facerects[maxindex].top;
			rect[0].bottom = facerects[maxindex].bottom;
			rect[0].orient = facerects[maxindex].orient;
			result.setNface(1);
			result.setRcface(rect);
		} else {
			result.setNface(0);
		}
		return result;
	}
}
