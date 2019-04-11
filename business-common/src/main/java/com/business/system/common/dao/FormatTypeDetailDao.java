package com.business.system.common.dao;

import com.business.system.common.entity.FormatTypeDetail;
import com.business.system.common.entity.FormatTypeDetailExample;
import com.business.system.util.bjui.Page;
import java.util.List;

public interface FormatTypeDetailDao {
    int insert(FormatTypeDetail record);

    int save(FormatTypeDetail record);

    int deleteByExample(FormatTypeDetailExample example);

    int deleteById(Long id);

    int updateById(FormatTypeDetail record);

    int updateByPrimaryKey(FormatTypeDetail record);

    int updateByExampleSelective(FormatTypeDetail record, FormatTypeDetailExample example);

    int updateByExample(FormatTypeDetail record, FormatTypeDetailExample example);

    FormatTypeDetail getById(Long id);

    List<FormatTypeDetail> listByExample(FormatTypeDetailExample example);

    int countByExample(FormatTypeDetailExample example);

    Page<FormatTypeDetail> listForPage(int pageCurrent, int pageSize, FormatTypeDetailExample example);
}