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
	
	
}


