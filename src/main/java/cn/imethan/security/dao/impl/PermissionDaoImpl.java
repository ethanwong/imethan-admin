package cn.imethan.security.dao.impl;

import org.springframework.stereotype.Repository;

import cn.imethan.common.hibernate.HibernateTemplateSupport;
import cn.imethan.security.dao.PermissionDao;
import cn.imethan.security.entity.Permission;

/**
 * 
 * PermissionDaoImpl.java
 *
 * @author Ethan Wong
 * @time 2015年9月2日下午4:08:58
 */
@Repository
public class PermissionDaoImpl extends HibernateTemplateSupport<Permission, Long>  implements PermissionDao{



}


