package cn.imethan.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PermissionController.java
 *
 * @author Ethan Wong
 * @time 2015年9月2日上午10:21:52
 */
@Controller
@RequestMapping("/security/permission")
public class PermissionController {
	
    @RequestMapping("")
    public String permission(Model model) {
    	System.out.println("------security/permission----------");
    	return "security/permission";
    }

}


