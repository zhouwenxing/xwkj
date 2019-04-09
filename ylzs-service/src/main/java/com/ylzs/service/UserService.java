package com.ylzs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylzs.core.model.User;
import com.ylzs.core.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	public void updateByPrimaryKeySelective(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 获取当前用户头像路径
	 * @param userId
	 * @return
	 */
	public String getHeadPathByUserId(String userId) {
		return userMapper.getHeadPathByUserId(userId);
	}
	
	

}
