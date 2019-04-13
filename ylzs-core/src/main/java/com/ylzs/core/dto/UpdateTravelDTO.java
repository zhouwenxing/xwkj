package com.ylzs.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@ApiModel(value="UpdateTravelDTO",description = "更新行程请求参数")
public class UpdateTravelDTO {
	
	@ApiModelProperty(value="登山行程id",required=true)
	private String mounClimId;
	
	@ApiModelProperty(value="当前更新时间段内行走距离",required=true)
	@Min(value=0)
	private Integer walkLength;
	
	@ApiModelProperty(value="当前更新时间段内攀登海拔",required=true)
	@Min(value=0)
	private Integer climblingAltitude;
	
	@ApiModelProperty(value="行程轨迹，必须为json数组,可以多个轨迹点一起传",required=true)
	@NotNull(message="行程轨迹不可为空")
	private ClimbingTrackDTO climbingTrack;

	public String getMounClimId() {
		return mounClimId;
	}

	public void setMounClimId(String mounClimId) {
		this.mounClimId = mounClimId;
	}

	public Integer getWalkLength() {
		return walkLength;
	}

	public void setWalkLength(Integer walkLength) {
		this.walkLength = walkLength;
	}

	public Integer getClimblingAltitude() {
		return climblingAltitude;
	}

	public void setClimblingAltitude(Integer climblingAltitude) {
		this.climblingAltitude = climblingAltitude;
	}

	public ClimbingTrackDTO getClimbingTrack() {
		return climbingTrack;
	}

	public void setClimbingTrack(ClimbingTrackDTO climbingTrack) {
		this.climbingTrack = climbingTrack;
	}
}
