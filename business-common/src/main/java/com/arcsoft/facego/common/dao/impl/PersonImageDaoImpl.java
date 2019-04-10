package com.arcsoft.facego.common.dao.impl;

import com.arcsoft.facego.common.dao.PersonImageDao;
import com.arcsoft.facego.common.dao.impl.mapper.PersonImageMapper;
import com.arcsoft.facego.common.entity.PersonImage;
import com.arcsoft.facego.common.entity.PersonImageExample;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.util.bjui.PageUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonImageDaoImpl implements PersonImageDao {
    @Autowired
    private PersonImageMapper personImageMapper;

    public int save(PersonImage record) {
        return this.personImageMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.personImageMapper.deleteByPrimaryKey(id);
    }

    public int updateById(PersonImage record) {
        return this.personImageMapper.updateByPrimaryKeySelective(record);
    }

    public List<PersonImage> listByExample(PersonImageExample example) {
        return this.personImageMapper.selectByExample(example);
    }

    public PersonImage getById(Long id) {
        return this.personImageMapper.selectByPrimaryKey(id);
    }

    public Page<PersonImage> listForPage(int pageCurrent, int pageSize, PersonImageExample example) {
        int count = this.personImageMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<PersonImage>(count, totalPage, pageCurrent, pageSize, this.personImageMapper.selectByExample(example));
    }
}