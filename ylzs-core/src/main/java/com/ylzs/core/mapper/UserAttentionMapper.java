package com.ylzs.core.mapper;

import org.apache.ibatis.annotations.Param;

import com.ylzs.core.model.UserAttention;

public interface UserAttentionMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserAttention record);

    int insertSelective(UserAttention record);

    UserAttention selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserAttention record);

    int updateByPrimaryKey(UserAttention record);

    /**
	 * 查询关注用户信息
	 * @param userId - 用户id
	 * @param attentionUserId - 关注的用户id
	 * @return
	 */
	UserAttention getAttentionInfo(@Param("userId") String userId,@Param("attentionUserId") String attentionUserId);
}