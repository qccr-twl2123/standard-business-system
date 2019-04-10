package com.business.system.util;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.Random;


public final class NOUtil {

	private NOUtil() {
	}

	public static Long getTradeNo() {
		return Long.valueOf(DateUtil.format(new Date(), "yyyyMMddHHmmss") + getRandom(5));
	}

	private static String getRandom(int bound) {
		Random ra = new Random();
		String result = "";
		for (int i = 1; i <= bound; i++) {
			result += ra.nextInt(10);
		}
		return result;
	}

}
