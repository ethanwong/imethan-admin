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
	
	/*******审计信息*******/
	private String ipAddress;//IP地址
	private String operateUser;//操作用户
	private Date createTime;//创建时间
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
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	
}


