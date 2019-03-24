package com.ylzs.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

@ApiModel(value="ClimbingTrackDTO",description = "行程轨迹")
public class ClimbingTrackDTO {
	
	@ApiModelProperty(value="当前位置经度",required=true)
	@NotNull(message="当前位置经度不可为空")
	private BigDecimal longitude;
	
	@ApiModelProperty(value="当前位置纬度",required=true)
	@NotNull(message="当前位置纬度不可为空")
	private BigDecimal latitude;
	
	@ApiModelProperty(value="当前位置海拔",required=true)
	@NotNull(message="当前位置海拔不可为空")
	private Integer altitude;

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public Integer getAltitude() {
		return altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}

}
