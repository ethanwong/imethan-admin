package cn.imethan.security.service;

import java.util.List;
import java.util.Set;

import cn.imethan.common.dto.ReturnDto;
import cn.imethan.common.hibernate.Page;
import cn.imethan.common.hibernate.SearchFilter;
import cn.imethan.security.entity.Menu;
import cn.imethan.security.entity.Role;
import cn.imethan.security.entity.User;

/**
 * UserService.java
 *
 * @author Ethan Wong
 * @time 2014年3月16日下午5:00:04
 */
public interface UserService{
	
	/**
	 * 根据用户名获取
	 * @param username
	 * @return
	 */
	public User getByUsername(String username);
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	public User getById(Long id);
	
	/**
	 * 获取分页列表
	 * @param filters
	 * @param page
	 * @return
	 */
	public Page<User> getPage(List<SearchFilter> filters , Page<User> page);
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	public ReturnDto save(User entity);
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public ReturnDto deleteById(Long id);
	
	/**
	 * 更新用户基本信息
	 * @param user
	 * @return
	 */
	public ReturnDto updateProfile(User user);
	
	/**
	 * 更新头像
	 * @param userId
	 * @param saveFileName
	 */
	public ReturnDto updateAvatar(Long userId, String saveFileName);
	
	/**
	 * 更新密码
	 * @param username
	 * @param password
	 * @return
	 */
	public ReturnDto updatePassword(String username, String password);
	
	/**
	 * 获取登录用户所拥有的菜单
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月27日 下午5:09:48
	 */
	public Set<Menu> getUserRootMenu(Set<Role> roles);
	
	/**
	 * 根据id列表删除
	 * @param ids
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年9月27日 下午9:34:59
	 */
	public ReturnDto deleteByIds(List<Long> ids);
	
}