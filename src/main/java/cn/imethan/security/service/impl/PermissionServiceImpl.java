package cn.imethan.security.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.common.hibernate.SearchFilter;
import cn.imethan.security.dao.PermissionDao;
import cn.imethan.security.entity.Permission;
import cn.imethan.security.service.PermissionService;

/**
 * PermissionServiceImpl.java
 *
 * @author Ethan Wong
 * @time 2015年9月2日上午9:24:59
 */
@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {
	
	private Logger logger = Logger.getLogger(PermissionServiceImpl.class); 
	private ReturnDto returnDto = new ReturnDto(true,"操作成功");
	
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Permission> getByMenuId(Long menuId) {
		SearchFilter searchFilter = new SearchFilter("EQL_menuId",menuId.toString());
		return permissionDao.getByFilter(searchFilter, false);
	}

	@Override
	public Permission getById(Long id) {
		return permissionDao.getById(id);
	}
	
	@Transactional(readOnly = false)
	@Override
	public ReturnDto deleteById(Long id) {
		try {
			permissionDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			returnDto = new ReturnDto(false,"删除失败");
			logger.error("删除失败", e.fillInStackTrace());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return returnDto;
	}

	@Transactional(readOnly = false)
	@Override
	public ReturnDto saveOrUpdate(Permission permission) {
		try {
			permissionDao.saveOrUpdate(permission);
		} catch (Exception e) {
			returnDto = new ReturnDto(false,"操作失败");
			logger.error("操作失败", e.fillInStackTrace());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return returnDto;
	}

}


