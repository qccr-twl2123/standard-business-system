/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.common.dao.impl.mapper;

import com.business.system.common.entity.SysLogOpt;
import com.business.system.common.entity.SysLogOptExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogOptMapper {
    int countByExample(SysLogOptExample example);

    int deleteByExample(SysLogOptExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysLogOpt record);

    int insertSelective(SysLogOpt record);

    List<SysLogOpt> selectByExample(SysLogOptExample example);

    SysLogOpt selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysLogOpt record, @Param("example") SysLogOptExample example);

    int updateByExample(@Param("record") SysLogOpt record, @Param("example") SysLogOptExample example);

    int updateByPrimaryKeySelective(SysLogOpt record);

    int updateByPrimaryKey(SysLogOpt record);
}
