package cn.imethan.admin.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.imethan.admin.dao.security.UserDao;
import cn.imethan.admin.dao.security.UserDaoImpl;
import cn.imethan.admin.entity.security.User;

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
			entity.setNickname("imethan");
			entity.setUsername("ethanwong");
			entity.setPassword("123456");
			userDao.save(entity);
			
			System.out.println("******user:"+entity.toString());
			
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


