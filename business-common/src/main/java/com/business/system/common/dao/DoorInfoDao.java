package com.business.system.common.dao;

import com.business.system.common.entity.DoorInfo;
import com.business.system.common.entity.DoorInfoExample;
import com.business.system.util.bjui.Page;

import java.util.List;

public interface DoorInfoDao {
    int save(DoorInfo record);

    int deleteById(Long id);

    int updateById(DoorInfo record);

    DoorInfo getById(Long id);

    List<DoorInfo> listByExample(DoorInfoExample example);

    Page<DoorInfo> listForPage(int pageCurrent, int pageSize, DoorInfoExample example);
}
