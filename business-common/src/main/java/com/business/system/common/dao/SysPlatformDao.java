/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.common.dao;

import com.business.system.common.entity.SysPlatform;
import com.business.system.common.entity.SysPlatformExample;
import com.business.system.util.bjui.Page;

import java.util.List;

public interface SysPlatformDao {
    int save(SysPlatform record);

    int deleteById(Long id);

    int updateById(SysPlatform record);

    SysPlatform getById(Long id);

    Page<SysPlatform> listForPage(int pageCurrent, int pageSize, SysPlatformExample example);

	List<SysPlatform> listAll();

	SysPlatform getByPlatformAppid(String platformAppid);
}
