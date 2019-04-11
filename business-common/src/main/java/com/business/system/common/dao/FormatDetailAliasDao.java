package com.business.system.common.dao;

import com.business.system.common.entity.FormatDetailAlias;
import com.business.system.common.entity.FormatDetailAliasExample;
import com.business.system.util.bjui.Page;
import java.util.List;

public interface FormatDetailAliasDao {
    int insert(FormatDetailAlias record);

    int save(FormatDetailAlias record);

    int deleteByExample(FormatDetailAliasExample example);

    int deleteById(Long id);

    int updateById(FormatDetailAlias record);

    int updateByPrimaryKey(FormatDetailAlias record);

    int updateByExampleSelective(FormatDetailAlias record, FormatDetailAliasExample example);

    int updateByExample(FormatDetailAlias record, FormatDetailAliasExample example);

    FormatDetailAlias getById(Long id);

    List<FormatDetailAlias> listByExample(FormatDetailAliasExample example);

    int countByExample(FormatDetailAliasExample example);

    Page<FormatDetailAlias> listForPage(int pageCurrent, int pageSize, FormatDetailAliasExample example);
}