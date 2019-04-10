package com.arcsoft.facego.common.dao;

import com.arcsoft.facego.common.entity.DoorInfo;
import com.arcsoft.facego.common.entity.DoorInfoExample;
import com.arcsoft.facego.util.bjui.Page;
import java.util.List;

public interface DoorInfoDao {
    int save(DoorInfo record);

    int deleteById(Long id);

    int updateById(DoorInfo record);

    DoorInfo getById(Long id);

    List<DoorInfo> listByExample(DoorInfoExample example);

    Page<DoorInfo> listForPage(int pageCurrent, int pageSize, DoorInfoExample example);
}