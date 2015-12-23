package cn.imethan.admin.web.console;

import java.text.ParseException;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.imethan.admin.quartz.dynamic.DynamicQuartzJobFactory;

/**
 * LoginController.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午1:54:07
 */
@Controller
@RequestMapping("")
public class LoginController {
	
	@Autowired
	private DynamicQuartzJobFactory dynamicQuartzJobFactory;


	@RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
	public String login() {
		return "console/login";
	}
	
	
	@RequestMapping(value = "/test",method = {RequestMethod.POST,RequestMethod.GET})
	public String test() {
		dynamicQuartzJobFactory.testOne();
		return "console/login";
	}
	
}
