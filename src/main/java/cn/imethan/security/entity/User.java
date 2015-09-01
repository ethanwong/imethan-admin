package cn.imethan.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.imethan.common.entity.BaseEntity;

/**
 * User.java
 *
 * @author Ethan Wong
 * @time 2014年3月16日下午4:32:34
 */
@Entity
@Table(name="imethan_security_user")
public class User extends BaseEntity {
	
	private static final long serialVersionUID = 2732105841282347957L;
	
	private String username;//账号
	private String password;//密码
	private String nickname;//昵称
	
	private String email;//Email
	private String phone;//手机号码
	private String locate;//位置
	private String avatar;//头像
	private String qq;//QQ
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="imethan_security_user_role",joinColumns = { @JoinColumn(name ="userId" )} ,inverseJoinColumns = { @JoinColumn(name = "roleId")})
	@OrderBy("id")
	private Set<Role> roles = new HashSet<Role>();//角色
	
	@Transient
	private Long roleId;//角色ID

	public Long getRoleId() {
		if(roles != null && !roles.isEmpty()){
			return roles.iterator().next().getId();
		}else{
			return roleId;
		}
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocate() {
		return locate;
	}

	public void setLocate(String locate) {
		this.locate = locate;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}



}