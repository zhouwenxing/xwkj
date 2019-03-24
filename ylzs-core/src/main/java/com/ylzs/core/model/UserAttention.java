package com.ylzs.core.model;

import java.sql.Timestamp;

import com.ylzs.core.model.base.RequestInfo;

public class UserAttention extends RequestInfo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4056342500217807108L;

	private String userId;

    private String attentionUserId;

    private Integer attentionStatus;
    
    private Timestamp updateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAttentionUserId() {
        return attentionUserId;
    }

    public void setAttentionUserId(String attentionUserId) {
        this.attentionUserId = attentionUserId == null ? null : attentionUserId.trim();
    }

	public Integer getAttentionStatus() {
		return attentionStatus;
	}

	public void setAttentionStatus(Integer attentionStatus) {
		this.attentionStatus = attentionStatus;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}