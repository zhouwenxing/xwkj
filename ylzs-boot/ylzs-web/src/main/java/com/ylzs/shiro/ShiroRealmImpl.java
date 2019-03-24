package com.ylzs.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylzs.redis.RedisService;

@Service("myShiroRealm") 
public class ShiroRealmImpl extends AuthorizingRealm{
	@Autowired
	private RedisService<Object> redisService;

	 /** 
     * 验证当前登录的Subject 
     * 执行Subject.login()时会调用此方法
     */ 
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//UsernamePasswordToken对象用来存放提交的登录信息 
		UsernamePasswordToken usernamePasswordToke = (UsernamePasswordToken) token;
		String userName = usernamePasswordToke.getUsername();
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userName, userName, userName);
        return authcInfo;  
	}

	/**
	 * 权限控制
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}

}
