package cn.imethan.admin.dao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.imethan.admin.base.hibernate.MyHibernateDaoSupport;

/**
 * UserDao.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午5:32:16
 */
@Repository
public class UserDao   {
	
//	@Autowired
//	private SessionFactory sessionFactory;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private MyHibernateDaoSupport myHibernateDaoSupport;
	
	
//	@Override
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		// TODO Auto-generated method stub
//		super.setSessionFactory(sessionFactory);
//	}

	public void test(){
//		super.setSessionFactory(sessionFactory);
//		hibernateTemplate.setSessionFactory(sessionFactory);
//		this.setHibernateTemplate(hibernateTemplate);
		
		System.out.println("********************************:"+myHibernateDaoSupport.getHibernateTemplate().find(" from User where username='ethan'"));
	}

}


