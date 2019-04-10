package com.arcsoft.facego.util.enums;

import lombok.Getter;

@Getter
public enum ChannelEnum {

	API(1, "API"), WEB(2, "WEB"), APP(3, "APP");

	private Integer code;

	private String desc;

	private ChannelEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	// 获取desc
	public static String getDesc(Integer code) {
		for (ChannelEnum em : ChannelEnum.values()) {
			if (em.getCode().equals(code)) {
				return em.getDesc();
			}
		}
		return "";
	}

}
