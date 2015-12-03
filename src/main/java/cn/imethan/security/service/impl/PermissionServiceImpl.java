package cn.imethan.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.common.hibernate.Page;
import cn.imethan.common.hibernate.SearchFilter;
import cn.imethan.common.security.filter.InvocationSecurityMetadataSource;
import cn.imethan.security.dao.MenuDao;
import cn.imethan.security.dao.PermissionDao;
import cn.imethan.security.dao.RoleDao;
import cn.imethan.security.entity.Menu;
import cn.imethan.security.entity.Permission;
import cn.imethan.security.service.PermissionService;

/**
 * PermissionServiceImpl.java
 *
 * @author Ethan Wong
 * @time 2015年9月2日上午9:24:59
 */
@Service("permissionService")
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

	private Logger logger = LogManager.getLogger(PermissionServiceImpl.class);

	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Permission> getByMenuId(Long menuId) {
		SearchFilter searchFilter = new SearchFilter("EQL_menu.id", menuId.toString());
		return permissionDao.getByFilter(searchFilter, false);
	}

	@Override
	public Permission getById(Long id) {
		return permissionDao.getById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public ReturnDto deleteById(Long id) {
		ReturnDto returnDto = new ReturnDto(true, "操作成功");
		try {
			permissionDao.deleteById(id);
			
			InvocationSecurityMetadataSource.reFresh();//刷新授权信息
		} catch (Exception e) {
			e.printStackTrace();
			returnDto.setSuccess(false);
			returnDto.setMessage("删除失败");
			logger.error("删除失败", e.fillInStackTrace());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return returnDto;
	}

	@Transactional(readOnly = false)
	@Override
	public ReturnDto saveOrUpdate(Permission permission) {
		ReturnDto returnDto = new ReturnDto(true,"添加成功");
		try {
			String url = permission.getUrl();
			String name = permission.getName();

			// 判断url是否已经存在
			boolean isUrlExists = isUrlExists(url);
			// 判断name是否已经存在
			boolean isNameExists = isNameExists(name);

			if (permission.getId() == null) {
				if (isNameExists) {
					return new ReturnDto(false, "授权名称已经存在");
				} else if (isUrlExists) {
					return new ReturnDto(false, "授权URL已经存在");
				}
				permissionDao.saveOrUpdate(permission);
			} else {
				Permission permissionDb = this.getById(permission.getId());
				
				if (isNameExists && !name.equals(permissionDb.getName()) ) {
					return new ReturnDto(false, "授权名称已经存在");
				} else if (isUrlExists && !url.equals(permissionDb.getUrl())) {
					return new ReturnDto(false, "授权URL已经存在");
				}
				
				permissionDb.setName(name);
				permissionDb.setUrl(url);
				permissionDao.saveOrUpdate(permissionDb);
			}
			InvocationSecurityMetadataSource.reFresh();//刷新授权信息
		} catch (Exception e) {
			returnDto.setSuccess(false);
			returnDto.setMessage("添加失败");
			logger.error("操作失败", e.fillInStackTrace());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return returnDto;
	}

	@Override
	public boolean isNameExists(String name) {
		List<Permission> list = permissionDao.getByFilter(new SearchFilter("EQS_name", name), false);
		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isUrlExists(String url) {
		List<Permission> list = permissionDao.getByFilter(new SearchFilter("EQS_url", url), false);
		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public Iterable<Permission> getAll() {
		return permissionDao.getAll();
	}

	@Override
	public Page<Permission> getPage(List<SearchFilter> filters, Page<Permission> page) {
		return permissionDao.getPageByFilters(page, filters, false);
	}
	

	@Override
	@Transactional(readOnly = false)
	public ReturnDto quickAddSave(Long menuId,String allNameAndUrl) {
		
		try {
			Menu menu = menuDao.getById(menuId);
			String[] nameAndUrls = allNameAndUrl.split(",");
			for(String nameAndUrl : nameAndUrls){
				if(nameAndUrl.trim().equals("")){
					continue;
				}
				System.out.println("nameAndUrl:"+nameAndUrl);
				int index = nameAndUrl.indexOf(":");
				String menuName = nameAndUrl.substring(0,index);
				String menuUrl = nameAndUrl.substring(index+1,nameAndUrl.length());
				Permission permission = new Permission(menu,menuName,menuUrl);
				
				permissionDao.saveOrUpdate(permission);
			}
			InvocationSecurityMetadataSource.reFresh();//刷新授权信息
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ReturnDto(false,"快捷授权失败");
		}
		
		return new ReturnDto(true,"快捷授权成功");
	}
	
	@Override
	public List<Permission>  quickAddPreview(Long menuId){
		List<Permission> list = new ArrayList<Permission>();
		
		Menu menu = menuDao.getById(menuId);
		
		String menuName = menu.getName();
		String menuUrl = menu.getUrl();
		String modelName = menuName;
		if(menuName.substring(menuName.length()-2, menuName.length()).trim().equals("管理")){
			modelName = menuName.substring(0, menuName.length()-2);
		}
		
		Permission jsonUrlPermission = new Permission(menu,"浏览"+modelName,menuUrl + "/json");
		Permission detailUrlPermission = new Permission(menu,"查看"+modelName,menuUrl + "/detail");
		Permission inputUrlPermission = new Permission(menu,"添加"+modelName+"",menuUrl + "/input");
		Permission updateUrlPermission = new Permission(menu,"更新"+modelName+"",menuUrl + "/update");
		Permission saveUrlPermission = new Permission(menu,"保存"+modelName,menuUrl + "/save");
		Permission deleteUrlPermission = new Permission(menu,"删除"+modelName,menuUrl + "/delete");
		
		list.add(jsonUrlPermission);
		list.add(detailUrlPermission);
		list.add(inputUrlPermission);
		list.add(updateUrlPermission);
		list.add(saveUrlPermission);
		list.add(deleteUrlPermission);		
		
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public ReturnDto deleteByIds(List<Long> rowids) {
		int deleteCount = rowids.size();
		
		try {
			if(deleteCount == 1){
				//判断是否被角色关联
				if(roleDao.isAssociationPermission(rowids.get(0))){
					return new ReturnDto(false,"授权被角色关联，不能删除");
				}
				
				this.deleteById(rowids.get(0));
			}else{
				for(Long rowId : rowids){
					//判断是否被角色关联
					if(roleDao.isAssociationPermission(rowId)){
						deleteCount--;
						continue;
					}
					
					this.deleteById(rowId);
				}
			}
			InvocationSecurityMetadataSource.reFresh();//刷新授权信息
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new ReturnDto(false,"删除授权失败");
		}
		if(deleteCount > 0){
			return new ReturnDto(true,"成功删除"+deleteCount+"条授权记录");
		}else{
			return new ReturnDto(false,"授权被角色关联，不能删除");
		}
		
	}

}
