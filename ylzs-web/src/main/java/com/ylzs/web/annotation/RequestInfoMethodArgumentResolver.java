package com.ylzs.web.annotation;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ylzs.core.constant.CommonConstant;
import com.ylzs.core.model.base.RequestInfo;

/**
 * 用于绑定@UserIp的方法参数解析器
 */
public class RequestInfoMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(Request.class)) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String userAgent = webRequest.getHeader("User-Agent");
//		UserAgent agent = UserAgent.parseUserAgentString(userAgent);
		RequestInfo requestInfo = new RequestInfo();
		requestInfo.setImei(webRequest.getParameter("imei"));
		requestInfo.setIp(getIpAddr(webRequest));
		if(userAgent.toLowerCase().contains("android")){
			requestInfo.setSource(CommonConstant.SOURCE_ANDROID);
		}else if(userAgent.toLowerCase().contains("ios")){
			requestInfo.setSource(CommonConstant.SOURCE_IOS);
		}else{
			requestInfo.setSource(CommonConstant.SOURCE_OTHER);
		}
		return requestInfo;
	}
	private  String getIpAddr(NativeWebRequest webRequest) {
		String ip = webRequest.getHeader("X-Real-IP");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = webRequest.getHeader("x-forwarded-for");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = webRequest.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = webRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			//ip = SecurityUtils.getSubject().getSession().getHost();

		}
		return ip;
	}
}
