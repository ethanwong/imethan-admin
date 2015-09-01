package cn.imethan.security.dao.impl;

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



}


