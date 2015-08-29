package cn.imethan.admin.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imethan.admin.dao.security.UserDao;

/**
 * UserService.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午5:22:12
 */
@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Transactional
	public void test(){
		userDao.test();
		System.out.println("this is userService test method");
	}

}


