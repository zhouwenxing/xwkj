package com.ylzs.web.controller;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ylzs.core.dto.DynamicCommentDTO;
import com.ylzs.core.model.DynamicComment;
import com.ylzs.core.model.UserDynamic;
import com.ylzs.core.model.base.CommonResponse;
import com.ylzs.core.model.base.RequestInfo;
import com.ylzs.service.DynamicCommentService;
import com.ylzs.service.UserDynamicService;
import com.ylzs.util.DateTimeUtil;
import com.ylzs.web.annotation.Request;

import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/zone/comment")
@Api(tags="动态评论Apis")
public class DynamicCommentController {
	@Autowired
	private DynamicCommentService dynamicCommentService;
	@Autowired
	private UserDynamicService userDynamicService;
	
	/**
	 * 发布评论
	 * @param request
	 * @param dynamicCommentDTO
	 */
	@PostMapping(value="/release")
	@ApiOperation(value="发布评论(可联调3.24)")
	public CommonResponse<String> releaseDynamic(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			@Validated @RequestBody DynamicCommentDTO dynamicCommentDTO,@ApiIgnore BindingResult result){
		if (result.hasErrors()){
			return new CommonResponse<String>("fail", result.getFieldErrors().get(0).getDefaultMessage(),null);
		}
		
		UserDynamic userDynamic = userDynamicService.selectByPrimaryKey(dynamicCommentDTO.getDynamicId());
		if(null == userDynamic){
			return new CommonResponse<String>("fail","非法请求",null);
		}else if(userDynamic.getDynamicStatus() != 2){
			return new CommonResponse<String>("fail","非法操作",null);
		}
		
		String userId = (String)session.getAttribute("userId");
		dynamicCommentService.releaseComment(userId,dynamicCommentDTO);
		return new CommonResponse<String>("succ");
	}
	
	/**
	 * 删除评论
	 * @param request
	 * @param commentId
	 */
	@GetMapping(value="/delete")
	@ApiOperation(value="删除评论(可联调3.24)")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="commentId",value="评论id",required = true,dataType="string", paramType = "query")
		 })
	public CommonResponse<String> releaseDynamic(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			String commentId){
		if(StringUtils.isBlank(commentId)){
			return new CommonResponse<String>("fail","评论id不可为空",null);
		}
		String userId = (String)session.getAttribute("userId");
		DynamicComment dynamicComment = dynamicCommentService.selectByPrimaryKey(commentId);
		if(null == dynamicComment){
			return new CommonResponse<String>("fail","该动态不存在",null);
		}else if(!dynamicComment.getCommentUserId().equals(userId)){
			return new CommonResponse<String>("fail","只能删除本人的评论",null);
		}
		
		dynamicComment.setCommentStatus(0);
		dynamicComment.setUpdateTime(DateTimeUtil.getNowTimestamp());
		dynamicCommentService.updateByPrimaryKeySelective(dynamicComment);
		return new CommonResponse<String>("succ");
	}

}
