package com.ylzs.core.model;

import java.sql.Timestamp;

import com.ylzs.core.model.base.RequestInfo;

public class User extends RequestInfo {
	private static final long serialVersionUID = 5284329871696865507L;

	private String unionid;

    private String nickName;

    private Integer userLevel;

    private String headPortraitId;

    private String dynamicCoverId;

    private Integer totalTime;

    private Integer totalDistance;

    private Integer totalAltitude;
    
    private Timestamp updateTime;//更新时间

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getHeadPortraitId() {
		return headPortraitId;
	}

	public void setHeadPortraitId(String headPortraitId) {
		this.headPortraitId = headPortraitId;
	}

	public String getDynamicCoverId() {
		return dynamicCoverId;
	}

	public void setDynamicCoverId(String dynamicCoverId) {
		this.dynamicCoverId = dynamicCoverId;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Integer totalDistance) {
		this.totalDistance = totalDistance;
	}

	public Integer getTotalAltitude() {
		return totalAltitude;
	}

	public void setTotalAltitude(Integer totalAltitude) {
		this.totalAltitude = totalAltitude;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	
}