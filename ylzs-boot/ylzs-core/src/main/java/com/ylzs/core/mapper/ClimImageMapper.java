package com.ylzs.core.mapper;

import com.ylzs.core.model.ClimImage;

public interface ClimImageMapper {
    int deleteByPrimaryKey(String id);

    int insert(ClimImage record);

    int insertSelective(ClimImage record);

    ClimImage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ClimImage record);

    int updateByPrimaryKey(ClimImage record);

}