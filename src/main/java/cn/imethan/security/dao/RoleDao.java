package cn.imethan.security.dao;

import cn.imethan.common.hibernate.CrudOperations;
import cn.imethan.security.entity.Role;

/**
 * RoleDao.java
 *
 * @author Ethan Wong
 * @time 2015年9月1日上午10:25:30
 */
public interface RoleDao extends CrudOperations<Role,Long>{
	
	/**
	 * 角色名称是否存在
	 * @param id
	 * @param name
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月27日 下午2:16:57
	 */
	Boolean isExistsName(String id, String name);
	
	/**
	 * 是否关联菜单
	 * @param menuId
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月27日 下午2:37:11
	 */
	boolean isAssociationMenu(Long menuId);

	/**
	 * 是否关联授权
	 * @param permissionId
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月27日 下午3:40:25
	 */
	boolean isAssociationPermission(Long permissionId);

}


