package cn.imethan.admin.web.console;

import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		System.out.println("----------login---------------isRememberMeAuthenticated:"+isRememberMeAuthenticated());

		return "console/login";
	}

//	@RequestMapping("/logout")
//	public String loginout() {
//		return "console/login";
//	}

	/**
	 * 判断用户是否从Remember Me Cookie自动登录
	 * 
	 * @return
	 */
	private boolean isRememberMeAuthenticated() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

}
