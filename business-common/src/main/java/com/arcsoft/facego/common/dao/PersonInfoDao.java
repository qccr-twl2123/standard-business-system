package com.arcsoft.facego.common.dao;

import com.arcsoft.facego.common.entity.PersonInfo;
import com.arcsoft.facego.common.entity.PersonInfoExample;
import com.arcsoft.facego.util.bjui.Page;
import java.util.List;

public interface PersonInfoDao {
    Long save(PersonInfo record);

    int deleteById(Long id);

    int updateById(PersonInfo record);

    PersonInfo getById(Long id);

    List<PersonInfo> listByExample(PersonInfoExample example);

    Page<PersonInfo> listForPage(int pageCurrent, int pageSize, PersonInfoExample example);
}