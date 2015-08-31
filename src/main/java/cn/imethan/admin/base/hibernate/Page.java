package cn.imethan.admin.base.hibernate;

import java.util.ArrayList;
import java.util.List;

/**
 * Page.java
 *
 * @author Ethan Wong
 * @time 2015年8月31日下午8:38:06
 */
public class Page<T> {
	
	private Integer pageNo;//页码
	private Integer pageCount;//页记录数
	private Integer totalCount;//总记录数
	private List<T> list = new ArrayList<T>();//页信息列表
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}


