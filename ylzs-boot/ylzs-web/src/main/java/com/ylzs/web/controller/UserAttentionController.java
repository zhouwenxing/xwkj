package com.ylzs.web.controller;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.ylzs.core.model.UserAttention;
import com.ylzs.core.model.base.CommonResponse;
import com.ylzs.core.model.base.RequestInfo;
import com.ylzs.service.UserAttentionService;
import com.ylzs.web.annotation.Request;


/**
 * 用户关注相关
 * @author Administrator
 */
@RestController
@RequestMapping("/user/attention")
@Api(tags="用户关注Apis")
public class UserAttentionController {
	@Autowired
	private UserAttentionService userAttentionService;
	
	/**
	 * 添加关注好友
	 * @param request
	 * @param attentionUserId
	 */
	@GetMapping(value="/add")
	@ApiOperation(value="添加好友关注(可联调3.24)")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="attentionUserId",value="关注的用户id",required = true,dataType="string", paramType = "query")
		 })
	public CommonResponse<String> addAttention(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			String attentionUserId){
		String userId = (String)session.getAttribute("userId");
		UserAttention userAttention = userAttentionService.queryAttentionInfo(userId,attentionUserId);
		if(null != userAttention && userAttention.getAttentionStatus() == 1){
			return new CommonResponse<String>("fail","您已关注该用户，请不要重复操作!",null);
		}
		//校验attentionUserId是否存在
		//...
		userAttentionService.addAttentionUser(userAttention,userId,attentionUserId);
		return new CommonResponse<String>("succ");
	}
	
	
	/**
	 * 取消关注好友
	 * @param request
	 * @param attentionUserId
	 */
	@GetMapping(value="/cancel")
	@ApiOperation(value="取消关注好友(可联调3.24)")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="attentionUserId",value="取消关注的用户id",required = true,dataType="string", paramType = "query")
		 })
	public CommonResponse<String> cancelAttention(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			String attentionUserId){
		String userId = (String)session.getAttribute("userId");
		UserAttention userAttention = userAttentionService.queryAttentionInfo(userId,attentionUserId);
		if(null == userAttention || userAttention.getAttentionStatus() == 0){
			return new CommonResponse<String>("fail","您尚未关注该用户!",null);
		}
		//校验attentionUserId是否存在
		//...
		userAttentionService.cancelAttentionUser(userAttention);
		return new CommonResponse<String>("succ");
	}


}
