package com.business.system.common.dao.impl;

import com.business.system.common.dao.DepartmentDao;
import com.business.system.common.dao.impl.mapper.DepartmentMapper;
import com.business.system.common.entity.Department;
import com.business.system.common.entity.DepartmentExample;
import com.business.system.util.bjui.Page;
import com.business.system.util.bjui.PageUtil;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    @Autowired
    private DepartmentMapper departmentMapper;

    public int save(Department record) {
        return this.departmentMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.departmentMapper.deleteByPrimaryKey(id);
    }

    public int updateById(Department record) {
        return this.departmentMapper.updateByPrimaryKeySelective(record);
    }

    public List<Department> listByExample(DepartmentExample example) {
        return this.departmentMapper.selectByExample(example);
    }

    public Department getById(Long id) {
        return this.departmentMapper.selectByPrimaryKey(id);
    }

    public Page<Department> listForPage(int pageCurrent, int pageSize, DepartmentExample example) {
        int count = this.departmentMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Department>(count, totalPage, pageCurrent, pageSize, this.departmentMapper.selectByExample(example));
    }
}
