package cn.imethan.security.service;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.imethan.security.entity.Menu;
import cn.imethan.security.entity.User;


/**
 * UserServiceTest.java
 *
 * @author Ethan Wong
 * @time 2015年11月7日下午9:09:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/main/applicationContext-main.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)  
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testGetUserMenu(){
		User user = userService.getById(3l);
		Set<Menu> userMenu = userService.getUserMenu(user.getRoles());
		for(Menu menu : userMenu){
			System.out.println("----root:"+menu.getName());
			for(Menu child:menu.getChildrens()){
				System.out.println("------root:"+child.getName());
			}
		}
		

	}
	
}


