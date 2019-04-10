package com.arcsoft.facego.util.enums;

import lombok.Getter;

@Getter
public enum BillStatusEnum {

	SUCCESS(1, "成功"), EX(2, "异常");

	private Integer code;

	private String desc;

	private BillStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	// 获取desc
	public static String getDesc(Integer code) {
		for (BillStatusEnum em : BillStatusEnum.values()) {
			if (em.getCode().equals(code)) {
				return em.getDesc();
			}
		}
		return "";
	}

}
