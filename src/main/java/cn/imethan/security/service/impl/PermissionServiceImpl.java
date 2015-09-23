package cn.imethan.security.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.common.hibernate.Page;
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
@Service("permissionService")
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

	private Logger logger = Logger.getLogger(PermissionServiceImpl.class);
	private ReturnDto returnDto = new ReturnDto(true, "操作成功");

	@Autowired
	private PermissionDao permissionDao;

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
				permissionDao.save(permission);
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

}
