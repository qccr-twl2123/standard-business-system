/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.common.dao;

import com.business.system.common.entity.SysRoleUser;
import com.business.system.common.entity.SysRoleUserExample;
import com.business.system.util.bjui.Page;

import java.util.List;

public interface SysRoleUserDao {
    int save(SysRoleUser record);

    int deleteById(Long id);

    int updateById(SysRoleUser record);

    SysRoleUser getById(Long id);

    Page<SysRoleUser> listForPage(int pageCurrent, int pageSize, SysRoleUserExample example);

	List<SysRoleUser> listByUserInfoId(Long userInfoId);

	int deleteByUserInfoId(Long userInfoId);

	List<SysRoleUser> listByUserInfoIdAndPlatformId(Long userInfoId, Long platformId);
}
