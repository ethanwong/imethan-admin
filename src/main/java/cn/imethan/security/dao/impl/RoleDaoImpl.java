package cn.imethan.security.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.imethan.common.hibernate.HibernateTemplateSupport;
import cn.imethan.security.dao.RoleDao;
import cn.imethan.security.entity.Role;

/**
 * 
 * RoleDaoImpl.java
 *
 * @author Ethan Wong
 * @time 2015年9月2日下午4:08:23
 */
@Repository
public class RoleDaoImpl extends HibernateTemplateSupport<Role, Long>  implements RoleDao{

	@Override
	public Boolean isExistsName(String id, String name) {
		String hql = "";
		Query query = null;
		if(StringUtils.isNotEmpty(id)){
			hql = "from Role where name=:name and id!=:id";
			query = this.createQuery(hql);
			query.setParameter("name", name);
			query.setParameter("id", Long.valueOf(id));
		}else{
			hql = "from Role where name=:name";
			query = this.createQuery(hql);
			query.setParameter("name", name);
		}
		
		if(query.list() !=null && !query.list().isEmpty()){
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isAssociationMenu(Long menuId) {
		Query query = this.getSession().createSQLQuery("select * from imethan_security_role_menu where menuId=?");
		query.setParameter(0, menuId);
		List list = query.list();
		if(list != null && !list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public boolean isAssociationPermission(Long permissionId) {
		Query query = this.getSession().createSQLQuery("select * from imethan_security_role_permission where permissionId=?");
		query.setParameter(0, permissionId);
		List list = query.list();
		if(list != null && !list.isEmpty()){
			return true;
		}
		return false;
	}



}


