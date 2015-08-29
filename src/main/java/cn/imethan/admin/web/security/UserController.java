package cn.imethan.admin.web.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UserController.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午3:02:53
 */
@Controller
@RequestMapping("/security/user")
public class UserController {
	
    @RequestMapping("")
    public String user(Model model) {
    	System.out.println("------security/user----------");
    	return "security/user";
    }

}


