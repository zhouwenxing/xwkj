package com.ylzs.core.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="ReleaseDynamicDTO",description = "发布动态请求参数")
public class ReleaseDynamicDTO {
	
	@ApiModelProperty(value="行程id,暂时必传，后续可能可以不传",required=true)
	private String mounClimId;
	
	@ApiModelProperty(value="山峰名称,以用户输入为准",required=true)
	private String moutailName;
	
	@ApiModelProperty(value="用户心得与经验",required=true)
	private String userExperience;
	
	@ApiModelProperty(value="图片id,第一张为封面，其余按顺序传入",required=true)
	private List<String> picIdList;

	
	public String getMounClimId() {
		return mounClimId;
	}

	public void setMounClimId(String mounClimId) {
		this.mounClimId = mounClimId;
	}

	public String getMoutailName() {
		return moutailName;
	}

	public void setMoutailName(String moutailName) {
		this.moutailName = moutailName;
	}

	public String getUserExperience() {
		return userExperience;
	}

	public void setUserExperience(String userExperience) {
		this.userExperience = userExperience;
	}

	public List<String> getPicIdList() {
		return picIdList;
	}

	public void setPicIdList(List<String> picIdList) {
		this.picIdList = picIdList;
	}
}
