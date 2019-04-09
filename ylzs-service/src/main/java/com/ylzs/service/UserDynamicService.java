package com.ylzs.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.ylzs.core.constant.DynamicEnum;
import com.ylzs.core.dto.DynamicListDTO;
import com.ylzs.core.dto.ReleaseTravelDTO;
import com.ylzs.core.mapper.UserDynamicMapper;
import com.ylzs.core.model.UserDynamic;
import com.ylzs.core.vo.DynamicVO;


@Service
public class UserDynamicService {
	@Autowired
	private UserDynamicMapper userdynamicMapper;

	/**
	 * 发布行程
	 * @param userId - 用户id
	 * @param releaseTravelDTO - 发布动态相关参数
	 */
	public void releaseDynamic(String userId, ReleaseTravelDTO releaseTravelDTO) {
		
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

	
	/**
	 * 获取关注好友列表
	 * @param dynamicListDTO - 动态列表参数
	 * @param userId - 用户id
	 * @return
	 */
	public List<DynamicVO> listFriendDynamic(DynamicListDTO dynamicListDTO,String userId) {
		PageHelper.startPage(dynamicListDTO.getPage(), dynamicListDTO.getRows());
		List<DynamicVO> dynamicList = userdynamicMapper.listFriendDynamic(userId);
		//获取图片
		for(DynamicVO dynamicVO : dynamicList){
			//获取动态图片信息
			String dynamicId = dynamicVO.getDynamicId();
			List<String> picList = userdynamicMapper.listDynamicPic(dynamicId);
			dynamicVO.setPicList(picList);
			//redis中获取当前动态发布人头像
			dynamicVO.setHeadPortraitId("");
			//获取评论内容
			List<DynamicVO.CommentInfo> commentList = userdynamicMapper.listDynamicComment(dynamicId);
			dynamicVO.setCommentList(commentList);
		}
		
		return dynamicList;
	}

	/**
	 * 获取动态详情
	 * @param userDynamic - 动态相关信息
	 * @param userId - 用户id
	 * @return
	 */
	public DynamicVO getDetailDynamic(UserDynamic userDynamic, String userId) {
		//校验是否可以查询
		//....
		if(userDynamic.getViewType() == DynamicEnum.VIEW_SELECT_FRIEND.getCode()){//可见类型为关注好友
			
		}
		DynamicVO dynamicVO = new DynamicVO();
		BeanUtils.copyProperties(userDynamic, dynamicVO);
		String dynamicId = userDynamic.getId();
		//获取图片信息
		List<String> picList = userdynamicMapper.listDynamicPic(dynamicId);
		dynamicVO.setPicList(picList);
		//获取评论内容
		List<DynamicVO.CommentInfo> commentList = userdynamicMapper.listDynamicComment(dynamicId);
		dynamicVO.setCommentList(commentList);
		//redis中获取当前动态发布人头像
		dynamicVO.setHeadPortraitId("");
		return dynamicVO;
	}

}
