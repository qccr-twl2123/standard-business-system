package com.business.system.util.base;

import java.io.DataInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.json.JSONUtil;
import com.business.system.util.bjui.Bjui;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 控制基础类，所以controller都应该继承这个类
 *
 * @author wujing
 */
public class BaseController extends BaseRoncoo {

	public static final String TEXT_UTF8 = "text/html;charset=UTF-8";
	public static final String JSON_UTF8 = "application/json;charset=UTF-8";
	public static final String XML_UTF8 = "application/xml;charset=UTF-8";

	@InitBinder
	protected void initBinder(HttpServletRequest request,
							  ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	public static TreeMap<String, Object> getParamMap(HttpServletRequest request) {
		TreeMap<String, Object> paramMap = new TreeMap<>();
		Map<String, String[]> map = request.getParameterMap();
		for (String key : map.keySet()) {
			paramMap.put(key, map.get(key)[0]);
		}
		if (paramMap.isEmpty()) {
			return new TreeMap<>(JSONUtil.parseObj(getString(request)));
		}
		return paramMap;
	}

	private static String getString(HttpServletRequest request) {
		DataInputStream in = null;
		try {
			in = new DataInputStream(request.getInputStream());
			byte[] buf = new byte[request.getContentLength()];
			in.readFully(buf);

			return new String(buf, "UTF-8");
		} catch (Exception e) {
			return "";
		} finally {
			if (null != in)
				try {
					in.close();// 关闭数据流
				} catch (IOException e) {
				}
		}
	}

	public static String redirect(String format, Object... arguments) {
		return new StringBuffer("redirect:").append(MessageFormat.format(format, arguments)).toString();
	}

	/**
	 * 成功提示，关闭当前对话框，并对tabid进行刷新
	 *
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 *
	 * @return json字符串
	 */
	protected static String success(String targetId) {
		return bjui(Bjui.OK, Bjui.CLOSE, targetId, "操作成功");
	}

	protected static Bjui successObject(String targetId) {
		return bjuiObject(Bjui.OK, Bjui.CLOSE, targetId, "操作成功");
	}
	/**
	 * 成功提示，关闭当前对话框，并对tabid进行刷新
	 *
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 *
	 * @return json字符串
	 */
	protected static String success(String targetId, String message) {
		return bjui(Bjui.OK, Bjui.CLOSE, targetId, message);
	}

	/**
	 * 删除成功提示，不关闭当前对话框
	 *
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @return
	 */
	protected static String delete(String targetId) {
		return delete(targetId, "操作成功");
	}


	/**
	 * 删除成功提示，不关闭当前对话框
	 *
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @return
	 */
	protected static Bjui deleteObject(String targetId) {
		return deleteObject(targetId, "操作成功");
	}

	/**
	 * 删除成功提示，不关闭当前对话框
	 *
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static String delete(String targetId, String message) {
		return bjui(Bjui.OK, Bjui.OPEN, targetId, message);
	}



	/**
	 * 删除成功提示，不关闭当前对话框
	 *
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static Bjui deleteObject(String targetId, String message) {
		return bjuiObject(Bjui.OK, Bjui.OPEN, targetId, message);
	}



	/**
	 * 信息提示，不关闭当前对话框
	 *
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static String ties(String message) {
		return bjui(Bjui.OK, Bjui.OPEN, "", message);
	}

	protected static String ties(String targetId, String message) {
		return bjui(Bjui.OK, Bjui.OPEN, targetId, message);
	}

	/**
	 * 错误提示，不关闭当前对话框，自定义提示信息
	 *
	 * @param message
	 *            提示信息
	 * @return
	 */
	protected static String error(String message) {
		return bjui(Bjui.ER, Bjui.OPEN, "", message);
	}

	protected static Bjui errorObject(String message) {
		return bjuiObject(Bjui.ER, Bjui.OPEN, "", message);
	}

	/**
	 * 删除成功提示，不关闭当前对话框
	 *
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * @return
	 */
	private static String bjui(int statusCode, Boolean closeCurrent, String targetId, String message) {
		Bjui bj = new Bjui();
		bj.setStatusCode(statusCode);
		bj.setCloseCurrent(closeCurrent);
		bj.setTabid(targetId);
		bj.setMessage(message);
		return JSONUtil.toJsonStr(bj);
	}


	/**
	 * 删除成功提示，不关闭当前对话框
	 *
	 * @param tabid
	 *            需要刷新的tabid或者dialogid
	 * @param message
	 *            提示信息
	 * @return
	 */
	private static Bjui bjuiObject(int statusCode, Boolean closeCurrent, String targetId, String message) {
		Bjui bj = new Bjui();
		bj.setStatusCode(statusCode);
		bj.setCloseCurrent(closeCurrent);
		bj.setTabid(targetId);
		bj.setMessage(message);
		return bj;
	}
}
