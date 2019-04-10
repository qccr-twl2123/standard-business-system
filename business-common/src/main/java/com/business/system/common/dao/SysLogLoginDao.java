/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.common.dao;

import com.business.system.common.entity.SysLogLogin;
import com.business.system.common.entity.SysLogLoginExample;
import com.business.system.util.bjui.Page;

public interface SysLogLoginDao {
    int save(SysLogLogin record);

    int deleteById(Long id);

    int updateById(SysLogLogin record);

    SysLogLogin getById(Long id);

    Page<SysLogLogin> listForPage(int pageCurrent, int pageSize, SysLogLoginExample example);
}
