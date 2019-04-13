package com.ylzs.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import com.ylzs.core.model.User;
import com.ylzs.core.model.UserWechat;
import com.ylzs.core.model.base.CommonResponse;
import com.ylzs.core.model.base.RequestInfo;
import com.ylzs.service.UserService;
import com.ylzs.util.EmojiUtil;
import com.ylzs.web.annotation.Request;


/**
 * 用户相关
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
@Api(tags="用户相关Apis")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);	
	
	@Autowired
	private UserService userService;
	/**
	 * 微信登录
	 * @param request
	 * @param userWechat
	 */
	@PostMapping(value="/wechat/login",params = {"!id"})
	@ApiOperation(value="微信登录")
	public CommonResponse<User> loginByWechat(@Request RequestInfo requestInfo,HttpSession session,UserWechat userWechat){
		String nickName = userWechat.getNickname();
		String country = userWechat.getCountry();
		String province = userWechat.getProvince();
		String city = userWechat.getCity();
		
		nickName = EmojiUtil.filterEmoji(nickName);//过滤emoji表情
		country = EmojiUtil.filterEmoji(country);//过滤emoji表情
		province = EmojiUtil.filterEmoji(province);//过滤emoji表情
		city = EmojiUtil.filterEmoji(city);//过滤emoji表情
		userWechat.setNickname(nickName);
		userWechat.setCountry(country);
		userWechat.setProvince(province);
		userWechat.setCity(city);
		
		User user = new User();
		String userId = user.getId();
		
		//创建完成之后手动执行登录，将session交给shrio管理
		UsernamePasswordToken token = new UsernamePasswordToken(userId, userId);
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject();// 获取当前用户
		// 开始验证，调用login方法时将会自动调用MyRealm中的权限认证类中方法
		try {
			currentUser.login(token);
			session.setAttribute("userId", userId);
		} catch (Exception e) {// 所有shrio登录异常统一处理
			logger.error("登录失败");
			e.printStackTrace();
		}
		token.clear();
		return new CommonResponse<User>("succ");
	}
	
	
	/**
	 * 修改昵称
	 * @param request
	 * @param nickName
	 */
	@GetMapping(value="/nickName/update")
	@ApiOperation(value="修改昵称(可联调3.24)")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="nickName",value="新昵称",required = true,dataType="string", paramType = "query")
		 })
	public CommonResponse<String> updateNickName(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			 String nickName){
		if(!StringUtils.isBlank(nickName)){
			return new CommonResponse<String>("fail","请输入昵称",null);
		}
		String userId = (String)session.getAttribute("userId");
		User user = new User();
		user.setId(userId);
		user.setNickName(nickName);
		userService.updateByPrimaryKeySelective(user);
		return new CommonResponse<String>("succ");
	}
	
	
	/**
	 * 上传头像
	 * @param request
	 * @param headId
	 */
	@PostMapping(value="/headPhoto/upload")
	@ApiOperation(value="上传头像(可联调4.7)")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="headId",value="头像图片id",required = true,dataType="string", paramType = "query")
		 })
	public CommonResponse<String> upload(@ApiIgnore @Request RequestInfo requestInfo,@ApiIgnore HttpSession session,
			String headId){
		if(StringUtils.isBlank(headId)){
			return new CommonResponse<String>("fail","头像id不可为空",null);
		}
		String userId = (String)session.getAttribute("userId");
		User user = new User();
		user.setId(userId);
		user.setHeadPortraitId(headId);
		userService.updateByPrimaryKeySelective(user);
		return new CommonResponse<String>("succ","上传成功",headId);
	}
}
