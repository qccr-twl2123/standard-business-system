package com.business.system.util;

/**
 * 常量工具类
 *
 * @author wujing
 */
public final class Constants {

	private Constants() {
	}

	/**
	 * 常量
	 *
	 * @author wujing
	 */
	public interface BeanSuffix {
		public final static String CARD = "_card";
		public final static String TELEPHONE = "_telephone";
		public final static String FLOW = "_flow";
	}

	/**
	 * 常量
	 *
	 * @author wujing
	 */
	public interface Session {
		public final static String USER_TYPE = "roncoo_user_type";
		public final static String USER_ID = "roncoo_user_info_id";
		public final static String USER = "roncoo_user";
		public final static String MENU = "roncoo_menu";
	}

	/**
	 * 云服务商
	 */
	public enum CloudService {
		/**
		 * 七牛云
		 */
		QINIU(1),
		/**
		 * 阿里云
		 */
		ALIYUN(2),
		/**
		 * 腾讯云
		 */
		QCLOUD(3);

		private int value;

		CloudService(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

}
