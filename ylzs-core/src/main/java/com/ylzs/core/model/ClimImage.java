package com.ylzs.core.model;

import com.ylzs.core.model.base.BaseModel;

public class ClimImage extends BaseModel {
	private static final long serialVersionUID = -2798147735235388817L;

    private String dynamicId;

    private String imageId;
    
    private String imagePath;

    private Integer imageType;

    private Integer imageSort;

	public String getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(String dynamicId) {
		this.dynamicId = dynamicId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
}