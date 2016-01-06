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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.imethan.common.hibernate.BaseEntity;

/**
 * User.java
 *
 * @author Ethan Wong
 * @time 2014年3月16日下午4:32:34
 */
@Entity
@Table(name="imethan_security_user")

//CacheConcurrencyStrategy.NONE 
//CacheConcurrencyStrategy.READ_ONLY，只读模式，在此模式下，如果对数据进行更新操作，会有异常； 
//CacheConcurrencyStrategy.READ_WRITE，读写模式在更新缓存的时候会把缓存里面的数据换成一个锁，其它事务如果去取相应的缓存数据，发现被锁了，直接就去数据库查询； 
//CacheConcurrencyStrategy.NONSTRICT_READ_WRITE，不严格的读写模式则不会的缓存数据加锁； 
//CacheConcurrencyStrategy.TRANSACTIONAL，事务模式指缓存支持事务，当事务回滚时，缓存也能回滚，只支持JTA环境
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="securityCache") 
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
	@Transient
	private String roleName;//角色名称
	
	public Long getRoleIdForInput(){
		if(roles != null && !roles.isEmpty()){
			return roles.iterator().next().getId();
		}else{
			return roleId;
		} 
	}

	public String getRoleName() {
		if(roles != null && !roles.isEmpty()){
			return roles.iterator().next().getName();
		}else{
			return roleName;
		}
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
//		if(roles != null && !roles.isEmpty()){
//			return roles.iterator().next().getId();
//		}else{
			return roleId;
//		}
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