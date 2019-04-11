package com.business.system.common.dao.impl;

import com.business.system.common.dao.FormatDetailAliasDao;
import com.business.system.common.entity.FormatDetailAlias;
import com.business.system.common.entity.FormatDetailAliasExample;
import com.business.system.common.mapper.FormatDetailAliasMapper;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FormatDetailAliasDaoImpl implements FormatDetailAliasDao {
    @Autowired
    private FormatDetailAliasMapper formatDetailAliasMapper;

    public int insert(FormatDetailAlias record) {
        return this.formatDetailAliasMapper.insert(record);
    }

    public int save(FormatDetailAlias record) {
        return this.formatDetailAliasMapper.insertSelective(record);
    }

    public int deleteByExample(FormatDetailAliasExample example) {
        return this.formatDetailAliasMapper.deleteByExample(example);
    }

    public int deleteById(Long id) {
        return this.formatDetailAliasMapper.deleteByPrimaryKey(id);
    }

    public int updateById(FormatDetailAlias record) {
        return this.formatDetailAliasMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(FormatDetailAlias record) {
        return this.formatDetailAliasMapper.updateByPrimaryKey(record);
    }

    public int updateByExampleSelective(FormatDetailAlias record, FormatDetailAliasExample example) {
        return this.formatDetailAliasMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(FormatDetailAlias record, FormatDetailAliasExample example) {
        return this.formatDetailAliasMapper.updateByExample(record, example);
    }

    public List<FormatDetailAlias> listByExample(FormatDetailAliasExample example) {
        return this.formatDetailAliasMapper.selectByExample(example);
    }

    public FormatDetailAlias getById(Long id) {
        return this.formatDetailAliasMapper.selectByPrimaryKey(id);
    }

    public int countByExample(FormatDetailAliasExample example) {
        return this.formatDetailAliasMapper.countByExample(example);
    }

    public Page<FormatDetailAlias> listForPage(int pageCurrent, int pageSize, FormatDetailAliasExample example) {
        int count = this.formatDetailAliasMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<FormatDetailAlias>(count, totalPage, pageCurrent, pageSize, this.formatDetailAliasMapper.selectByExample(example));
    }
}