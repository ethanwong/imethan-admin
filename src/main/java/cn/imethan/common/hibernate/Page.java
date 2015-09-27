package cn.imethan.common.hibernate;

import java.util.ArrayList;
import java.util.List;

/**
 * Page.java
 *
 * @author Ethan Wong
 * @time 2015年8月31日下午8:38:06
 */
public class Page<T> {
	
	private Integer pageNo = 1;//页码
	private Integer pageSize = 10;//页记录数
	private Integer pageCount;//总页数
	private Long totalCount;//总记录数
	private List<T> list = new ArrayList<T>();//页信息列表
	
	public Page(){
		
	}
	
	public Page(Integer pageNo,Integer pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		return (int) ((this.getTotalCount()/this.getPageSize()) + ((this.getTotalCount()%this.getPageSize())>1?1:0));
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public Integer getFirstResult(){
		return (pageNo-1)*pageSize;
	}
	public Integer getMaxResults(){
		return pageSize;
	}
}


