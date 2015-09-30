package cn.imethan.security.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.common.hibernate.Page;
import cn.imethan.common.hibernate.SearchFilter;
import cn.imethan.security.dao.RoleDao;
import cn.imethan.security.dao.UserDao;
import cn.imethan.security.entity.Menu;
import cn.imethan.security.entity.Role;
import cn.imethan.security.entity.User;
import cn.imethan.security.service.UserService;

/**
 * UserServiceImpl.java
 *
 * @author Ethan Wong
 * @time 2015年9月2日上午10:11:00
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	private Logger logger = LogManager.getLogger(UserServiceImpl.class); 
	private ReturnDto returnDto = new ReturnDto(true,"操作成功");
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public void justForTestMethod() {
		System.out.println("------justForTestMethod------------");
	}	

	@Override
	public User getByUsername(String username) {
		SearchFilter searchFilter = new SearchFilter("EQS_username",username);
		return userDao.getByFilter(searchFilter, false).get(0);
	}

	@Override
	public User getById(Long id) {
		return userDao.getById(id);
	}

	@Override
	public Page<User> getPage(List<SearchFilter> filters, Page<User> page) {
		return userDao.getPageByFilters(page, filters, false);
	}
	
	@Transactional(readOnly = false)
	@Override
	public ReturnDto save(User entity) {
		try {
			//设置角色信息
			Long roleId = entity.getRoleId();
			System.out.println("-----------roleId:"+roleId);
			Role role = roleDao.getById(roleId);
			Set<Role> roles = new HashSet<Role>();
			roles.add(role);
			entity.setRoles(roles);
			
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String password = bCryptPasswordEncoder.encode(entity.getPassword());
			entity.setPassword(password);
			
			if(entity.getId() != null){
				entity.setModifyTime(new Date());
			}			
			userDao.save(entity);
		} catch (Exception e) {
			returnDto = new ReturnDto(false,"操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("操作失败", e.fillInStackTrace());
			e.printStackTrace();
		}
		return returnDto;
	}
	
	@Transactional(readOnly = false)
	@Override
	public ReturnDto deleteById(Long id) {
		try {
			userDao.deleteById(id);
		} catch (Exception e) {
			returnDto = new ReturnDto(false,"操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("操作失败", e.fillInStackTrace());
			e.printStackTrace();
		}
		return returnDto;
	}

	@Transactional(readOnly = false)
	@Override
	public ReturnDto updateProfile(User user) {
		try {
			User userDb = userDao.getById(user.getId());
			userDb.setEmail(user.getEmail());
			userDb.setLocate(user.getLocate());
			userDb.setNickname(user.getNickname());
			userDb.setPhone(user.getPhone());
			userDb.setQq(user.getQq());
			userDao.saveOrUpdate(userDb);
		} catch (Exception e) {
			returnDto = new ReturnDto(false,"操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("操作失败", e.fillInStackTrace());
			e.printStackTrace();
		}
		
		return returnDto;
	}

	@Transactional(readOnly = false)
	@Override
	public ReturnDto updateAvatar(Long userId, String saveFileName) {
		
		try {
			User userDb = userDao.getById(userId);
			userDb.setAvatar(saveFileName);
			userDao.saveOrUpdate(userDb);
		} catch (Exception e) {
			returnDto = new ReturnDto(false,"操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("操作失败", e.fillInStackTrace());
			e.printStackTrace();
		}
		
		return returnDto;
	}

	@Transactional(readOnly = false)
	@Override
	public ReturnDto updatePassword(String username, String password) {
		try {
			User user = this.getByUsername(username);
			user.setPassword(password);
			userDao.saveOrUpdate(user);
		} catch (Exception e) {
			returnDto = new ReturnDto(false,"操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("操作失败", e.fillInStackTrace());
			e.printStackTrace();
		}
		return returnDto;
	}

	@Override
	public Set<Menu> getUserRootMenu(Set<Role> roles) {
		Set<Menu> rootMenuList = new HashSet<Menu>();
		
		for(Role role : roles){
			for(Menu menu : role.getMenus()){
				if(menu.isRoot() && !rootMenuList.contains(menu)){
					rootMenuList.add(menu);
				}
			}
		}
		
		return rootMenuList;
	}

	@Transactional(readOnly = false)
	@Override
	public ReturnDto deleteByIds(List<Long> ids) {
		try {
			for(Long id : ids){
				userDao.deleteById(id);
			}
		} catch (Exception e) {
			returnDto = new ReturnDto(false,"操作失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("操作失败", e.fillInStackTrace());
			e.printStackTrace();
		}
		return new ReturnDto(true,"删除成功");
	}


}


