package com.ylzs.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.github.pagehelper.PageInfo;
import com.ylzs.core.constant.DynamicEnum;
import com.ylzs.core.dto.DynamicListDTO;
import com.ylzs.core.model.UserDynamic;
import com.ylzs.core.model.base.CommonResponse;
import com.ylzs.core.model.base.RequestInfo;
import com.ylzs.core.vo.DynamicVO;
import com.ylzs.service.UserDynamicService;
import com.ylzs.web.annotation.Request;


/**
 * 用户动态相关
 * @author Administrator
 */
@RestController
@RequestMapping("/dynamic")
@Api(tags="用户动态Apis")
public class UserDynamicController {
	@Autowired
	private UserDynamicService userDynamicService;
	
	/**
	 * 删除动态
	 * @param request
	 * @param dynamicId
	 */
	@PostMapping(value="/delete")
	@ApiOperation(value="删除动态(可联调3.31)")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="dynamicId",value="动态id",required = true,dataType="string", paramType = "query")
		 })
	public CommonResponse<String> deleteDynamic(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			String dynamicId){
		String userId = (String)session.getAttribute("userId");
		UserDynamic userDynamic = userDynamicService.selectByPrimaryKey(dynamicId);
		if(null == userDynamic){
			return new CommonResponse<String>("fail","动态id非法",null);
		}else if(!userId.equals(userDynamic.getUserId())){
			return new CommonResponse<String>("fail","非法操作",null);
		}
		userDynamic.setDynamicStatus(DynamicEnum.DYNAMIC_DELETE.getCode());
		userDynamicService.updateByPrimaryKeySelective(userDynamic);
		return new CommonResponse<String>("succ");
	}
	
	/**
	 * 获取热门动态列表
	 * @param request
	 * @param dynamicListDTO
	 */
	@PostMapping(value="/hot/list")
	@ApiOperation(value="获取热门动态列表")
	public CommonResponse<DynamicVO> listHotDynamic(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session){
//		String userId = (String)session.getAttribute("userId");
		return new CommonResponse<DynamicVO>("succ");
	}
	
	/**
	 * 获取关注好友动态列表
	 * @param request
	 * @param dynamicListDTO
	 */
	@PostMapping(value="/attention/list")
	@ApiOperation(value="获取关注好友动态列表(可联调3.31)")
	public CommonResponse<PageInfo<DynamicVO>> listFriendDynamic(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			@RequestBody DynamicListDTO dynamicListDTO){
		String userId = (String)session.getAttribute("userId");
		List<DynamicVO> dynamicList = userDynamicService.listFriendDynamic(dynamicListDTO,userId);
		return new CommonResponse<PageInfo<DynamicVO>>("succ",new PageInfo<DynamicVO>(dynamicList));
	}
	
	/**
	 * 查询自己或者他人的动态详情
	 * @param request
	 * @param dynamicId
	 */
	@PostMapping(value="/detail")
	@ApiOperation(value="查询自己或者他人的动态详情(可联调3.31)")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="dynamicId",value="动态id",required = true,dataType="string", paramType = "query")
		 })
	public CommonResponse<DynamicVO> detail(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			String dynamicId){
		if(StringUtils.isBlank(dynamicId)){
			return new CommonResponse<DynamicVO>("fail","动态id不可为空");
		}
		String userId = (String)session.getAttribute("userId");
		UserDynamic userDynamic = userDynamicService.selectByPrimaryKey(dynamicId);
		if(null == userDynamic){
			return new CommonResponse<DynamicVO>("fail","未获取到相关动态信息");
		}
		DynamicVO dynamicVO = userDynamicService.getDetailDynamic(userDynamic,userId);
		return new CommonResponse<DynamicVO>("succ",dynamicVO);
	}
}
