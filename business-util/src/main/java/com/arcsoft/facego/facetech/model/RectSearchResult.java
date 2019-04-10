package com.arcsoft.facego.facetech.model;


/**
 * @Description 搜索返回的人脸坐标信息载体
 */
public class RectSearchResult  {
	/**
	 * 人脸坐标信息
	 */
	private Rect faceRect;

	public Rect getFaceRect() {
		return faceRect;
	}

	public void setFaceRect(Rect faceRect) {
		this.faceRect = faceRect;
	}

}
