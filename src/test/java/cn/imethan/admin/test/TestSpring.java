package cn.imethan.admin.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.imethan.admin.service.security.UserService;

public class TestSpring {
	
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"main/applicationContext.xml"});
		UserService userService = context.getBean(UserService.class);
		userService.test();
	}
	
}
