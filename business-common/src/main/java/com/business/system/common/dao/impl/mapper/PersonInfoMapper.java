package com.business.system.common.dao.impl.mapper;

import com.business.system.common.entity.PersonInfo;
import com.business.system.common.entity.PersonInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonInfoMapper {
    int countByExample(PersonInfoExample example);

    int deleteByExample(PersonInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PersonInfo record);

    Long insertSelective(PersonInfo record);

    List<PersonInfo> selectByExample(PersonInfoExample example);

    PersonInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PersonInfo record, @Param("example") PersonInfoExample example);

    int updateByExample(@Param("record") PersonInfo record, @Param("example") PersonInfoExample example);

    int updateByPrimaryKeySelective(PersonInfo record);

    int updateByPrimaryKey(PersonInfo record);
}
