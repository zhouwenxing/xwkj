package com.ylzs.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;


@ApiModel(value="CreateTravelDTO",description = "创建行程请求参数")
public class CreateTravelDTO {
	
	@ApiModelProperty(value="山峰名称，能获取到最好")
	private String mountainName;
	
	@ApiModelProperty(value="山峰经度",required=true)
	@NotNull(message="山峰经度不可为空")
	private BigDecimal mountainLongitude;
	
	@ApiModelProperty(value="山峰纬度",required=true)
	@NotNull(message="山峰经度不可为空")
	private BigDecimal mountainLatitude;
	
	@ApiModelProperty(value="行程轨迹",required=true)
	@NotNull(message="行程轨迹不可为空")
	private ClimbingTrackDTO climbingTrack;
	
	
	public String getMountainName() {
		return mountainName;
	}
	public void setMountainName(String mountainName) {
		this.mountainName = mountainName;
	}
	public BigDecimal getMountainLongitude() {
		return mountainLongitude;
	}
	public void setMountainLongitude(BigDecimal mountainLongitude) {
		this.mountainLongitude = mountainLongitude;
	}
	public BigDecimal getMountainLatitude() {
		return mountainLatitude;
	}
	public void setMountainLatitude(BigDecimal mountainLatitude) {
		this.mountainLatitude = mountainLatitude;
	}
	public ClimbingTrackDTO getClimbingTrack() {
		return climbingTrack;
	}
	public void setClimbingTrack(ClimbingTrackDTO climbingTrack) {
		this.climbingTrack = climbingTrack;
	}
}
