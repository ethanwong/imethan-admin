package cn.imethan.admin.web.console;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LoginController.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午1:54:07
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping("")
	public String login(){
		return "console/login";
	}

}


