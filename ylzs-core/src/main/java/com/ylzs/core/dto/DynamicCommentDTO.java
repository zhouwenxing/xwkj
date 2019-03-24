package com.ylzs.core.dto;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="DynamicCommentDTO",description = "动态评论请求参数")
public class DynamicCommentDTO {
	
	@ApiModelProperty(value="动态id",required=true)
	@NotBlank(message="动态id不可为空")
	private String dynamicId;
	
	@ApiModelProperty(value="评论内容",required=true)
	@NotBlank(message="请输入内容")
	private String commentContent;

	public String getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(String dynamicId) {
		this.dynamicId = dynamicId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
}
