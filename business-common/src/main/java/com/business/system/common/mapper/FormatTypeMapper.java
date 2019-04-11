package com.business.system.common.mapper;

import com.business.system.common.entity.FormatType;
import com.business.system.common.entity.FormatTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FormatTypeMapper {
    int countByExample(FormatTypeExample example);

    int deleteByExample(FormatTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FormatType record);

    int insertSelective(FormatType record);

    List<FormatType> selectByExample(FormatTypeExample example);

    FormatType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FormatType record, @Param("example") FormatTypeExample example);

    int updateByExample(@Param("record") FormatType record, @Param("example") FormatTypeExample example);

    int updateByPrimaryKeySelective(FormatType record);

    int updateByPrimaryKey(FormatType record);
}