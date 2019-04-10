package com.arcsoft.facego.facetech.model;


/**
 * @Description 提取feature数据载体
 */
public class ExtractResult extends Result {
	/**
	 * 花费的时间
	 */
	public long costtime;
	/**
	 * 特征值
	 */
	public byte[] feature;
	/**
	 * feature大小
	 */
	public int featuresize;

	public void setCosttime(long costtime) {
		this.costtime = costtime;
	}

	public long getCosttime() {
		return costtime;
	}

	public byte[] getFeature() {
		return feature;
	}

	public void setFeature(byte[] feature) {
		this.feature = feature;
	}

	public int getFeaturesize() {
		return featuresize;
	}

	public void setFeaturesize(int featuresize) {
		this.featuresize = featuresize;
	}

}
