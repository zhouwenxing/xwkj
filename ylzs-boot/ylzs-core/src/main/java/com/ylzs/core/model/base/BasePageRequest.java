package com.ylzs.core.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页参数")
public class BasePageRequest {
	
	@ApiModelProperty("当前页数，默认1")
	private Integer page = 1;
	@ApiModelProperty("每页显示行数，默认10")
	private Integer rows = 10;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
