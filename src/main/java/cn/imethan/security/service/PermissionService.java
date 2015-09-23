package cn.imethan.security.service;

import java.util.List;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.common.hibernate.Page;
import cn.imethan.common.hibernate.SearchFilter;
import cn.imethan.security.entity.Permission;

/**
 * PermissionService.java
 *
 * @author Ethan Wong
 * @time 2014年3月16日下午5:00:27
 */
public interface PermissionService{
	
	/**
	 * 根据menuId获取授权列表
	 * @param menuId
	 * @return
	 */
	List<Permission> getByMenuId(Long menuId);
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	Permission getById(Long id);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	ReturnDto deleteById(Long id);
	
	/**
	 * 保存或者删除
	 * @param permission
	 * @return
	 */
	ReturnDto saveOrUpdate(Permission permission);

	Iterable<Permission> getAll();
	
	/**
	 * 获取分页列表
	 * @param filters
	 * @param page
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月21日 下午11:24:38
	 */
	Page<Permission> getPage(List<SearchFilter> filters, Page<Permission> page);

}


