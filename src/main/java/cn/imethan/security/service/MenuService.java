package cn.imethan.security.service;

import java.util.List;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.security.entity.Menu;


/**
 * MenuService.java
 *
 * @author Ethan Wong
 * @time 2014年3月17日下午10:09:38
 */
public interface MenuService {
	
	/**
	 * 保存或者修改
	 * @param entity
	 * @return
	 */
	public ReturnDto saveOrModify(Menu entity);
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	public Menu getById(Long id);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public ReturnDto deleteById(Long id);
	
	/**
	 * 获取根节点
	 * @return
	 */
	public List<Menu> getRootMenu();
	
	/**
	 * 获取菜单和授权关联列表
	 * @param roleId
	 * @return
	 */
	public List<Menu> getMenuPermissionForRoleInput(Long roleId);
	
	/**
	 * 获取一级菜单的子菜单ID列表
	 * @param menuId
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月21日 下午11:39:56
	 */
	public List<Long> getRootMenuChildIdList(Long menuId);
	
	
}


