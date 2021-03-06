package com.business.system.util.enums;

import lombok.Getter;

@Getter
public enum BillTypeEnum {

	PAY(1, "支付单"), RECHAREGE(2, "充值单"), REFUND(3, "退款单"), TX(4, "提现单");

	private Integer code;

	private String desc;

	private BillTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	// 获取desc
	public static String getDesc(Integer code) {
		for (BillTypeEnum em : BillTypeEnum.values()) {
			if (em.getCode().equals(code)) {
				return em.getDesc();
			}
		}
		return "";
	}

}
