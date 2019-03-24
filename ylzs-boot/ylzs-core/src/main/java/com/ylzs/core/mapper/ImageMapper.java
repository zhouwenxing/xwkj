package com.ylzs.core.mapper;

import com.ylzs.core.model.Image;

public interface ImageMapper {
    int deleteByPrimaryKey(String id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}