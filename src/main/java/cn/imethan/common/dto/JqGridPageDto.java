package cn.imethan.common.dto;

import java.util.ArrayList;
import java.util.List;

import cn.imethan.common.hibernate.Page;

/**
 * JqPageDto.java
 *
 * @author Ethan Wong
 * @time 2014年11月18日下午10:45:22
 */
public class JqGridPageDto<T> {
	
	private Integer page;//当前页
	private Integer total;//总页数
	private Long records;//总记录数
	private List<T> rows = new ArrayList<T>();//结果集合
	
	public JqGridPageDto(Page<T> page){
		this.page = page.getPageNo();
		this.total = page.getPageCount();
		this.records = page.getTotalCount();
		this.rows = page.getList();
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Long getRecords() {
		return records;
	}
	public void setRecords(Long records) {
		this.records = records;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}