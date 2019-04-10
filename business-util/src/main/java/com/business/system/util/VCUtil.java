package com.business.system.util;


import cn.hutool.crypto.digest.DigestUtil;

/**
 * @author wujing
 */
public final class VCUtil {

	private final static String KEY = "RONCOO";
	private final static String SPLIT = "_";

	private VCUtil() {

	}

	public static Boolean checkVC(String vc, Long totalBalance, Long lastTradeBalance) {
		if (vc.equals(getVC(totalBalance, lastTradeBalance))) {
			return true;
		}
		return false;
	}

	public static String getVC(Long totalBalance, Long lastTradeBalance) {
		String sourceString = KEY + (totalBalance == null ? 0 : totalBalance) + SPLIT + (lastTradeBalance == null ? 0 : lastTradeBalance) + SPLIT;
		return DigestUtil.md5Hex(sourceString);
	}
}
