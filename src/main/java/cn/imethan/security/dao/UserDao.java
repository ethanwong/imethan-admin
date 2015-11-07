package cn.imethan.security.dao;

import cn.imethan.common.hibernate.CrudOperations;
import cn.imethan.security.entity.User;

/**
 * UserDao.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午5:32:16
 */
public interface UserDao extends CrudOperations<User,Long>{
	
	/**
	 * 账号是否已经存在
	 * @param id
	 * @param name
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年11月7日 下午2:09:46
	 */
	boolean isExistsName(String id, String name);





}


