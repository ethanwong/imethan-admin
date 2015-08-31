package cn.imethan.admin.dao.security;

import org.springframework.stereotype.Repository;

import cn.imethan.admin.base.hibernate.MyHibernateTemplate;
import cn.imethan.admin.base.hibernate.SimpleSearch;
import cn.imethan.admin.entity.security.User;

/**
 * 
 * UserDaoImpl.java
 *
 * @author Ethan Wong
 * @time 2015年8月31日下午10:49:04
 */
@Repository
public class UserDaoImpl extends MyHibernateTemplate<User, Long>  implements UserDao{



}


