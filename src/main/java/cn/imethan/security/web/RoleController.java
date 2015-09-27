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
	@RequestMapping(value = "json", method = {RequestMethod.POST })
	public List<Role> json() {
		return roleService.getAllList();
	}

	@ResponseBody
	@RequestMapping(value = "detail/{id}", method = {RequestMethod.POST })
	public Role detail(@PathVariable Long id) {
		return roleService.getById(id);
	}

	@ResponseBody
	@RequestMapping(value = "save", method = { RequestMethod.POST })
	public ReturnDto save(@ModelAttribute("role") Role role, @RequestParam String menuPermission) {
		return roleService.saveOrUpdate(role, menuPermission);
	}

	@ResponseBody
	@RequestMapping(value = "menupermission/{roleId}", method = { RequestMethod.POST })
	public List<Menu> menuPermissionForRoleInput(@PathVariable Long roleId) {
		return menuService.getMenuPermissionForRoleInput(roleId);
	}

	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.POST })
	public ReturnDto delete(Model model, @PathVariable Long id, ServletRequest request) {
		return roleService.deleteById(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/isExistsName", method = { RequestMethod.POST })
	public Boolean isExistsName(Model model,ServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		return !roleService.isExistsName(id,name);
	}
	
    @ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id, ServletRequest request, Model model) throws Exception {
        if (id != null) {
            Role role = roleService.getById(id);
            model.addAttribute("role", role);
        }
    }

}
