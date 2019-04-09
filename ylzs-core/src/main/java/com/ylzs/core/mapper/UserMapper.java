package com.ylzs.core.mapper;

import org.apache.ibatis.annotations.Param;

import com.ylzs.core.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
	 * 获取当前用户头像路径
	 * @param userId
	 * @return
	 */
	String getHeadPathByUserId(@Param("userId") String userId);
}