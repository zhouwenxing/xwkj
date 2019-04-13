package com.ylzs.core.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.ylzs.core.model.base.RequestInfo;

public class UserDynamic extends RequestInfo {
	private static final long serialVersionUID = -5596852894983379356L;

    private String mounClimId;
    
    private String mountainName;

    private String userExperience;

    private Integer climblingAltitude;
    
    private String userId;

    private Integer viewType;

    private String releaseLocation;

    private BigDecimal releaseLongitude;

    private BigDecimal releaseLatitude;

    private Integer dynamicStatus;

    private Timestamp updateTime;
    
    private Integer viemTimes;

	public String getMounClimId() {
		return mounClimId;
	}

	public void setMounClimId(String mounClimId) {
		this.mounClimId = mounClimId;
	}

	public String getMountainName() {
		return mountainName;
	}

	public void setMountainName(String mountainName) {
		this.mountainName = mountainName;
	}

	public String getUserExperience() {
		return userExperience;
	}

	public void setUserExperience(String userExperience) {
		this.userExperience = userExperience;
	}

	public Integer getClimblingAltitude() {
		return climblingAltitude;
	}

	public void setClimblingAltitude(Integer climblingAltitude) {
		this.climblingAltitude = climblingAltitude;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getViewType() {
		return viewType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
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

	public Integer getDynamicStatus() {
		return dynamicStatus;
	}

	public void setDynamicStatus(Integer dynamicStatus) {
		this.dynamicStatus = dynamicStatus;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getViemTimes() {
		return viemTimes;
	}

	public void setViemTimes(Integer viemTimes) {
		this.viemTimes = viemTimes;
	}
	
}