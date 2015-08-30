package cn.imethan.admin.entity.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.sun.istack.internal.NotNull;

import cn.imethan.admin.base.entity.BaseEntity;

/**
 * Resource.java
 *
 * @author Ethan Wong
 * @time 2014年3月17日下午10:01:41
 */
@Entity
@Table(name="imethan_security_resource")
@JsonIgnoreProperties(value={"parent","roles","modifyTime","createTime","url"})
public class Resource extends BaseEntity {
	
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
	private String nodeType = "resource";//Ztree节点类型，resource和permission,在角色授权编辑功能使用到,默认是resource类型
	@Transient
	private boolean isChecked;//节点是否选中
	
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

	public Resource(){
	}
	
	public Resource(Long id){
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
	private Resource parent;//父级
	
	@OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY,mappedBy="parent")
	@OrderBy("id")
	private Set<Resource> childrens = new HashSet<Resource>();//子级
	
	@OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY,mappedBy="resource")
	@OrderBy("id")
	private Set<Permission> permissions = new HashSet<Permission>();//授权
	
	public Resource getParent() {
		return parent;
	}
	public void setParent(Resource parent) {
		this.parent = parent;
	}
	
	public Set<Resource> getChildrens() {
		return childrens;
	}
	public void setChildrens(Set<Resource> childrens) {
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
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}