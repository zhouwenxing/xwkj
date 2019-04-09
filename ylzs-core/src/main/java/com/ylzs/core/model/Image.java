package com.ylzs.core.model;

import com.ylzs.core.model.base.BaseModel;

public class Image extends BaseModel {
	private static final long serialVersionUID = -6299973090126039736L;

    private String ext;

    private String fileName;

    private Integer fileSize;

    private Integer fileHeight;

    private Integer fileWidth;

    private String filePath;

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getFileHeight() {
		return fileHeight;
	}

	public void setFileHeight(Integer fileHeight) {
		this.fileHeight = fileHeight;
	}

	public Integer getFileWidth() {
		return fileWidth;
	}

	public void setFileWidth(Integer fileWidth) {
		this.fileWidth = fileWidth;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}