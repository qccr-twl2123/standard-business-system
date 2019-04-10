package com.business.system.common.dao;

import com.business.system.common.entity.Brand;
import com.business.system.common.entity.BrandExample;
import com.business.system.util.bjui.Page;
import java.util.List;

public interface BrandDao {
    int insert(Brand record);

    int save(Brand record);

    int deleteByExample(BrandExample example);

    int deleteById(Long id);

    int updateById(Brand record);

    int updateByPrimaryKey(Brand record);

    int updateByExampleSelective(Brand record, BrandExample example);

    int updateByExample(Brand record, BrandExample example);

    Brand getById(Long id);

    List<Brand> listByExample(BrandExample example);

    int countByExample(BrandExample example);

    Page<Brand> listForPage(int pageCurrent, int pageSize, BrandExample example);
}