package cn.imethan.admin.dao.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.imethan.admin.base.hibernate.MyHibernateTemplate;
import cn.imethan.admin.base.hibernate.SearchFilter;
import cn.imethan.admin.entity.security.User;

/**
 * UserDao.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午5:32:16
 */
@Repository
public class UserDao extends MyHibernateTemplate<User, Long>{


	public void test(){
//		hibernateTemplate.setSessionFactory(sessionFactory);
//		this.setHibernateTemplate(hibernateTemplate);
//		this.deleteById(2l);
		List<SearchFilter> list = new ArrayList<SearchFilter>();
		list.add(new SearchFilter("EQS_username","ethan"));
		list.add(new SearchFilter("EQS_password","123456"));
		
//		System.out.println("********************************1:"+this.findByFilter(new SearchFilter("EQS_username","ethan"), false));
		System.out.println("********************************1:"+this.getByFilters(list, false));
//		System.out.println("********************************1:"+this.getAll());
//		System.out.println("********************************1:"+this.getById(1l));
//		System.out.println("********************************1:"+sessionFactory.getCurrentSession().get(User.class, 1l));
//		System.out.println("********************************2:"+this.hibernateTemplate.find(" from User where username='ethan'"));
	}



}


