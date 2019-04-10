package com.business.system.common.dao.impl.mapper;

import com.business.system.common.entity.VerificationLog;
import com.business.system.common.entity.VerificationLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VerificationLogMapper {
    int countByExample(VerificationLogExample example);

    int deleteByExample(VerificationLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VerificationLog record);

    int insertSelective(VerificationLog record);

    List<VerificationLog> selectByExample(VerificationLogExample example);

    VerificationLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VerificationLog record, @Param("example") VerificationLogExample example);

    int updateByExample(@Param("record") VerificationLog record, @Param("example") VerificationLogExample example);

    int updateByPrimaryKeySelective(VerificationLog record);

    int updateByPrimaryKey(VerificationLog record);
}
