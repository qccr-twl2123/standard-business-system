/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.web.bean.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户操作日志表
 * </p>
 *
 * @author wujing
 * @since 2017-12-27
 */
@Data
@Accessors(chain = true)
public class SysLogOptQO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 用户ID
	 */
	private Long userInfoId;
	/**
	 * 真实姓名
	 */
	private String loginName;
	/**
	 * 动作
	 */
	private Integer action;
	/**
	 * 备注
	 */
	private String remark;
}
