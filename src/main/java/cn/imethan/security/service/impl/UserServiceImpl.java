package cn.imethan.security.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Order;
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
import cn.imethan.security.utils.comparator.MenuComparator;

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
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("id"));
		return userDao.getPageByFiltersAndOrders(page, filters, orderList, false);
	}
	
	@Transactional(readOnly = false)
	@Override
	public ReturnDto save(User entity) {
		try {
			//设置角色信息
			Long roleId = entity.getRoleId();
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
			
			return new ReturnDto(true,"添加成功");
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
	public Set<Menu> getUserMenu(Set<Role> roles) {
//		Set<Menu> userMenuList = new HashSet<Menu>();
		TreeSet<Menu> userMenuList = new TreeSet<Menu>(new MenuComparator());
		
		for(Role role : roles){
			Set<Menu> allMenu = role.getMenus();//获取拥有权限的一级菜单
			for(Menu menu : allMenu){
				//剔除没有权限的菜单
				if(menu.isRoot() && !userMenuList.contains(menu)){
					
					//排序处理
					TreeSet<Menu> childrenTemp = new TreeSet<Menu>(new MenuComparator());
					
					Set<Menu> childrens = menu.getChildrens();
					for(Menu children : childrens){
						if(allMenu.contains(children)){
							childrenTemp.add(children);
						}
					}
					
					menu.setChildrens(childrenTemp);
					userMenuList.add(menu);
				}
			}
		}
		
		return userMenuList;
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

	@Override
	public boolean isExistsName(String id, String name) {
		return userDao.isExistsName(id,name);
	}


}


