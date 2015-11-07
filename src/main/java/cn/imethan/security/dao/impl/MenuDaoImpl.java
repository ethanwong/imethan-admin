package cn.imethan.security.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.imethan.common.hibernate.HibernateTemplateSupport;
import cn.imethan.security.dao.MenuDao;
import cn.imethan.security.entity.Menu;

/**
 * MenuDaoImpl.java
 *
 * @author Ethan Wong
 * @time 2015年9月1日上午10:27:56
 */
@Repository
public class MenuDaoImpl extends HibernateTemplateSupport<Menu, Long>  implements MenuDao{

	@Override
	public List<Long> getRootMenuChildIdList(Long menuId) {
		Query query = this.createQuery("select id from Menu where parent.id=:menuId");
		query.setLong("menuId", menuId);
		return query.list();
	}

	@Override
	public List<Menu> getRootMenu() {
		Query query = this.createQuery("from Menu where isRoot=true order by orderNo asc");
		return  query.list();
	}

}


