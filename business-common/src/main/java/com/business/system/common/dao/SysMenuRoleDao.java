/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.common.dao;

import com.business.system.common.entity.SysMenuRole;
import com.business.system.common.entity.SysMenuRoleExample;
import com.business.system.util.bjui.Page;

import java.util.List;

public interface SysMenuRoleDao {
    int save(SysMenuRole record);

    int deleteById(Long id);

    int updateById(SysMenuRole record);

    SysMenuRole getById(Long id);

    Page<SysMenuRole> listForPage(int pageCurrent, int pageSize, SysMenuRoleExample example);

	List<SysMenuRole> listByRoleId(Long roleId);

	int deleteByRoleId(Long roleId);
}
