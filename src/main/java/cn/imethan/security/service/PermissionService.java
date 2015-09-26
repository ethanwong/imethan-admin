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
	
	/**
	 * 授权名称是否已经存在
	 * @param name
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月23日 下午10:10:33
	 */
	public boolean isNameExists(String name);

	/**
	 * 授权url是否已经存在
	 * @param url
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月23日 下午10:10:52
	 */
	public boolean isUrlExists(String url);
	
	/**
	 * 快捷添加保持
	 * @param menuId
	 * @param allNameAndUrl
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月26日 下午3:40:02
	 */
	ReturnDto quickAddSave(Long menuId,String allNameAndUrl);
	
	/**
	 * 快捷添加预览
	 * @param menuId
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月26日 下午3:52:04
	 */
	List<Permission> quickAddPreview(Long menuId);
	
	/**
	 * 根据ID列表删除
	 * @param rowids
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月26日 下午8:16:33
	 */
	ReturnDto deleteByIds(List<Long> rowids);

}


