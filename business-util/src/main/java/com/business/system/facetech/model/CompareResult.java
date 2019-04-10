package com.business.system.facetech.model;


/**
 * @Description 两张图片比较结果
 */
public class CompareResult extends Result implements Comparable<CompareResult>{
	/**
	 * 比较结果
	 */
	public float compareResult;
	/**
	 * 比较坐标1
	 */
	public Rect referrect;
	/**
	 * 比较坐标2
	 */
	public Rect proberect;

	public float getCompareresult() {
		return compareResult;
	}

	public void setCompareresult(float compareResult) {
		this.compareResult = compareResult;
	}

	@Override
	public int compareTo(CompareResult o) {
		// TODO Auto-generated method stub
		if(this.compareResult > o.compareResult) {
			return -1;
		} else if (this.compareResult < o.compareResult) {
			return 1;
		} else {
			return 0;
		}

	}

}
