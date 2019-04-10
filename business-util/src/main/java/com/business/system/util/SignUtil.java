package com.business.system.util;

import cn.hutool.crypto.digest.DigestUtil;

import java.util.Map;
import java.util.TreeMap;


public final class SignUtil {

	private SignUtil() {
	}

	public static boolean checkSign(TreeMap<String, Object> map, String AppSeceret) {
		String sign = map.get("sign").toString();
		map.remove("sign");
		map.remove("serialVersionUID");
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> m : map.entrySet()) {
			if (m.getValue() != null) {
				sb.append(m.getKey()).append("=").append(m.getValue().toString()).append("&");
			}
		}
		sb.append(AppSeceret);
		if (sign.equals(DigestUtil.md5Hex(sb.toString()))) {
			return true;
		}
		return false;
	}

	public static String getSign(TreeMap<String, Object> map, String AppSeceret) {
		map.remove("sign");
		map.remove("serialVersionUID");
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> m : map.entrySet()) {
			if (m.getValue() != null) {
				sb.append(m.getKey()).append("=").append(m.getValue().toString()).append("&");
			}
		}
		sb.append(AppSeceret);
		return DigestUtil.md5Hex(sb.toString());
	}

}
