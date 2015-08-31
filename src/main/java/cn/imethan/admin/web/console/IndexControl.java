package cn.imethan.admin.web.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.imethan.admin.service.security.UserService;

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
	private UserService userService;
	
    @RequestMapping("")
    public String indexOne(Model model) {
    	System.out.println("------console----------");
    	return "console/index";
    }
}


