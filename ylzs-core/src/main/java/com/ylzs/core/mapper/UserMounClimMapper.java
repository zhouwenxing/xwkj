package com.ylzs.core.mapper;

import org.apache.ibatis.annotations.Param;

import com.ylzs.core.model.UserMounClim;
import com.ylzs.core.vo.HomeStatisVO;

public interface UserMounClimMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserMounClim record);

    int insertSelective(UserMounClim record);

    UserMounClim selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserMounClim record);

    int updateByPrimaryKey(UserMounClim record);
    
    /**
     * 首页统计用户登山信息
     * @param userId - 用户id
     * @return
     */
	HomeStatisVO staticsClimbMountain(@Param("userId") String userId);
}