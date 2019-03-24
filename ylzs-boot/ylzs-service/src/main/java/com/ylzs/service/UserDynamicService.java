package com.ylzs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylzs.core.dto.ReleaseDynamicDTO;
import com.ylzs.core.mapper.UserDynamicMapper;
import com.ylzs.core.model.UserDynamic;


@Service
public class UserDynamicService {
	@Autowired
	private UserDynamicMapper userdynamicMapper;

	
	public void releaseDynamic(String userId, ReleaseDynamicDTO releaseZoneDTO) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 删除动态
	 * @param userZone
	 */
	public void updateByPrimaryKeySelective(UserDynamic userZone) {
		userdynamicMapper.updateByPrimaryKeySelective(userZone);
	}

	
	public UserDynamic selectByPrimaryKey(String dynamicId) {
		return userdynamicMapper.selectByPrimaryKey(dynamicId);
	}

}
