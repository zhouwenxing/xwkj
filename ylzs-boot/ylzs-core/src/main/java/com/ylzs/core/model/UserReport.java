package com.ylzs.core.model;

import com.ylzs.core.model.base.RequestInfo;

public class UserReport extends RequestInfo {
	private static final long serialVersionUID = -1073970019359401718L;

    private String userId;

    private String reportUserId;

    private String reportDynamicId;

    private Integer reportKind;

    private Integer reportType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}

	public String getReportDynamicId() {
		return reportDynamicId;
	}

	public void setReportDynamicId(String reportDynamicId) {
		this.reportDynamicId = reportDynamicId;
	}

	public Integer getReportKind() {
		return reportKind;
	}

	public void setReportKind(Integer reportKind) {
		this.reportKind = reportKind;
	}

	public Integer getReportType() {
		return reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}
}