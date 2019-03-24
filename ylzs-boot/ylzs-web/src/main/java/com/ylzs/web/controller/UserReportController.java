package com.ylzs.web.controller;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.ylzs.core.model.UserReport;
import com.ylzs.core.model.base.CommonResponse;
import com.ylzs.core.model.base.RequestInfo;
import com.ylzs.service.UserReportService;
import com.ylzs.util.CommonUtil;
import com.ylzs.util.DateTimeUtil;
import com.ylzs.web.annotation.Request;



/**
 * 用户举报
 * @author Administrator
 */
@RestController
@RequestMapping("/report")
@Api(tags="用户举报Apis")
public class UserReportController {
	@Autowired
	private UserReportService userReportService;
	
	
	/**
	 * 举报用户
	 * @param request
	 * @param reportUserId
	 * @param reportType
	 */
	@GetMapping(value="/user")
	@ApiOperation(value="举报用户(可联调3.24)")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="reportUserId",value="举报的用户id",required = true,dataType="string", paramType = "query"),
		  @ApiImplicitParam(name="reportType",value="举报类型",required = true,dataType="int", paramType = "query")
		 })
	public CommonResponse<String> reportUser(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			String reportUserId,Integer reportType){
		String userId = (String)session.getAttribute("userId");
		if(StringUtils.isBlank(reportUserId)){
			return new CommonResponse<String>("fail","举报用户id不可为空",null);
		}else if(null == reportType){
			return new CommonResponse<String>("fail","请选择举报类型",null);
		}
		if(userId.equals(reportUserId)){
			return new CommonResponse<String>("fail","不可以举报本人",null);
		}
		//控制举报频率？？.....
		UserReport userReport = new UserReport();
		userReport.setId(CommonUtil.uuid());
		userReport.setUserId(userId);
		userReport.setReportUserId(reportUserId);
		userReport.setReportKind(1);
		userReport.setReportType(reportType);
		userReport.setCreateTime(DateTimeUtil.getNowTimestamp());
		userReportService.insert(userReport);
		return new CommonResponse<String>("succ");
	}
	
	/**
	 * 举报动态
	 * @param request
	 * @param reportUserId
	 * @param reportType
	 */
	@GetMapping(value="/dynamic")
	@ApiOperation(value="举报动态(可联调3.24)")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="dynamicId",value="举报的动态id",required = true,dataType="string", paramType = "query"),
		  @ApiImplicitParam(name="reportType",value="举报类型",required = true,dataType="int", paramType = "query")
		 })
	public CommonResponse<String> reportDynamic(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			String dynamicId,Integer reportType){
		String userId = (String)session.getAttribute("userId");
		if(StringUtils.isBlank(dynamicId)){
			return new CommonResponse<String>("fail","举报动态id不可为空",null);
		}else if(null == reportType){
			return new CommonResponse<String>("fail","请选择举报类型",null);
		}

		//校验动态id是否存在
		//......
		UserReport userReport = new UserReport();
		userReport.setId(CommonUtil.uuid());
		userReport.setUserId(userId);
		userReport.setReportDynamicId(dynamicId);
		userReport.setReportKind(2);
		userReport.setReportType(reportType);
		userReport.setCreateTime(DateTimeUtil.getNowTimestamp());
		userReportService.insert(userReport);
		return new CommonResponse<String>("succ");
	}

}
