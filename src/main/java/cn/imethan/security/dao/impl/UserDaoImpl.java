package cn.imethan.security.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.imethan.common.hibernate.HibernateTemplateSupport;
import cn.imethan.security.dao.UserDao;
import cn.imethan.security.entity.User;

/**
 * 
 * UserDaoImpl.java
 *
 * @author Ethan Wong
 * @time 2015年8月31日下午10:49:04
 */
@Repository
public class UserDaoImpl extends HibernateTemplateSupport<User, Long>  implements UserDao{

	@Override
	public boolean isExistsName(String id, String name) {
		String hql = "";
		Query query = null;
		if(StringUtils.isNotEmpty(id)){
			hql = "from User where username=:name and id!=:id";
			query = this.createQuery(hql);
			query.setParameter("name", name);
			query.setParameter("id", Long.valueOf(id));
		}else{
			hql = "from User where username=:name";
			query = this.createQuery(hql);
			query.setParameter("name", name);
		}
		
		if(query.list() !=null && !query.list().isEmpty()){
			return true;
		}
		
		return false;
	}



}


