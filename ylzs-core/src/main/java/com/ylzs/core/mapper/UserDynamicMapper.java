package com.ylzs.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ylzs.core.model.UserDynamic;
import com.ylzs.core.vo.CommentVO;
import com.ylzs.core.vo.DynamicVO;

public interface UserDynamicMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDynamic record);

    int insertSelective(UserDynamic record);

    UserDynamic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDynamic record);

    int updateByPrimaryKey(UserDynamic record);

    /**
	 * 获取关注好友动态列表
	 * @param userId - 用户id
	 * @return
	 */
	List<DynamicVO> listFriendDynamic(@Param("userId") String userId);

	/**
	 * 获取动态图片
	 * @param dynamicId - 动态id
	 * @return
	 */
	List<String> listDynamicPic(@Param("dynamicId") String dynamicId);

	/**
	 * 获取动态评论信息
	 * @param dynamicId - 动态id
	 * @return
	 */
	List<DynamicVO.CommentInfo> listDynamicComment(@Param("dynamicId") String dynamicId);
}