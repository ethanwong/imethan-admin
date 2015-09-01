package cn.imethan.admin.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.imethan.security.entity.User;
import cn.imethan.security.service.UserService;

public class TestSpring {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"main/applicationContext.xml"});
		UserService userService = context.getBean(UserService.class);
		User entity = new User();
		entity.setNickname("imethan");
		entity.setUsername("ethanwong");
		entity.setPassword("123456");
		userService.save(entity);
	}
	
	@Test
	public void test1(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"main/applicationContext.xml"});
		UserService userService = context.getBean(UserService.class);
		User entity = new User();
		entity.setNickname("imethan");
		entity.setUsername("ethanwong");
		entity.setPassword("123456");
		userService.save(entity);
	}
	
}
