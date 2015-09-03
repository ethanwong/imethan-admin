package cn.imethan.security.dao.impl;

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



}


