package com.ylzs.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;


@ApiModel(value="ReleaseTravelDTO",description = "发布行程请求参数")
public class ReleaseTravelDTO {
	
	@ApiModelProperty(value="登山行程id，发布行程必传，只发布动态不传")
	@NotNull(message="登山id不可为空", groups = {Travel.class})
	private String mounClimId;
	
	@ApiModelProperty(value="山峰名称",required=true)
	@NotNull(message="请输入山峰名称", groups = {Travel.class,Dynamic.class})
	private String mountainName;
	
	@ApiModelProperty(value="用户攀登心得和经验",required=true)
	@NotNull(message="请输入攀登心得和经验", groups = {Travel.class,Dynamic.class})
	private String userExperience;
	
	@ApiModelProperty(value="查看类型：1-公开2-仅自己可见3-微信好友可见4-关注好友可见",required=true)
	@NotNull(message="请选择动态可见类型", groups = {Travel.class,Dynamic.class})
	private Integer viewType;//查看类型
	
	@ApiModelProperty(value="view=4时，此值必填")
	private List<String> friendList;
	
	@ApiModelProperty(value="图片id集合，第一张为封面，其余按顺序传入",required=true)
	@NotNull(message="请上传相关图片", groups = {Travel.class,Dynamic.class})
	private List<String> picIdList;
	
	@ApiModelProperty(value="发布位置，中文地址",required=true)
	@NotNull(message="请选择发布位置", groups = {Travel.class,Dynamic.class})
    private String releaseLocation;

	@ApiModelProperty(value="发布位置经度，需和中文地址匹配",required=true)
	@NotNull(message="当前位置经度不可为空", groups = {Travel.class,Dynamic.class})
    private BigDecimal releaseLongitude;

	@ApiModelProperty(value="发布位置纬度，需和中文地址匹配",required=true)
	@NotNull(message="当前位置纬度不可为空", groups = {Travel.class,Dynamic.class})
    private BigDecimal releaseLatitude;

	public String getMounClimId() {
		return mounClimId;
	}

	public void setMounClimId(String mounClimId) {
		this.mounClimId = mounClimId;
	}

	public String getMountainName() {
		return mountainName;
	}

	public void setMountainName(String moutainName) {
		this.mountainName = moutainName;
	}

	public String getUserExperience() {
		return userExperience;
	}

	public void setUserExperience(String userExperience) {
		this.userExperience = userExperience;
	}

	public Integer getViewType() {
		return viewType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}

	public List<String> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<String> friendList) {
		this.friendList = friendList;
	}

	public List<String> getPicIdList() {
		return picIdList;
	}

	public void setPicIdList(List<String> picIdList) {
		this.picIdList = picIdList;
	}

	public String getReleaseLocation() {
		return releaseLocation;
	}

	public void setReleaseLocation(String releaseLocation) {
		this.releaseLocation = releaseLocation;
	}

	public BigDecimal getReleaseLongitude() {
		return releaseLongitude;
	}

	public void setReleaseLongitude(BigDecimal releaseLongitude) {
		this.releaseLongitude = releaseLongitude;
	}

	public BigDecimal getReleaseLatitude() {
		return releaseLatitude;
	}

	public void setReleaseLatitude(BigDecimal releaseLatitude) {
		this.releaseLatitude = releaseLatitude;
	}
	
	public interface Travel{
		
	}
	public interface Dynamic{
		
	}
	}
