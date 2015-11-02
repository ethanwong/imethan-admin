package cn.imethan.security.web;

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

import cn.imethan.common.dto.ReturnDto;
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
		return "security/menu";
	}

	@ResponseBody
	@RequestMapping(value = "json", method = { RequestMethod.POST })
	public List<Menu> json() {
		return menuService.getRootMenu();
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ReturnDto save(@ModelAttribute("menu") Menu menu, BindingResult result, ServletRequest request) {

		// 设置父级节点
		if (StringUtils.isNotBlank(request.getParameter("parentId"))) {
			String parentId = request.getParameter("parentId");
			menu.setParent(new Menu(Long.valueOf(parentId.trim())));
		}

		boolean isSuccess = true;
		String message = "添加成功。";
		if (result.hasErrors()) {
			isSuccess = false;
			message = "添加失败";
		} else {
			ReturnDto returnDto = menuService.saveOrModify(menu);
			isSuccess = returnDto.isSuccess();
			message = returnDto.getMessage();
		}

		return new ReturnDto(isSuccess, message);
	}

	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.POST })
	public ReturnDto delete(Model model, @PathVariable Long id, ServletRequest request) {
		return menuService.deleteById(id);
	}
	
    @ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id, ServletRequest request, Model model) throws Exception {
        if (id != null) {
            Menu menu = menuService.getById(id);
            model.addAttribute("menu", menu);
        }
    }

}
