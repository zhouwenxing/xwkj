package com.ylzs.core.mapper;

import com.ylzs.core.model.UserWechat;

public interface UserWechatMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserWechat record);

    int insertSelective(UserWechat record);

    UserWechat selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserWechat record);

    int updateByPrimaryKey(UserWechat record);
}