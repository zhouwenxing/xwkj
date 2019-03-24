package com.ylzs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylzs.core.mapper.UserAttentionMapper;
import com.ylzs.core.model.UserAttention;
import com.ylzs.util.CommonUtil;
import com.ylzs.util.DateTimeUtil;


@Service
public class UserAttentionService {
	@Autowired
	private UserAttentionMapper userAttentionMapper;

	/**
	 * 添加关注
	 * @param userAttention - 关注信息
	 * @param userId - 用户id
	 * @param attentionUserId - 关注的用户id
	 */
	public void addAttentionUser(UserAttention userAttention,String userId, String attentionUserId) {
		if(null == userAttention){//添加
			userAttention = new UserAttention();
			userAttention.setId(CommonUtil.uuid());
			userAttention.setUserId(userId);
			userAttention.setAttentionUserId(attentionUserId);
			userAttention.setAttentionStatus(1);
			userAttention.setCreateTime(DateTimeUtil.getNowTimestamp());
			userAttentionMapper.insertSelective(userAttention);
		}else{//更新
			userAttention.setAttentionStatus(1);
			userAttention.setUpdateTime(DateTimeUtil.getNowTimestamp());
			userAttentionMapper.updateByPrimaryKeySelective(userAttention);
		}
		
	}

	/**
	 * 取消关注
	 * @param userAttention
	 */
	public void cancelAttentionUser(UserAttention userAttention) {
		userAttention.setAttentionStatus(0);
		userAttention.setUpdateTime(DateTimeUtil.getNowTimestamp());
		userAttentionMapper.updateByPrimaryKeySelective(userAttention);
	}

	/**
	 * 查询关注用户信息
	 * @param userId - 用户id
	 * @param attentionUserId - 关注的用户id
	 * @return
	 */
	public UserAttention queryAttentionInfo(String userId,String attentionUserId) {
		return userAttentionMapper.getAttentionInfo(userId,attentionUserId);
	}

}
