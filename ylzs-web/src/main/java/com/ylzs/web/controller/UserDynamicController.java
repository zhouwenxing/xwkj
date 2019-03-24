package com.ylzs.web.controller;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.ylzs.core.dto.ReleaseDynamicDTO;
import com.ylzs.core.model.UserDynamic;
import com.ylzs.core.model.base.CommonResponse;
import com.ylzs.core.model.base.RequestInfo;
import com.ylzs.service.UserDynamicService;
import com.ylzs.web.annotation.Request;


/**
 * 用户动态相关
 * @author Administrator
 */
@RestController
@RequestMapping("/user/dynamic")
@Api(tags="用户动态Apis")
public class UserDynamicController {
	@Autowired
	private UserDynamicService userDynamicService;
	
	/**
	 * 发布行程
	 * @param request
	 * @param nickName
	 */
	@PostMapping(value="/dynamic/release")
	@ApiOperation(value="发布动态")
//	@ApiImplicitParams({
//		  @ApiImplicitParam(name="nickName",value="新昵称",required = true,dataType="string", paramType = "query")
//		 })
	public CommonResponse<String> releaseDynamic(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			ReleaseDynamicDTO releaseZoneDTO){
		String userId = (String)session.getAttribute("userId");
		userDynamicService.releaseDynamic(userId,releaseZoneDTO);
		return new CommonResponse<String>("succ");
	}
	
	
	/**
	 * 发布行程
	 * @param request
	 * @param nickName
	 */
	@PostMapping(value="/delete")
	@ApiOperation(value="删除动态")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="dynamicId",value="动态id",required = true,dataType="string", paramType = "query")
		 })
	public CommonResponse<String> deleteDynamic(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			String dynamicId){
		String userId = (String)session.getAttribute("userId");
		UserDynamic userZone = new UserDynamic();
		userZone.setId(dynamicId);
//		userZone.setZoneStatus(0);
		userDynamicService.updateByPrimaryKeySelective(userZone);
		return new CommonResponse<String>("succ");
	}
	
	
	
	
	
	

}
