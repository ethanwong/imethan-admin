package cn.imethan.security.web;

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

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.security.entity.Menu;
import cn.imethan.security.entity.Role;
import cn.imethan.security.service.MenuService;
import cn.imethan.security.service.RoleService;

/**
 * RoleController.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午3:02:53
 */
@Controller
@RequestMapping("/security/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;

	@RequestMapping("")
	public String role(Model model) {
		return "security/role";
	}

	@ResponseBody
	@RequestMapping(value = "json", method = { RequestMethod.POST })
	public List<Role> json() {
		return roleService.getAllList();
	}

	@ResponseBody
	@RequestMapping(value = "detail/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public Role detail(@PathVariable Long id) {
		return roleService.getById(id);
	}

	@ResponseBody
	@RequestMapping(value = "save", method = { RequestMethod.POST })
	public ReturnDto save(@ModelAttribute("role") Role role, @RequestParam String menuPermission) {
		System.out.println("------------role:" + role);
		System.out.println("------------menuPermission:" + menuPermission);
		return roleService.saveOrUpdate(role, menuPermission);
	}

	@ResponseBody
	@RequestMapping(value = "menupermission/{roleId}", method = { RequestMethod.POST })
	public List<Menu> menuPermissionForRoleInput(@PathVariable Long roleId) {
		System.out.println("-------roleId:" + roleId);
		return menuService.getMenuPermissionForRoleInput(roleId);
	}

	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.POST })
	public ReturnDto delete(Model model, @PathVariable Long id, ServletRequest request) {
		return roleService.deleteById(id);
	}

}
