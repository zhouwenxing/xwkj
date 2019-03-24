package com.ylzs.core.mapper;

import com.ylzs.core.model.DynamicComment;

public interface DynamicCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(DynamicComment record);

    int insertSelective(DynamicComment record);

    DynamicComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DynamicComment record);

    int updateByPrimaryKey(DynamicComment record);
}