package cn.imethan.security.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.imethan.common.entity.BaseEntity;

/**
 * Permission.java
 *
 * @author Ethan Wong
 * @time 2014年3月16日下午4:33:10
 */
@Entity
@Table(name="imethan_security_permission")
@JsonIgnoreProperties(value={"resource"})
public class Permission extends BaseEntity {
	
	private static final long serialVersionUID = 3002097053120526602L;
	
	private String name;//权限名称，格式：“资源名称:操作”，形如：“channel:new”
	private String intro;//描述
	private String permission;//权限
	
	@ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
	@JoinColumn(name="resourceId")
	private Resource resource = new Resource();//资源
	
	public Resource getResource() {
		return resource;
	}
	public void setResources(Resource resource) {
		this.resource = resource;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}