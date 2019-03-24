package com.ylzs.core.model.base;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    private String id;
	private Timestamp createTime;//创建时间
	private String remark;//备注
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseModel other = (BaseModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
