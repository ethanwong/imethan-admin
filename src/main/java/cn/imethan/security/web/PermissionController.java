package cn.imethan.security.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import cn.imethan.security.entity.Menu;
import cn.imethan.security.entity.Permission;
import cn.imethan.security.service.MenuService;
import cn.imethan.security.service.PermissionService;

/**
 * PermissionController.java
 *
 * @author Ethan Wong
 * @time 2015年9月2日上午10:21:52
 */
@Controller
@RequestMapping("/security/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private MenuService menuService;

	@RequestMapping("")
	public String permission(Model model) {
		return "security/permission";
	}

	@ResponseBody
	@RequestMapping(value = "json/{menuId}/{isRoot}", method = { RequestMethod.GET, RequestMethod.POST })
	public JqGridPageDto<Permission> json(ServletRequest rquest, @PathVariable Long menuId, @PathVariable Boolean isRoot, @RequestParam("page") Integer pageNo, @RequestParam("rows") Integer pageSize) {

		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		if (menuId == 0l) {

		}else{
			if (isRoot) {
				List<Long> rootMenuChildIdList = menuService.getRootMenuChildIdList(menuId);
				rootMenuChildIdList.add(menuId);

				SearchFilter searchFilter = new SearchFilter("INL_menu.id", rootMenuChildIdList.toString().replace("[", "").trim().replace("]", "").trim());
				filters.add(searchFilter);
			} else {
				SearchFilter searchFilter = new SearchFilter("EQL_menu.id", menuId.toString());
				filters.add(searchFilter);
			}
		}


		Page<Permission> page = new Page<Permission>(pageNo, pageSize);
		page = permissionService.getPage(filters, page);
		return new JqGridPageDto<Permission>(page);
	}

	@ResponseBody
	@RequestMapping(value = "detail/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public Permission detail(@PathVariable Long id) {

		return permissionService.getById(id);
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnDto save(@ModelAttribute("permission") Permission permission, BindingResult result, Model model, ServletRequest request) {

		String menuId = request.getParameter("menuId");

		if (StringUtils.isNoneBlank(menuId)) {
			Menu menu = menuService.getById(Long.valueOf(menuId));
			permission.setMenu(menu);
		}

		boolean isSuccess = true;
		String message = "添加成功。";
		if (result.hasErrors()) {
			isSuccess = false;
			message = "添加失败";
		} else {
			ReturnDto returnDto = permissionService.saveOrUpdate(permission);
			isSuccess = returnDto.isSuccess();
			message = returnDto.getMessage();
		}

		return new ReturnDto(isSuccess, message);
	}

	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.POST })
	public ReturnDto delete(Model model, @PathVariable Long id, ServletRequest request) {
		return permissionService.deleteById(id);
	}

	// @ModelAttribute
	// public void getModel(@RequestParam(value = "id", required = false) Long
	// id, ServletRequest request, Model model) throws Exception {
	// if (id != null) {
	// Permission permission = permissionService.getById(id);
	// model.addAttribute("permission", permission);
	// }
	// }

}
