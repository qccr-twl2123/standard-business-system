/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.web.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 菜单信息
 * </p>
 *
 * @author wujing
 * @since 2017-12-27
 */
@Data
@Accessors(chain = true)
public class SysMenuVO implements Serializable {

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
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 1 正常 2 冻结 9 注销
	 */
	private Integer statusId;
	/**
	 * 平台ID
	 */
	private Long platformId;
	/**
	 * 父ID
	 */
	private Long parentId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单路径
	 */
	private String menuUrl;
	/**
	 * 目标名称
	 */
	private String targetName;
	/**
	 * 菜单图标
	 */
	private String menuIcon;
	/**
	 * 备注
	 */
	private String remark;

	private Integer isShow;

	private List<SysMenuVO> list;

}
