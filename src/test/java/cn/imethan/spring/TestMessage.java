package cn.imethan.spring;

import java.util.Locale;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TestMessage.java
 *
 * @author Ethan Wong
 * @time 2015年11月4日上午11:33:06
 */
public class TestMessage {
	@Test
	public  void test1() {
	    MessageSource resources = new ClassPathXmlApplicationContext("beans.xml");
	    String message = resources.getMessage("message", null, "Default",null);
	    System.out.println(message);
	}
	
	@Test
	public  void test2() {
		MessageSource resources = new ClassPathXmlApplicationContext("beans.xml");
	    String message = resources.getMessage("argument.required",
	        new Object [] {"userDao"}, "Required", Locale.ENGLISH);
	    System.out.println(message);
	}
	
	@Test
	public void test3(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
		Example example = context.getBean(Example.class);
		example.execute();
	}

}


