package cn.imethan.security.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.imethan.common.dto.JqGridPageDto;
import cn.imethan.common.dto.ReturnDto;
import cn.imethan.common.hibernate.Page;
import cn.imethan.common.hibernate.SearchFilter;
import cn.imethan.security.entity.Role;
import cn.imethan.security.entity.User;
import cn.imethan.security.service.RoleService;
import cn.imethan.security.service.UserService;

/**
 * UserController.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午3:02:53
 */
@Controller
@RequestMapping("/security/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping("")
	public String user(Model model) {
		return "security/user";
	}

	@ResponseBody
	@RequestMapping(value = "json", method = { RequestMethod.POST, RequestMethod.GET })
	public JqGridPageDto<User> json(@RequestParam("page") Integer pageNo,@RequestParam("rows") Integer pageSize) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();

		Page<User> page = new Page<User>(pageNo, pageSize);
		page = userService.getPage(filters, page);
		
		return new JqGridPageDto<User>(page);
	}

	@ResponseBody
	@RequestMapping(value = "detail/{id}", method = { RequestMethod.POST })
	public User detail(@PathVariable Long id) {
		return userService.getById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnDto save(@ModelAttribute("user") User user) {
		return userService.save(user);
	}

	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ReturnDto delete(@PathVariable Long id) {
		return userService.deleteById(id);
	}
	
    @ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id, ServletRequest request, Model model) throws Exception {
        if (id != null) {
            User user = userService.getById(id);
            model.addAttribute("user", user);
        }
    }
    
	@ResponseBody
	@RequestMapping(value = "/rolejson" , method = {RequestMethod.POST})
	public List<Role>  json(){
		return roleService.getAllList();
	}

}