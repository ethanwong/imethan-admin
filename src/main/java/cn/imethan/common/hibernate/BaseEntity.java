package cn.imethan.common.hibernate;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.imethan.common.utils.CustomDateSerializer;

/**
 * BaseEntity.java
 *
 * @author Ethan Wong
 * @time 2014年3月14日下午8:23:18
 */
@MappedSuperclass 
public class BaseEntity extends IdEntity{
	
	private static final long serialVersionUID = -8052135027431298206L;
	
	private Date createTime = new Date();//创建时间
	private Date modifyTime;//更新时间
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}


