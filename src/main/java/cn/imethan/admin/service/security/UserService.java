package cn.imethan.admin.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
		try {
			userDao.test();
			System.out.println("this is userService test method");
			
			
//			int i = 10;
//	        //数学异常: java.lang.ArithmeticException
//	        int j = i / 0;
//	        System.out.println(j); 
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			System.out.println("--------Exception----------");
		}
	}

}

