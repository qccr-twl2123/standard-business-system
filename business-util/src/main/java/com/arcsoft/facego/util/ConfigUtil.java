package com.arcsoft.facego.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
	/**
	 * fd appid  请到官网申请  http://www.arcsoft.com.cn/ai/
	 */
	public static String fdAppID = "";
	/**
	 * fd key  请到官网申请  http://www.arcsoft.com.cn/ai/
	 */
	public static String fdSDKKey = "";
	/**
	 * fd  用于数值表示的最小人脸尺寸 有效值范围[2,50] 推荐值 16
	 */
	public static int fdNscanle = 16;
	/**
	 * fd  分配给引擎使用的内存地址  单位：M    建议值：32M以上
	 */
	public static int fdMemSize = 32;
	/**
	 * 期望的脸部检测角度的优先级  推荐值为0，0：为正脸
	 */
	public static int fdOrientPriority = 0;
	/**
	 * fd  用户期望引擎最多能检测出的人脸数 有效值范围[1,100]，如不做特殊处理请用1
	 */
	public static int fdMaxFaceNum = 5;
	/**
	 * fd  引擎池大小，建议值：4
	 */
	public static int fdPoolSize = 0;

	/**
	 * fr appid  请到官网申请  http:/**www.arcsoft.com.cn/ai/
	 */
	public static String frAppID = "";
	/**
	 * fr key  请到官网申请  http:/**www.arcsoft.com.cn/ai/
	 */
	public static String frSDKKey = "";
	/**
	 * fr  分配给引擎使用的内存地址  单位：M    建议值：32M以上
	 */
	public static int frMemSize = 32;
	/**
	 * fr  线程池数量，建议值：20
	 */
	public static int frPoolSize = 0;
	/**
	 * FR 搜索feature线程数，和内核有关，数值<=内核数
	 */
	public static int maxThreadNum = 1;


	/**
	 * 图片存放的目录，后面跟/image
	 */
	public static String imagedir = "";
	/**
	 * 图片删除后要移动到的地址,当前默认为image同级目录
	 */
	public static String imageCanDeleteDir = "imagesCanDelete";
	/**
	 * 存放图片目录的名称  如：/image/  放到的是root下
	 */
	public static String imgurlprefix = "";



	private static final Properties properties = new Properties();
	public ConfigUtil() throws FileNotFoundException {
//		this.imagedir
		File path = new File(ResourceUtils.getURL("classpath:").getPath());
		if(!path.exists()) path = new File("");
		System.out.println("path:"+path.getAbsolutePath());

//如果上传目录为/static/images/upload/，则可以如下获取：
		File upload = new File(path.getAbsolutePath(),"static/images/upload/");
		if(!upload.exists()) upload.mkdirs();
		System.out.println("upload url:"+upload.getAbsolutePath());
	}

	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String keyName) {
		return getProperty(keyName, "");
	}
	public static String getProperty(String keyName, String defaultValue) {
		return properties.getProperty(keyName, defaultValue).trim();
	}









}
