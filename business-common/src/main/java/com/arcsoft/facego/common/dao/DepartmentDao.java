package com.arcsoft.facego.common.dao;

import com.arcsoft.facego.common.entity.Department;
import com.arcsoft.facego.common.entity.DepartmentExample;
import com.arcsoft.facego.util.bjui.Page;
import java.util.List;

public interface DepartmentDao {
    int save(Department record);

    int deleteById(Long id);

    int updateById(Department record);

    Department getById(Long id);

    List<Department> listByExample(DepartmentExample example);

    Page<Department> listForPage(int pageCurrent, int pageSize, DepartmentExample example);
}