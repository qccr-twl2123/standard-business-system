package com.business.system.common.dao.impl;

import com.business.system.common.dao.FormatTypeDetailDao;
import com.business.system.common.entity.FormatTypeDetail;
import com.business.system.common.entity.FormatTypeDetailExample;
import com.business.system.common.mapper.FormatTypeDetailMapper;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FormatTypeDetailDaoImpl implements FormatTypeDetailDao {
    @Autowired
    private FormatTypeDetailMapper formatTypeDetailMapper;

    public int insert(FormatTypeDetail record) {
        return this.formatTypeDetailMapper.insert(record);
    }

    public int save(FormatTypeDetail record) {
        return this.formatTypeDetailMapper.insertSelective(record);
    }

    public int deleteByExample(FormatTypeDetailExample example) {
        return this.formatTypeDetailMapper.deleteByExample(example);
    }

    public int deleteById(Long id) {
        return this.formatTypeDetailMapper.deleteByPrimaryKey(id);
    }

    public int updateById(FormatTypeDetail record) {
        return this.formatTypeDetailMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(FormatTypeDetail record) {
        return this.formatTypeDetailMapper.updateByPrimaryKey(record);
    }

    public int updateByExampleSelective(FormatTypeDetail record, FormatTypeDetailExample example) {
        return this.formatTypeDetailMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(FormatTypeDetail record, FormatTypeDetailExample example) {
        return this.formatTypeDetailMapper.updateByExample(record, example);
    }

    public List<FormatTypeDetail> listByExample(FormatTypeDetailExample example) {
        return this.formatTypeDetailMapper.selectByExample(example);
    }

    public FormatTypeDetail getById(Long id) {
        return this.formatTypeDetailMapper.selectByPrimaryKey(id);
    }

    public int countByExample(FormatTypeDetailExample example) {
        return this.formatTypeDetailMapper.countByExample(example);
    }

    public Page<FormatTypeDetail> listForPage(int pageCurrent, int pageSize, FormatTypeDetailExample example) {
        int count = this.formatTypeDetailMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<FormatTypeDetail>(count, totalPage, pageCurrent, pageSize, this.formatTypeDetailMapper.selectByExample(example));
    }
}