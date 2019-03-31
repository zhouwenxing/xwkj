package com.ylzs.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylzs.core.constant.DynamicEnum;
import com.ylzs.core.dto.ClimbingTrackDTO;
import com.ylzs.core.dto.CreateTravelDTO;
import com.ylzs.core.dto.ReleaseTravelDTO;
import com.ylzs.core.dto.UpdateTravelDTO;
import com.ylzs.core.mapper.UserDynamicMapper;
import com.ylzs.core.mapper.UserMounClimMapper;
import com.ylzs.core.model.UserDynamic;
import com.ylzs.core.model.UserMounClim;
import com.ylzs.core.vo.HomeStatisVO;
import com.ylzs.util.CommonUtil;
import com.ylzs.util.DateTimeUtil;

@Service
public class MoutainClimbService {
	@Autowired
	private UserMounClimMapper moutainClimMapper;
	@Autowired
	private ClimImageService climImageService;
	@Autowired
	private UserDynamicMapper userDynamicMapper;
	@Autowired
	private ViewFriendService viewFriendService;

	
	 /**
     * 首页统计用户登山信息
     * @param userId - 用户id
     * @return
     */
	public HomeStatisVO staticsClimbMountain(String userId) {
		return moutainClimMapper.staticsClimbMountain(userId);
	}


	/**
	 * 发布行程
	 * @param userMounClim - 登山相关信息
	 * @param releaseTravelDTO - 发布行程相关请求参数
	 */
	@Transactional
	public void realeaseTravel(UserMounClim userMounClim, ReleaseTravelDTO releaseTravelDTO) {
		//更新行程信息
		if(null != userMounClim){
			userMounClim.setUserExperience(releaseTravelDTO.getUserExperience());
			userMounClim.setMountainName(releaseTravelDTO.getMountainName());
			moutainClimMapper.updateByPrimaryKeySelective(userMounClim);
		}
		//发布动态
		String dynamicId = CommonUtil.uuid();
		UserDynamic userDynamic = new UserDynamic();
		BeanUtils.copyProperties(releaseTravelDTO, userDynamic);
		userDynamic.setId(dynamicId);
		//冗余字段
		userDynamic.setUserExperience(releaseTravelDTO.getUserExperience());
		userDynamic.setMountainName(releaseTravelDTO.getMountainName());
		userDynamic.setClimblingAltitude(userMounClim.getClimblingAltitude());
		
		userDynamic.setCreateTime(DateTimeUtil.getNowTimestamp());
		userDynamic.setDynamicStatus(DynamicEnum.DYNAMIC_RELEASE.getCode());
		userDynamicMapper.insertSelective(userDynamic);
		//更新查看好友表
		if(userDynamic.getViewType() == DynamicEnum.VIEW_SELECT_FRIEND.getCode()){
			viewFriendService.batchInsertFriend(dynamicId,releaseTravelDTO.getFriendList(),DynamicEnum.FRIEND_VISIBLE.getCode());
		}
		//更新行程图片
		climImageService.batchUpdateImage(dynamicId,releaseTravelDTO.getPicIdList());
	}


	/**
	 * 创建行程
	 * @param userId - 用户id
	 * @param createTravelDTO - 行程相关参数
	 */
	public String createTravel(String userId, CreateTravelDTO createTravelDTO) {
		UserMounClim userMounClim = new UserMounClim();
		BeanUtils.copyProperties(createTravelDTO, userMounClim);
		
		ClimbingTrackDTO climbingTrackDTO = createTravelDTO.getClimbingTrack();
		JSONArray climbingTrackJsonArray = new JSONArray();
		climbingTrackJsonArray.add(JSONObject.fromObject(climbingTrackDTO).toString());
		
		userMounClim.setClimbingTrack(climbingTrackJsonArray.toString());//行程轨迹
		String mounClimId = CommonUtil.uuid();
		userMounClim.setId(mounClimId);
		userMounClim.setUserId(userId);
		userMounClim.setCreateTime(DateTimeUtil.getNowTimestamp());
		moutainClimMapper.insertSelective(userMounClim);
		return mounClimId;
	}


	/**
	 * 更新行程
	 * @param userMounClim - 登山行程信息
	 * @param updateTravelDTO - 行程相关请求参数
	 */
	public void updateTravel(UserMounClim userMounClim, UpdateTravelDTO updateTravelDTO) {
		JSONArray climbingTrackJsonArray = JSONArray.fromObject(userMounClim.getClimbingTrack());
		ClimbingTrackDTO climbingTrackDTO = updateTravelDTO.getClimbingTrack();
		climbingTrackJsonArray.add(JSONObject.fromObject(climbingTrackDTO).toString());
		userMounClim.setUpdateTime(DateTimeUtil.getNowTimestamp());
		userMounClim.setWalkLength(userMounClim.getWalkLength() + updateTravelDTO.getWalkLength());
		userMounClim.setClimblingAltitude(userMounClim.getClimblingAltitude() + updateTravelDTO.getClimblingAltitude());
		userMounClim.setClimbingTrack(climbingTrackJsonArray.toString());
		moutainClimMapper.updateByPrimaryKeySelective(userMounClim);
	}


	public UserMounClim selectByPrimaryKey(String mounClimId) {
		return moutainClimMapper.selectByPrimaryKey(mounClimId);
	}

}
