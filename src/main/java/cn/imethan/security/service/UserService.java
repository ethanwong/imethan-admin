package cn.imethan.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.imethan.security.dao.UserDao;
import cn.imethan.security.entity.User;

/**
 * UserService.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午5:22:12
 */
@Service
@Transactional(readOnly = true)
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly = false)
	public void save(User entity){
		try {
			userDao.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

}


