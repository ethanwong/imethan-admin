package cn.imethan.security.dao.impl;

import org.springframework.stereotype.Repository;

import cn.imethan.common.hibernate.HibernateTemplateSupport;
import cn.imethan.security.dao.ResourceDao;
import cn.imethan.security.entity.Resource;

/**
 * ResourceDaoImpl.java
 *
 * @author Ethan Wong
 * @time 2015年9月1日上午10:27:56
 */
@Repository
public class ResourceDaoImpl extends HibernateTemplateSupport<Resource, Long>  implements ResourceDao{

}


