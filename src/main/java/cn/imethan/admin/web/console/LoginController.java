package cn.imethan.admin.web.console;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * LoginController.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午1:54:07
 */
@Controller
@RequestMapping("")
public class LoginController {

	@RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
	public String login() {
		return "console/login";
	}
}
