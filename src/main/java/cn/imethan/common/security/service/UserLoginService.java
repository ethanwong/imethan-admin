package cn.imethan.common.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import cn.imethan.security.entity.Menu;
import cn.imethan.security.entity.Permission;
import cn.imethan.security.entity.Role;
import cn.imethan.security.entity.User;
import cn.imethan.security.service.UserService;

/**
 * UserLoginService.java
 *
 * @author Ethan Wong
 * @time 2015年9月10日下午11:04:35
 */
@Transactional(readOnly = true)
public class UserLoginService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username "+ username + "not exists");
		}
		Set<GrantedAuthority> authorities = obtainGrantedAuthorities(user);
		boolean enabled = true;//账号是否启动
		boolean accountNonExpired = true;//是否没有过期
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		UserInfo userDetails = new UserInfo( user.getUsername(), 
				 user.getPassword(),
				 enabled, 
				 accountNonExpired, 
				 credentialsNonExpired, 
				 accountNonLocked, authorities);
		
		userDetails.setUsername(username);
		userDetails.setPassword(user.getPassword());
		userDetails.setUserId(user.getId());
		userDetails.setRolename(user.getRoleName());
		userDetails.setRoleId(user.getRoleId());
		userDetails.setCreateDate(user.getCreateTime());
		return userDetails;
	}

	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		System.out.println("---------------UserDetailsService curentUser Authority---------------");
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			for(Menu menu : role.getMenus()){
				System.out.println("UserDetailsService menu       name:" + menu.getName());
				authSet.add(new SimpleGrantedAuthority(menu.getName().trim()));
			}
			for (Permission permission : role.getPermissions()) {
				System.out.println("UserDetailsService permission name:" + permission.getName());
				authSet.add(new SimpleGrantedAuthority(permission.getName().trim()));
			}
		}
		return authSet;
	}

}
