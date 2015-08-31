package cn.imethan.admin.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.imethan.admin.entity.security.User;
import cn.imethan.admin.service.security.UserService;

public class TestSpring {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"main/applicationContext.xml"});
		UserService userService = context.getBean(UserService.class);
		User enttiy = new User();
		userService.save(enttiy);
	}
	
	@Test
	public void test1(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"main/applicationContext.xml"});
		UserService userService = context.getBean(UserService.class);
		User enttiy = new User();
		userService.save(enttiy);
	}
	
}
