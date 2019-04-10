package com.arcsoft.facego.common.dao.impl.mapper;

import com.arcsoft.facego.common.entity.PersonImage;
import com.arcsoft.facego.common.entity.PersonImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonImageMapper {
    int countByExample(PersonImageExample example);

    int deleteByExample(PersonImageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PersonImage record);

    int insertSelective(PersonImage record);

    List<PersonImage> selectByExampleWithBLOBs(PersonImageExample example);

    List<PersonImage> selectByExample(PersonImageExample example);

    PersonImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PersonImage record, @Param("example") PersonImageExample example);

    int updateByExampleWithBLOBs(@Param("record") PersonImage record, @Param("example") PersonImageExample example);

    int updateByExample(@Param("record") PersonImage record, @Param("example") PersonImageExample example);

    int updateByPrimaryKeySelective(PersonImage record);

    int updateByPrimaryKeyWithBLOBs(PersonImage record);

    int updateByPrimaryKey(PersonImage record);
}