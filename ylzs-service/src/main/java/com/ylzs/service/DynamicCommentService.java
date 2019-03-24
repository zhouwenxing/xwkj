package com.ylzs.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylzs.core.dto.DynamicCommentDTO;
import com.ylzs.core.mapper.DynamicCommentMapper;
import com.ylzs.core.model.DynamicComment;
import com.ylzs.util.DateTimeUtil;


@Service
public class DynamicCommentService {
	@Autowired
	private DynamicCommentMapper dynamicCommentMapper;

	/**
	 * 发表评论
	 * @param userId
	 * @param dynamicCommentDTO
	 */
	public void releaseComment(String userId, DynamicCommentDTO dynamicCommentDTO) {
		DynamicComment dynamicComment = new DynamicComment();
		BeanUtils.copyProperties(dynamicCommentDTO, dynamicComment);
		dynamicComment.setCommentUserId(userId);
		dynamicComment.setCommentStatus(1);
		dynamicComment.setCreateTime(DateTimeUtil.getNowTimestamp());
		dynamicCommentMapper.insert(dynamicComment);
		
	}

	
	public void updateByPrimaryKeySelective(DynamicComment zoneComment) {
		dynamicCommentMapper.updateByPrimaryKeySelective(zoneComment);		
	}


	public DynamicComment selectByPrimaryKey(String commentId) {
		return dynamicCommentMapper.selectByPrimaryKey(commentId);
	}

}
