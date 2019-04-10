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

package com.arcsoft.facego.facetech.model;

/**
 * @Description 图片信息载体
 */
public class ImageInfo {
	/**
	 * 图片对应的RGB数据
	 */
	public byte[] rgbData;
	/**
	 * 图片宽
	 */
	public int width;
	/**
	 * 图片高
	 */
	public int height;

	

	public byte[] getRgbData() {
		return rgbData;
	}

	public void setRgbData(byte[] rgbData) {
		this.rgbData = rgbData;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
