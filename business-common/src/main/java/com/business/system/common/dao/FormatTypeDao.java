package com.business.system.common.dao;

import com.business.system.common.entity.FormatType;
import com.business.system.common.entity.FormatTypeExample;
import com.business.system.util.bjui.Page;
import java.util.List;

public interface FormatTypeDao {
    int insert(FormatType record);

    int save(FormatType record);

    int deleteByExample(FormatTypeExample example);

    int deleteById(Long id);

    int updateById(FormatType record);

    int updateByPrimaryKey(FormatType record);

    int updateByExampleSelective(FormatType record, FormatTypeExample example);

    int updateByExample(FormatType record, FormatTypeExample example);

    FormatType getById(Long id);

    List<FormatType> listByExample(FormatTypeExample example);

    int countByExample(FormatTypeExample example);

    Page<FormatType> listForPage(int pageCurrent, int pageSize, FormatTypeExample example);
}