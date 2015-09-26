package cn.imethan.security.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.common.hibernate.SearchFilter;
import cn.imethan.security.dao.MenuDao;
import cn.imethan.security.dao.PermissionDao;
import cn.imethan.security.dao.RoleDao;
import cn.imethan.security.entity.Menu;
import cn.imethan.security.entity.Permission;
import cn.imethan.security.entity.Role;
import cn.imethan.security.service.MenuService;

/**
 * MenuServiceImpl.java
 *
 * @author Ethan Wong
 * @time 2015年9月1日下午2:27:08
 */
@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
	
	private Logger logger = Logger.getLogger(MenuServiceImpl.class); 
	private ReturnDto returnDto = new ReturnDto(true,"操作成功");
	
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionDao permissionDao;
	
	@Transactional(readOnly = false)
	@Override
	public ReturnDto saveOrModify(Menu entity) {
		try {
			menuDao.save(entity);
			returnDto.setObject(entity);
		} catch (Exception e) {
			e.printStackTrace();
			returnDto = new ReturnDto(false,"保存失败");
			logger.error("保存失败", e.fillInStackTrace());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return returnDto;
	}

	@Override
	public Menu getById(Long id) {
		return menuDao.getById(id);
	}
	
	@Transactional(readOnly = false)
	@Override
	public ReturnDto deleteById(Long id) {
		try {
			Menu menu = this.getById(id);
			if(menu.getChildrens() != null && !menu.getChildrens().isEmpty()){
				return new ReturnDto(false,"请先删除二级菜单"); 
			}
			if(menu.getPermissions() != null && !menu.getPermissions().isEmpty()){
				return new ReturnDto(false,"请先删除关联的授权"); 
			}
			menuDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			returnDto = new ReturnDto(false,"操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("删除失败", e.fillInStackTrace());
		}
		return returnDto;
	}

	@Override
	public List<Menu> getRootMenu() {
		SearchFilter searchFilter = new SearchFilter("EQB_isRoot","true");
		return menuDao.getByFilter(searchFilter, false);
	}

	@Override
	public List<Menu> getMenuPermissionForRoleInput(Long roleId) {
		//获取选中的资源和授权信息
		Role role = roleDao.getById(roleId);
		
		Set<Menu> checkMenuSet = new HashSet<Menu>();
		Set<Permission> checkPermissionSet = new HashSet<Permission>();
		if(role != null){
			checkMenuSet = role.getMenus();
			checkPermissionSet = role.getPermissions();
		}
		
		List<Menu> menus = menuDao.getRootMenu();
		for(Menu menu : menus){//遍历父级节点
			
			//设置父级资源节点是否选中
			if(checkMenuSet.contains(menu)){
				menu.setChecked(true);
			}
			
			Set<Menu> childrens = menu.getChildrens();
			for(Menu children : childrens){//遍历子级节点
				
				//设置子级资源节点是否选中
				if(checkMenuSet.contains(children)){
					children.setChecked(true);
					menu.setChecked(true);
				}
				
				Set<Permission> permissions = children.getPermissions();//子级节点的授权信息
				if(children.getChildrens() ==null || children.getChildrens().isEmpty()){
					Set<Menu> menuChildrensTemp = new HashSet<Menu>();
					
					for(Permission permission : permissions){
						Menu menuChildrenTemp = new Menu();
						menuChildrenTemp.setId(permission.getId());
						menuChildrenTemp.setName(permission.getName());
						menuChildrenTemp.setNodeType("permission");
						
						//设置授权节点是否选中
						if(checkPermissionSet.contains(permission)){
							menuChildrenTemp.setChecked(true);
							children.setChecked(true);
							menu.setChecked(true);
						}
						menuChildrensTemp.add(menuChildrenTemp);
					}
					children.setChildrens(menuChildrensTemp);
				}
			}
		}
		return menus;
	}

	@Override
	public List<Long> getRootMenuChildIdList(Long menuId) {
		
		return menuDao.getRootMenuChildIdList(menuId);
	}

}


