package com.business.system.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {




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

	public static final String ALIYUN_DOMAIN = getProperty("aliyun.domain");
	public static final String ALIYUN_PREFIX = getProperty("aliyun.prefix");
	public static final String ALIYUN_END_POINT = getProperty("aliyun.end.point");
	public static final String ALIYUN_ACCESS_KEY_ID = getProperty("aliyun.access.key.id");
	public static final String ALIYUN_ACCESS_SECRET = getProperty("aliyun.access.key.secret");
	public static final String ALIYUN_BUCKET_NAME= getProperty("aliyun.bucket.name");









}
