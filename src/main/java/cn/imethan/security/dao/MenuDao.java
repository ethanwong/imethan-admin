package cn.imethan.security.dao;

import java.util.List;

import cn.imethan.common.hibernate.CrudOperations;
import cn.imethan.security.entity.Menu;

/**
 * MenuDao.java
 *
 * @author Ethan Wong
 * @time 2015年9月1日上午10:25:00
 */
public interface MenuDao extends CrudOperations<Menu,Long>{
	
	/**
	 * 获取一级菜单的子菜单ID列表
	 * @param menuId
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月21日 下午11:39:56
	 */
	List<Long> getRootMenuChildIdList(Long menuId);
	
	/**
	 * 获取所有根级菜单
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月26日 下午10:57:40
	 */
	List<Menu> getRootMenu();
	


}


