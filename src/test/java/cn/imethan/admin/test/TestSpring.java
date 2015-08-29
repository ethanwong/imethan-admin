package cn.imethan.admin.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.imethan.admin.service.security.UserService;

public class TestSpring {
	
	@Test
	public void test1(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"main/applicationContext.xml"});
		UserService userService = context.getBean(UserService.class);
		userService.test();
	}
	
}
