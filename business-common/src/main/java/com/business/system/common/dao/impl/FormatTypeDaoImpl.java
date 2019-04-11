package com.business.system.common.dao.impl;

import com.business.system.common.dao.FormatTypeDao;
import com.business.system.common.entity.FormatType;
import com.business.system.common.entity.FormatTypeExample;
import com.business.system.common.mapper.FormatTypeMapper;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FormatTypeDaoImpl implements FormatTypeDao {
    @Autowired
    private FormatTypeMapper formatTypeMapper;

    public int insert(FormatType record) {
        return this.formatTypeMapper.insert(record);
    }

    public int save(FormatType record) {
        return this.formatTypeMapper.insertSelective(record);
    }

    public int deleteByExample(FormatTypeExample example) {
        return this.formatTypeMapper.deleteByExample(example);
    }

    public int deleteById(Long id) {
        return this.formatTypeMapper.deleteByPrimaryKey(id);
    }

    public int updateById(FormatType record) {
        return this.formatTypeMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(FormatType record) {
        return this.formatTypeMapper.updateByPrimaryKey(record);
    }

    public int updateByExampleSelective(FormatType record, FormatTypeExample example) {
        return this.formatTypeMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(FormatType record, FormatTypeExample example) {
        return this.formatTypeMapper.updateByExample(record, example);
    }

    public List<FormatType> listByExample(FormatTypeExample example) {
        return this.formatTypeMapper.selectByExample(example);
    }

    public FormatType getById(Long id) {
        return this.formatTypeMapper.selectByPrimaryKey(id);
    }

    public int countByExample(FormatTypeExample example) {
        return this.formatTypeMapper.countByExample(example);
    }

    public Page<FormatType> listForPage(int pageCurrent, int pageSize, FormatTypeExample example) {
        int count = this.formatTypeMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<FormatType>(count, totalPage, pageCurrent, pageSize, this.formatTypeMapper.selectByExample(example));
    }
}