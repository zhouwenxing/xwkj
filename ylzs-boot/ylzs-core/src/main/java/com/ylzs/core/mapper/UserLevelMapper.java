package com.ylzs.core.mapper;

import com.ylzs.core.model.UserLevel;

public interface UserLevelMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserLevel record);

    int insertSelective(UserLevel record);

    UserLevel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserLevel record);

    int updateByPrimaryKey(UserLevel record);
}