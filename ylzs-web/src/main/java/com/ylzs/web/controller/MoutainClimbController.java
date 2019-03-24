package com.ylzs.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.ylzs.core.dto.CreateTravelDTO;
import com.ylzs.core.dto.ReleaseTravelDTO;
import com.ylzs.core.dto.UpdateTravelDTO;
import com.ylzs.core.model.UserMounClim;
import com.ylzs.core.model.base.CommonResponse;
import com.ylzs.core.model.base.RequestInfo;
import com.ylzs.service.MoutainClimbService;
import com.ylzs.web.annotation.Request;


@RestController
@RequestMapping("/moutain")
@Api(tags="登山相关Apis")
public class MoutainClimbController {
	@Autowired
	private MoutainClimbService moutainClimbService;
	
	/**
	 * 创建行程
	 * @param request
	 * @param createTravelDTO
	 */
	@PostMapping(value="/travel/create")
	@ApiOperation(value="创建行程(可联调3.24)")
	public CommonResponse<String> createTravel(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			@Validated @RequestBody CreateTravelDTO createTravelDTO,@ApiIgnore BindingResult result){
		if (result.hasErrors()){
			return new CommonResponse<String>("fail", result.getFieldErrors().get(0).getDefaultMessage(),null);
		}
		String userId = (String)session.getAttribute("userId");
		String mounClimId = moutainClimbService.createTravel(userId,createTravelDTO);
		return new CommonResponse<String>("succ",null,mounClimId);
	}
	
	/**
	 * 更新行程
	 * @param request
	 * @param updateTravelDTO
	 */
	@PostMapping(value="/travel/update")
	@ApiOperation(value="更新行程,暂定5分钟更新一次(可联调3.24)")
	public CommonResponse<String> updateTravel(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			@Validated @RequestBody UpdateTravelDTO updateTravelDTO,@ApiIgnore BindingResult result){
		if (result.hasErrors()){
			return new CommonResponse<String>("fail", result.getFieldErrors().get(0).getDefaultMessage(),null);
		}
		String userId = (String)session.getAttribute("userId");
		UserMounClim userMounClim = moutainClimbService.selectByPrimaryKey(updateTravelDTO.getMounClimId());
		
		if(!userMounClim.getUserId().equals(userId)){
			return new CommonResponse<String>("fail","非法请求",null);
		}
		
		moutainClimbService.updateTravel(userMounClim,updateTravelDTO);
		return new CommonResponse<String>("succ");
	}
	
	/**
	 * 发布行程
	 * @param request
	 * @param releaseTravelDTO
	 */
	@PostMapping(value="/travel/release")
	@ApiOperation(value="发布行程(可联调3.24)")
	public CommonResponse<String> releaseTravel(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			@Validated @RequestBody ReleaseTravelDTO releaseTravelDTO,@ApiIgnore BindingResult result){
		if (result.hasErrors()){
			return new CommonResponse<String>("fail", result.getFieldErrors().get(0).getDefaultMessage(),null);
		}
		String userId = (String)session.getAttribute("userId");
		UserMounClim userMounClim = moutainClimbService.selectByPrimaryKey(releaseTravelDTO.getMounClimId());
		
		if(!userMounClim.getUserId().equals(userId)){
			return new CommonResponse<String>("fail","非法请求",null);
		}
		if(releaseTravelDTO.getViewType() == 4){
			List<String> friendList = releaseTravelDTO.getFriendList();
			if(null == friendList || friendList.size() == 0){
				return new CommonResponse<String>("fail","请选择可见关注好友",null);
			}
		}
		moutainClimbService.realeaseTravel(userMounClim,releaseTravelDTO);
		return new CommonResponse<String>("succ");
	}

}
