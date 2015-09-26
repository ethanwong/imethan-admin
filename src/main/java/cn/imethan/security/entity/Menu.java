package cn.imethan.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.internal.NotNull;

import cn.imethan.common.entity.BaseEntity;

/**
 * Resource.java
 *
 * @author Ethan Wong
 * @time 2014年3月17日下午10:01:41
 */
@Entity
@Table(name="imethan_security_menu")
@JsonIgnoreProperties(value={"parent","roles","modifyTime","createTime","url"})
public class Menu extends BaseEntity {
	
	private static final long serialVersionUID = 6701956302298630995L;
	
	@NotNull
	@Size(min=1, max=20,message="name must be between 4 and 20")
	private String name;//名称
	
	@NotNull
	@Size(min=1,message="module must not by null")
	private String module;//模块名称
	
	@NotNull
	@Size(min=1,message="url must not by null")
	private String url;//URL
	
	@NotNull
	@Size(min=1,message="intro must not by null")
	private String intro;//描述
	
	private boolean isRoot;//是否是根节点
	
	@Transient
	private boolean open = true;//节点是否打开
	@Transient
	private String nodeType = "menu";//Ztree节点类型，menu和permission,在角色授权编辑功能使用到,默认是menu类型
	@Transient
	private boolean isChecked;//节点是否选中
	@Transient
	private String url2;//ztree中设置url的值默认会进行url跳转，所以更改为url2参数来取值
	
	public String getUrl2() {
		url2 = url;
		return url2;
	}
	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public Menu(){
	}
	
	public Menu(Long id){
		this.setId(id);
	}
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	@Transient
	private String urls;

	public String getUrls() {
		return url;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	@Transient
	private Long parentId;
	
	public Long getParentId() {
		if(parent != null){
			return this.parent.getId();
		}else{
			return null;
		}
		
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	@ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="pid")
	private Menu parent;//父级
	
	@OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY,mappedBy="parent")
	@OrderBy("id")
	private Set<Menu> childrens = new HashSet<Menu>();//子级
	
	@OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY,mappedBy="menu")
	@OrderBy("id")
	private Set<Permission> permissions = new HashSet<Permission>();//授权
	
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	
	public Set<Menu> getChildrens() {
		return childrens;
	}
	public void setChildrens(Set<Menu> childrens) {
		this.childrens = childrens;
	}
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

}