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
 * @Description 图片中人脸信息载体，坐标已左上角的点为 0，类似CSS中left，right，top，bottom，绘画出一个人脸的矩形框
 */
public class Rect {
	/**
	 * 距离左边的距离
	 */
	public int left;
	/**
	 * 距离右边的距离
	 */
	public int right;
	/**
	 * 具离上边的距离
	 */
	public int top;
	/**
	 * 具离下边的距离
	 */
	public int bottom;
	/**
	 * 角度信息
	 */
	public int orient;

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getOrient() {
		return orient;
	}

	public void setOrient(int orient) {
		this.orient = orient;
	}
}
