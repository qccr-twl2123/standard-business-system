/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.common.dao;

import com.business.system.common.entity.SysUserInfo;
import com.business.system.common.entity.SysUserInfoExample;
import com.business.system.util.bjui.Page;

public interface SysUserInfoDao {
	Long save(SysUserInfo record);

	int deleteById(Long id);

	int updateById(SysUserInfo record);

	SysUserInfo getById(Long id);

	Page<SysUserInfo> listForPage(int pageCurrent, int pageSize, SysUserInfoExample example);

	SysUserInfo getByLoginName(String username);
}
