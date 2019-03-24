package com.ylzs.core.mapper;

import com.ylzs.core.model.UserDynamic;

public interface UserDynamicMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDynamic record);

    int insertSelective(UserDynamic record);

    UserDynamic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDynamic record);

    int updateByPrimaryKey(UserDynamic record);
}