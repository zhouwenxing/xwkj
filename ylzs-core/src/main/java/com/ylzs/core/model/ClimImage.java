package com.ylzs.core.model;

import java.sql.Timestamp;
import com.ylzs.core.model.base.RequestInfo;

public class ClimImage extends RequestInfo {
	private static final long serialVersionUID = -2798147735235388817L;

    private String mounClimId;

    private String imageId;

    private Integer imageType;

    private Integer imageSort;

    private Timestamp updateTime;

	public String getMounClimId() {
		return mounClimId;
	}

	public void setMounClimId(String mounClimId) {
		this.mounClimId = mounClimId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public Integer getImageType() {
		return imageType;
	}

	public void setImageType(Integer imageType) {
		this.imageType = imageType;
	}

	public Integer getImageSort() {
		return imageSort;
	}

	public void setImageSort(Integer imageSort) {
		this.imageSort = imageSort;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}