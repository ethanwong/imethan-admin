package cn.imethan.security.dao.impl;

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

}


