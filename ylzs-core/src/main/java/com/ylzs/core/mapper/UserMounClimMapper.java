package com.ylzs.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ylzs.core.model.UserMounClim;
import com.ylzs.core.vo.HomeStatisVO;
import com.ylzs.core.vo.MoutainClimbVO;

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

	/**
     * 获取行程历史记录
     * @param userId - 用户id
     * @return
     */
	List<MoutainClimbVO> listHistoryByUserId(@Param("userId") String userId);
}