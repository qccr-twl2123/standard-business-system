package com.arcsoft.facego.common.dao;

import com.arcsoft.facego.common.entity.PersonImage;
import com.arcsoft.facego.common.entity.PersonImageExample;
import com.arcsoft.facego.util.bjui.Page;
import java.util.List;

public interface PersonImageDao {
    int save(PersonImage record);

    int deleteById(Long id);

    int updateById(PersonImage record);

    PersonImage getById(Long id);

    List<PersonImage> listByExample(PersonImageExample example);

    Page<PersonImage> listForPage(int pageCurrent, int pageSize, PersonImageExample example);
}