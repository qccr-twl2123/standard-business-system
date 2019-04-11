package com.business.system.common.mapper;

import com.business.system.common.entity.FormatTypeDetail;
import com.business.system.common.entity.FormatTypeDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FormatTypeDetailMapper {
    int countByExample(FormatTypeDetailExample example);

    int deleteByExample(FormatTypeDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FormatTypeDetail record);

    int insertSelective(FormatTypeDetail record);

    List<FormatTypeDetail> selectByExample(FormatTypeDetailExample example);

    FormatTypeDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FormatTypeDetail record, @Param("example") FormatTypeDetailExample example);

    int updateByExample(@Param("record") FormatTypeDetail record, @Param("example") FormatTypeDetailExample example);

    int updateByPrimaryKeySelective(FormatTypeDetail record);

    int updateByPrimaryKey(FormatTypeDetail record);
}