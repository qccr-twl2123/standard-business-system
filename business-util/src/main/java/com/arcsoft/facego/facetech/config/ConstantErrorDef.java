/*
 * Copyright 2018 ArcSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.arcsoft.facego.facetech.config;

/**
 * @Description 通用常量类
 * 
 */
public class ConstantErrorDef {

	/**
	 * 通用成功
	 */
	public static final int SUCCESS_CODE = 200;
	/**
	 * 添加信息错误
	 */
	public static final int ERROR_PARAM_ADD = -201;
	/**
	 * 参数检查错误
	 */
	public static final int ERROR_PARAM_CHECK = -202;
	/**
	 * 信息已存在
	 */
	public static final int ERROR_INFO_EXIST = -203;
	/**
	 * 信息不存在
	 */
	public static final int ERROR_INFO_NON_EXISTENT = -204;

	/**
	 * 图片格式错误
	 */
	public static final int ERROR_IMAGE_FORMAT_NOT_SUPPORT = -205;
	/**
	 * 图片信息为空
	 */
	public static final int ERROR_IMAGE_IS_NULL = -206;

	/**
	 * 上传图片太大
	 */
	public static final int ERROR_IMAGE_TOO_BIG = -207;

	/**
	 * 列表为空
	 */
	public static final int ERROR_LIST_EMPTY = -301;

	/**
	 * 登录错误
	 */
	public static final int ERROR_PARAM_LOGIN = -210;
	/**
	 * 添加失败
	 */
	public static final int ERROR_INSERT = -211;
	/**
	 * 修改失败
	 */
	public static final int ERROR_UPDATE = -212;
	/**
	 * 修改失败
	 */
	public static final int ERROR_DELETE = -213;

	/**
	 * 数据库执行时错误
	 */
	public static final int ERROR_DB_BASE = -10000;

	/**
	 * 人脸信息格式错误
	 */
	public static final int ERROR_PARAM_FACE_RECT_FORMAT = -1000;

	/**
	 * FD 提取特征失败
	 */
	public static final int ERROR_FD_DETECT = -1001;

	/**
	 * fr 比较失败
	 */
	public static final int ERROR_FR_COMPARE = -1002;
	/**
	 * fr 提取失败
	 */
	public static final int ERROR_FR_EXTRACTFEAUTRE = -1003;
	/**
	 * 文件提取人员信息时出错
	 */
	public static final int ERROR_IMAGE_FILE_PROCESS = -1004;
	/**
	 * feature数据为空
	 */
	public static final int ERROR_PARAM_FEATURE_INVALID = -1005;
	public static final String ERROR_PARAM_FEATURE_INVALID_STR = "feature数据为空";
	/**
	 * 无人脸信息
	 */
	public static final int ERROR_PARAM_FACE_RECT_NULL = -1006;

	/**
	 * 未找到引擎
	 */
	public static final int ERROR_ENGINEHANDLE_NULL = -1007;

	/**
	 * 未知错误
	 */
	public static final int ERROR_UNKNOWN_CODE = -100000;

	/**
	 * 未知的系统错误
	 */
	public static final int ERROR_GOALBAL = -999999;

}
