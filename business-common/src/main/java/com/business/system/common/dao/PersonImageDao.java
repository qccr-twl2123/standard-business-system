package com.business.system.common.dao;

import com.business.system.common.entity.PersonImage;
import com.business.system.common.entity.PersonImageExample;
import com.business.system.util.bjui.Page;

import java.util.List;

public interface PersonImageDao {
    int save(PersonImage record);

    int deleteById(Long id);

    int updateById(PersonImage record);

    PersonImage getById(Long id);

    List<PersonImage> listByExample(PersonImageExample example);

    Page<PersonImage> listForPage(int pageCurrent, int pageSize, PersonImageExample example);
}
