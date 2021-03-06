/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.business.system.common.dao.impl.mapper;

import com.business.system.common.entity.SysUserInfo;
import com.business.system.common.entity.SysUserInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserInfoMapper {
    int countByExample(SysUserInfoExample example);

    int deleteByExample(SysUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserInfo record);

    int insertSelective(SysUserInfo record);

    List<SysUserInfo> selectByExample(SysUserInfoExample example);

    SysUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserInfo record, @Param("example") SysUserInfoExample example);

    int updateByExample(@Param("record") SysUserInfo record, @Param("example") SysUserInfoExample example);

    int updateByPrimaryKeySelective(SysUserInfo record);

    int updateByPrimaryKey(SysUserInfo record);
}
