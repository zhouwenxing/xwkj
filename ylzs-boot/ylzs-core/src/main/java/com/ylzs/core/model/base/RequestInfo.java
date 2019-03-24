package com.ylzs.core.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RequestInfo extends BaseModel{
	private static final long serialVersionUID = -815871416161368071L;
	
	@JsonIgnore
	private String ip;//注册IP
	@JsonIgnore
	private String imei;//设备编码
	@JsonIgnore
	private Integer source;//1-Android 2-ios 3-PC 4-其他
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public void setRequestInfo(RequestInfo requestInfo){
		this.ip = requestInfo.getIp();
		this.imei = requestInfo.getImei();
	}
}
