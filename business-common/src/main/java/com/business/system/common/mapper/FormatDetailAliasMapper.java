package com.business.system.common.mapper;

import com.business.system.common.entity.FormatDetailAlias;
import com.business.system.common.entity.FormatDetailAliasExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FormatDetailAliasMapper {
    int countByExample(FormatDetailAliasExample example);

    int deleteByExample(FormatDetailAliasExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FormatDetailAlias record);

    int insertSelective(FormatDetailAlias record);

    List<FormatDetailAlias> selectByExample(FormatDetailAliasExample example);

    FormatDetailAlias selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FormatDetailAlias record, @Param("example") FormatDetailAliasExample example);

    int updateByExample(@Param("record") FormatDetailAlias record, @Param("example") FormatDetailAliasExample example);

    int updateByPrimaryKeySelective(FormatDetailAlias record);

    int updateByPrimaryKey(FormatDetailAlias record);
}