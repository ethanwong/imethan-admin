package cn.imethan.security.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.common.hibernate.Page;
import cn.imethan.security.dao.MenuDao;
import cn.imethan.security.dao.PermissionDao;
import cn.imethan.security.dao.RoleDao;
import cn.imethan.security.entity.Menu;
import cn.imethan.security.entity.Permission;
import cn.imethan.security.entity.Role;
import cn.imethan.security.service.RoleService;

/**
 * RoleServiceImpl.java
 *
 * @author Ethan Wong
 * @time 2015年9月2日上午9:59:27
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
	
	private Logger logger = LogManager.getLogger(RoleServiceImpl.class); 
	private ReturnDto returnDto = new ReturnDto(true,"操作成功");
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private PermissionDao permissionDao;
	
	private Map<String,Object> parseMenuPermission(String menuPermission){
		Map<String,Object> map = new HashMap<String,Object>();
		Set<Menu> menuSet = new HashSet<Menu>();
		Set<Permission> permissionSet = new HashSet<Permission>();
		
		String[] list = StringUtils.split(menuPermission, ",");

		for(String temp : list){
//			 type:resource id:1
//			 type:resource id:4
//			 type:permission id:16
			String[] tempSplits = StringUtils.split(temp,"-");
			String type = tempSplits[0];
			String id = tempSplits[1];
			Long idLong = Long.parseLong(id);
			if(type.equals("menu")){
				Menu resource = menuDao.getById(idLong);
				menuSet.add(resource);
			}
			if(type.equals("permission")){
				Permission permission = permissionDao.getById(idLong);
				permissionSet.add(permission);
			}
		}
		map.put("menuSet", menuSet);
		map.put("permissionSet", permissionSet);
		
		return map;
	}
	
	@Transactional(readOnly = false)
	@Override
	public ReturnDto saveOrUpdate(Role entity, String menuPermission) {
		try {
			System.out.println();
			long beginTime = System.currentTimeMillis();
			Map<String,Object> result = this.parseMenuPermission(menuPermission);
			System.out.println("end time:"+(System.currentTimeMillis()-beginTime));
			if(!result.isEmpty()){
				if(result.get("menuSet") !=null ){
					Set<Menu> resourceSet = (Set<Menu>) result.get("menuSet");
					entity.setMenus(resourceSet);
				}
				if(result.get("permissionSet") !=null ){
					Set<Permission> permissionSet = (Set<Permission>) result.get("permissionSet");
					entity.setPermissions(permissionSet);
				}
			}
			roleDao.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			returnDto = new ReturnDto(false,"操作失败");
			logger.error("操作失败", e.fillInStackTrace());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return returnDto;
	}

	@Override
	public Page<Role> getPage(Map<String, Object> parameters, Page<Role> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllList() {
		return roleDao.getAll();
	}

	@Transactional(readOnly = false)
	@Override
	public ReturnDto deleteById(Long id) {
		try {
			Role role = roleDao.getById(id);
			if(role.getUsers() != null && !role.getUsers().isEmpty()){
				return new ReturnDto(false,"角色已经被用户关联，不能删除");
			}
			
			role.setPermissions(null);
			role.setMenus(null);
			roleDao.save(role);
			roleDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			returnDto = new ReturnDto(false,"操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("删除失败", e.fillInStackTrace());
		}
		return returnDto;
	}

	@Override
	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	@Override
	public Boolean isExistsName(String id, String name) {
		return roleDao.isExistsName(id,name);
	}

}


