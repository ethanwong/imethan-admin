package cn.imethan.security.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cn.imethan.common.entity.BaseEntity;

/**
 * Permission.java
 *
 * @author Ethan Wong
 * @time 2014年3月16日下午4:33:10
 */
@Entity
@Table(name="imethan_security_permission")
@JsonIgnoreProperties(value={"menu"})
public class Permission extends BaseEntity {
	
	private static final long serialVersionUID = 3002097053120526602L;
	
	public static final String AUTHORITY_PREFIX = "ROLE_";
	
	private String name;//权限名称，格式：“资源名称:操作”，形如：“channel:new”
	private String intro;//描述
	private String permission;//权限
	
	@Transient
	public String getPrefixedName() {
		return AUTHORITY_PREFIX + name;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
	@JoinColumn(name="menuId")
	private Menu menu = new Menu();//资源
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
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

}