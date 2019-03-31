package com.ylzs.core.constant;

public enum DynamicEnum {
	
	//动态可见类型
	VIEW_OPEN(1, "公开"), 
	VIEW_ONLY_SELF(2, "仅自己可见"), 
	VIEW_WECHAT_FRIEND(3, "微信好友可见"), 
	VIEW_ATTENTION_FRIEND(4, "关注好友可见"),
	VIEW_SELECT_FRIEND(5, "指定好友可见"),
	
	//动态类型
	DYNAMIC_DRAFT(51, "暂存"), 
	DYNAMIC_RELEASE(52, "已发布"), 
	DYNAMIC_DELETE(53, "已删除"),
	
	FRIEND_VISIBLE(71, "好友可见"), 
	FRIEND_NOT_VISIBLE(72, "好友不可见");

	private DynamicEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	private int code;
	private String value;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue(int viewType) {
		for (DynamicEnum dynamicEnum : DynamicEnum.values()) {
			if (dynamicEnum.getValue().equals(this.value)) {
				return dynamicEnum.getValue();
			}
		}
		return "";
	}

}
