package com.business.system.common.dao;

import com.business.system.common.entity.PersonInfo;
import com.business.system.common.entity.PersonInfoExample;
import com.business.system.util.bjui.Page;

import java.util.List;

public interface PersonInfoDao {
    Long save(PersonInfo record);

    int deleteById(Long id);

    int updateById(PersonInfo record);

    PersonInfo getById(Long id);

    List<PersonInfo> listByExample(PersonInfoExample example);

    Page<PersonInfo> listForPage(int pageCurrent, int pageSize, PersonInfoExample example);
}
