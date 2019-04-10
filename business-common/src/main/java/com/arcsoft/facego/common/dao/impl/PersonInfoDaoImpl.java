package com.arcsoft.facego.common.dao.impl;

import com.arcsoft.facego.common.dao.PersonInfoDao;
import com.arcsoft.facego.common.dao.impl.mapper.PersonInfoMapper;
import com.arcsoft.facego.common.entity.PersonInfo;
import com.arcsoft.facego.common.entity.PersonInfoExample;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.util.bjui.PageUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonInfoDaoImpl implements PersonInfoDao {
    @Autowired
    private PersonInfoMapper personInfoMapper;

    public Long save(PersonInfo record) {
        return this.personInfoMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.personInfoMapper.deleteByPrimaryKey(id);
    }

    public int updateById(PersonInfo record) {
        return this.personInfoMapper.updateByPrimaryKeySelective(record);
    }

    public List<PersonInfo> listByExample(PersonInfoExample example) {
        return this.personInfoMapper.selectByExample(example);
    }

    public PersonInfo getById(Long id) {
        return this.personInfoMapper.selectByPrimaryKey(id);
    }

    public Page<PersonInfo> listForPage(int pageCurrent, int pageSize, PersonInfoExample example) {
        int count = this.personInfoMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<PersonInfo>(count, totalPage, pageCurrent, pageSize, this.personInfoMapper.selectByExample(example));
    }
}