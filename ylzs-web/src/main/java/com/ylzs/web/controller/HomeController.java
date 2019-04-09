package com.ylzs.web.controller;

import java.util.List;

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
import com.ylzs.service.UserRankService;
import com.ylzs.service.UserService;
import com.ylzs.web.annotation.Request;

import springfox.documentation.annotations.ApiIgnore;



@RestController
@RequestMapping("/home")
@Api(tags="首页Apis")
public class HomeController {
	@Autowired
	private MoutainClimbService moutainClimbService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRankService userRankService;
	
	
	/**
	 * 首页统计接口
	 */
	@GetMapping(value="/statics")
	@ApiOperation(value="首页统计(可联调3.24，4.7修改)")
	public CommonResponse<HomeStatisVO> staticsClimbMountain(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session){
		String userId = (String)session.getAttribute("userId");
		HomeStatisVO homeStatisVO = moutainClimbService.staticsClimbMountain(userId);
		return new CommonResponse<HomeStatisVO>("succ",homeStatisVO);
	}
	
	
	/**
	 * 首页头像列表
	 */
	@GetMapping(value="/user/list")
	@ApiOperation(value="首页头像列表(可联调4.7)")
	public CommonResponse<List<String>> userList(@ApiIgnore HttpSession session){
		String userId = (String)session.getAttribute("userId");
		//获取我的用户头像
		String headPath = userService.getHeadPathByUserId(userId);
		//获取日榜中好友排名前2的
		List<String> list = userRankService.getTop2HeadPath(userId);
		list.set(0, headPath);
		return new CommonResponse<List<String>>("succ","",list);
	}
	

}
