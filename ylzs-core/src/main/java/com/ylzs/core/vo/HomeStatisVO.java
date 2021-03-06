package com.ylzs.core.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="HomeStatisVO",description = "首页统计信息")
public class HomeStatisVO {
	
	@ApiModelProperty(value="累计攀登高度(m)")
	private long totalHeight;
	
	@ApiModelProperty(value="累计运动时间(s)")
	private long totalTime;
	
	@ApiModelProperty(value="登山距离(m)")
	private long totalDistance;
	
	@ApiModelProperty(value="累积登山次数")
	private int climbCount;
	
	@ApiModelProperty(value="我的排名")
	private int myRanking;

	public long getTotalHeight() {
		return totalHeight;
	}

	public void setTotalHeight(long totalHeight) {
		this.totalHeight = totalHeight;
	}

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public long getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(long totalDistance) {
		this.totalDistance = totalDistance;
	}

	public int getClimbCount() {
		return climbCount;
	}

	public void setClimbCount(int climbCount) {
		this.climbCount = climbCount;
	}

	public int getMyRanking() {
		return myRanking;
	}

	public void setMyRanking(int myRanking) {
		this.myRanking = myRanking;
	}
}
