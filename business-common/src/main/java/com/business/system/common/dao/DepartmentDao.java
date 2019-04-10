package com.business.system.common.dao;

import com.business.system.common.entity.Department;
import com.business.system.common.entity.DepartmentExample;
import com.business.system.util.bjui.Page;

import java.util.List;

public interface DepartmentDao {
    int save(Department record);

    int deleteById(Long id);

    int updateById(Department record);

    Department getById(Long id);

    List<Department> listByExample(DepartmentExample example);

    Page<Department> listForPage(int pageCurrent, int pageSize, DepartmentExample example);
}
