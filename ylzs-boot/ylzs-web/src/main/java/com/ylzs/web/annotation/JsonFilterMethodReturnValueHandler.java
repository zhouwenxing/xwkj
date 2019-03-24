package com.ylzs.web.annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JsonFilterMethodReturnValueHandler implements
		HandlerMethodReturnValueHandler {

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		//如果有我们自定义的JsonFilter注解 就用我们这个Handler来处理
		 boolean hasJsonAnno = null != returnType.getMethodAnnotation(JsonFilter.class);
	     return hasJsonAnno;
	}

	@Override
	public void handleReturnValue(Object returnValue,
			MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		mavContainer.setRequestHandled(true);//设置这个就是最终的处理类了，处理完不再去找下一个类进行处理
		// 获得注解并执行filter方法 最后返回
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
	    Annotation[] annos = returnType.getMethodAnnotations();
	    CustomerJsonSerializer jsonSerializer = new CustomerJsonSerializer();
	    List<Annotation> list = Arrays.asList(annos);
	    for(Annotation a : list){
	    	if (a instanceof JSON) {
	    		JsonFilter jsonFilter = (JsonFilter) a;
		        jsonSerializer.filter(jsonFilter.type(), jsonFilter.include(), jsonFilter.filter());
		      }
	    }
	    
	    //java8 -->lambda表达式
//	    Arrays.asList(annos).forEach(a ->{
//	      if (a instanceof JSON) {
//	        JsonFilter jsonFilter = (JsonFilter) a;
//	        jsonSerializer.filter(jsonFilter.type(), jsonFilter.include(), jsonFilter.filter());
//	      }
//	    });
	    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
	    String json = jsonSerializer.toJson(returnValue);
	    response.getWriter().write(json);
	}
}
