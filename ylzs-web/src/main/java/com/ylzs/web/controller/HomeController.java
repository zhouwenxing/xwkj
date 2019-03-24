package com.ylzs.web.controller;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ylzs.core.model.base.CommonResponse;
import com.ylzs.core.model.base.RequestInfo;
import com.ylzs.core.vo.HomeStatisVO;
import com.ylzs.service.MoutainClimbService;
import com.ylzs.web.annotation.Request;

import springfox.documentation.annotations.ApiIgnore;



@RestController
@RequestMapping("/home")
@Api(tags="首页Apis")
public class HomeController {
	@Autowired
	private MoutainClimbService moutainClimbService;
	
	
	/**
	 * 首页统计接口
	 */
	@GetMapping(value="/statics")
	@ApiOperation(value="首页统计(可联调3.24)")
	public CommonResponse<HomeStatisVO> staticsClimbMountain(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session){
		String userId = (String)session.getAttribute("userId");
		HomeStatisVO homeStatisVO = moutainClimbService.staticsClimbMountain(userId);
		return new CommonResponse<HomeStatisVO>("succ",homeStatisVO);
	}
	
	
	

}
