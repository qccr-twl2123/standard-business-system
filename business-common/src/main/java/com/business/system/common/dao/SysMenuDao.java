/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.common.dao;

import com.business.system.common.entity.SysMenu;
import com.business.system.common.entity.SysMenuExample;
import com.business.system.util.bjui.Page;

import java.util.List;

public interface SysMenuDao {
	Long save(SysMenu record);

	int deleteById(Long id);

	int updateById(SysMenu record);

	SysMenu getById(Long id);

	Page<SysMenu> listForPage(int pageCurrent, int pageSize, SysMenuExample example);

	List<SysMenu> listByPlatformId(Long platformId);

	List<SysMenu> listByParentId(Long id);

	List<SysMenu> listByParentIdAndPlatformId(Long parentId, Long platformId);
}
