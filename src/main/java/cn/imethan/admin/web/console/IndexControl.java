package cn.imethan.admin.web.console;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexControl.java
 *
 * @author Ethan Wong
 * @time 2015年8月28日下午10:45:50
 */
@Controller
@RequestMapping("/console")
public class IndexControl {
	
    @RequestMapping("")
    public String indexOne(Model model) {
    	System.out.println("------console----------");
    	return "console/index";
    }
}


