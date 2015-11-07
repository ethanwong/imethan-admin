package cn.imethan.admin.web.console;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.imethan.common.security.service.SecurityContext;
import cn.imethan.common.security.service.UserInfo;
import cn.imethan.security.entity.User;
import cn.imethan.security.service.UserService;

/**
 * IndexControl.java
 *
 * @author Ethan Wong
 * @time 2015年8月28日下午10:45:50
 */
@Controller
@RequestMapping("/console")
public class IndexControl {
	
	@Autowired
	private UserService userSerivce;

	@RequestMapping("")
	public String index(Model model,HttpServletRequest request) {
		System.out.println("------console----------isRememberMe:"+isRememberMeAuthenticated());
		
		UserInfo userInfo = SecurityContext.getUserInfo();//获取登录用户信息
		System.out.println("------console----------userInfo:"+userInfo.getRolename());
		
		if (userInfo != null) {
			String username = userInfo.getUsername();
			
			User user = userSerivce.getByUsername(username);
			//设置登录用户菜单
			HttpSession session = request.getSession();
			session.setAttribute("userRootMenus", userSerivce.getUserMenu(user.getRoles()));
			
		}

		return "console/index";
	}
	
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
