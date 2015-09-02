package cn.imethan.security.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.imethan.common.utils.JsonUtil;
import cn.imethan.security.entity.Menu;
import cn.imethan.security.service.MenuService;

/**
 * MenuController.java
 *
 * @author Ethan Wong
 * @time 2015年9月1日下午10:40:07
 */
@Controller
@RequestMapping("/security/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
    @RequestMapping("")
    public String menu(Model model) {
    	System.out.println("------security/menu----------");
    	return "security/menu";
    }
    
	@ResponseBody
	@RequestMapping(value="json",method = {RequestMethod.POST})
	public List<Menu> json(){
		List<Menu> menus = menuService.getRootMenu();
		String json = JsonUtil.writeValueAsString(menus);
		System.out.println("----------menu------json:"+json);
		return menus;
	}

}


