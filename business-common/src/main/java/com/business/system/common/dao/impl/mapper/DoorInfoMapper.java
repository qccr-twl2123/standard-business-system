package com.business.system.common.dao.impl.mapper;

import com.business.system.common.entity.DoorInfo;
import com.business.system.common.entity.DoorInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DoorInfoMapper {
    int countByExample(DoorInfoExample example);

    int deleteByExample(DoorInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DoorInfo record);

    int insertSelective(DoorInfo record);

    List<DoorInfo> selectByExample(DoorInfoExample example);

    DoorInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DoorInfo record, @Param("example") DoorInfoExample example);

    int updateByExample(@Param("record") DoorInfo record, @Param("example") DoorInfoExample example);

    int updateByPrimaryKeySelective(DoorInfo record);

    int updateByPrimaryKey(DoorInfo record);
}
