package cn.imethan.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * RoleController.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午3:02:53
 */
@Controller
@RequestMapping("/security/role")
public class RoleController {
	
    @RequestMapping("")
    public String role(Model model) {
    	System.out.println("------security/role----------");
    	return "security/role";
    }

}


