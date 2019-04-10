package com.arcsoft.facego.common.dao.impl;

import com.arcsoft.facego.common.dao.VerificationLogDao;
import com.arcsoft.facego.common.dao.impl.mapper.VerificationLogMapper;
import com.arcsoft.facego.common.entity.VerificationLog;
import com.arcsoft.facego.common.entity.VerificationLogExample;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.util.bjui.PageUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VerificationLogDaoImpl implements VerificationLogDao {
    @Autowired
    private VerificationLogMapper verificationLogMapper;

    public int save(VerificationLog record) {
        return this.verificationLogMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.verificationLogMapper.deleteByPrimaryKey(id);
    }

    public int updateById(VerificationLog record) {
        return this.verificationLogMapper.updateByPrimaryKeySelective(record);
    }

    public List<VerificationLog> listByExample(VerificationLogExample example) {
        return this.verificationLogMapper.selectByExample(example);
    }

    public VerificationLog getById(Long id) {
        return this.verificationLogMapper.selectByPrimaryKey(id);
    }

    public Page<VerificationLog> listForPage(int pageCurrent, int pageSize, VerificationLogExample example) {
        int count = this.verificationLogMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<VerificationLog>(count, totalPage, pageCurrent, pageSize, this.verificationLogMapper.selectByExample(example));
    }
}