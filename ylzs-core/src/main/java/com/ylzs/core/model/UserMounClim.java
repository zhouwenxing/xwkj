package com.ylzs.core.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.ylzs.core.model.base.RequestInfo;

public class UserMounClim extends RequestInfo{
	private static final long serialVersionUID = 7152891736318744033L;

    private String userId;

    private String mountainName;

    private BigDecimal mountainLongitude;

    private BigDecimal mountainLatitude;

    private String userExperience;

    private Integer walkLength;

    private Integer climblingAltitude;

    private Integer upAltitude;

    private Integer climblingTime;

    private String climbingTrack;//jsonArr
    
    private Timestamp updateTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public String getUserExperience() {
		return userExperience;
	}

	public void setUserExperience(String userExperience) {
		this.userExperience = userExperience;
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

	public Integer getUpAltitude() {
		return upAltitude;
	}

	public void setUpAltitude(Integer upAltitude) {
		this.upAltitude = upAltitude;
	}

	public Integer getClimblingTime() {
		return climblingTime;
	}

	public void setClimblingTime(Integer climblingTime) {
		this.climblingTime = climblingTime;
	}

	public String getClimbingTrack() {
		return climbingTrack;
	}

	public void setClimbingTrack(String climbingTrack) {
		this.climbingTrack = climbingTrack;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}