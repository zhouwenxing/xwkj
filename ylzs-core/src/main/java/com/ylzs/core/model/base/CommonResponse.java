package com.ylzs.core.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="返回参数")
public class CommonResponse<T> {
	public static final String SUCCESS = "succ";
	public static final String FAIL = "fail";
	
	@ApiModelProperty(value="succ-成功，fail失败")
	private String code;//响应码 succ/fail
	
	@ApiModelProperty(value="当code=fail，会返回错误信息")
	private String msg;//响应信息
	@ApiModelProperty(value="自定义返回数据")
	private T data;//返回其他自定义信息
	
	public CommonResponse(String code){
		this.code = code;
	}
	
	public CommonResponse(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public CommonResponse(String code,String msg,T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
		
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
