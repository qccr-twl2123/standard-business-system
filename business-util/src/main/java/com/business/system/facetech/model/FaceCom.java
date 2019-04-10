/*
 * Copyright 2018 ArcSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http:/**www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.business.system.facetech.model;

/**
 * @Description feature比较返回结果载体
 *
 */
public class FaceCom implements Comparable<Object> {
	/**
	 * faceinfo表中的faceid
	 */
	private int faceId;
	/**
	 * 相似度，最大值为1
	 */
	private float similar;
	/**
	 * 对应的注册图
	 */
	private String currentImgUrl;

	public int getFaceId() {
		return faceId;
	}

	public void setFaceId(int faceId) {
		this.faceId = faceId;
	}

	public float getSimilar() {
		return similar;
	}

	public void setSimilar(float similar) {
		this.similar = similar;
	}

	public String getCurrentImgUrl() {
		return currentImgUrl;
	}

	public void setCurrentImgUrl(String currentImgUrl) {
		this.currentImgUrl = currentImgUrl;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		FaceCom fr = (FaceCom) o;
		if (this.similar > fr.similar) {
			return -1;
		} else if (this.similar < fr.similar) {
			return 1;
		} else {
			return 0;
		}
	}
}
