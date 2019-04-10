package com.arcsoft.facego.common.dao.impl;

import com.arcsoft.facego.common.dao.DoorInfoDao;
import com.arcsoft.facego.common.dao.impl.mapper.DoorInfoMapper;
import com.arcsoft.facego.common.entity.DoorInfo;
import com.arcsoft.facego.common.entity.DoorInfoExample;
import com.arcsoft.facego.util.bjui.Page;
import com.arcsoft.facego.util.bjui.PageUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DoorInfoDaoImpl implements DoorInfoDao {
    @Autowired
    private DoorInfoMapper doorInfoMapper;

    public int save(DoorInfo record) {
        return this.doorInfoMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.doorInfoMapper.deleteByPrimaryKey(id);
    }

    public int updateById(DoorInfo record) {
        return this.doorInfoMapper.updateByPrimaryKeySelective(record);
    }

    public List<DoorInfo> listByExample(DoorInfoExample example) {
        return this.doorInfoMapper.selectByExample(example);
    }

    public DoorInfo getById(Long id) {
        return this.doorInfoMapper.selectByPrimaryKey(id);
    }

    public Page<DoorInfo> listForPage(int pageCurrent, int pageSize, DoorInfoExample example) {
        int count = this.doorInfoMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<DoorInfo>(count, totalPage, pageCurrent, pageSize, this.doorInfoMapper.selectByExample(example));
    }
}