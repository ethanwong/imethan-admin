package cn.imethan.common.security.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import cn.imethan.security.entity.Menu;
import cn.imethan.security.entity.Permission;

/**
 * InvocationSecurityMetadataSource.java
 *
 * @author Ethan Wong
 * @time 2015年9月10日下午11:43:00
 */
public class InvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private AntPathMatcher urlMatcher = new AntPathMatcher();
	
	public SessionFactory sessionFactory;

	public InvocationSecurityMetadataSource(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.loadAuthorityDefine();
	}
	
	@SuppressWarnings("unchecked")
	private List<Permission> getAllPermissionList(){
		return sessionFactory.openSession().createQuery("from Permission").list();
	}
	
	@SuppressWarnings("unchecked")
	private List<Menu> getAllMenuList(){
		return sessionFactory.openSession().createQuery("from Menu").list();
	}
	
	private void loadAuthorityDefine() {		
		System.out.println("---------------loadAuthorityDefine Authority---------------");
		if(resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            
	          Iterable<Menu> menuAuthorityList = this.getAllMenuList();
	          for (Menu menu : menuAuthorityList) {
	          	System.out.println("InvocationSecurityMetadataSource menu:"+menu.getPrefixedName() +" -url:"+menu.getUrl());
	          	Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
	          	ConfigAttribute configAttribute = new SecurityConfig(menu.getPrefixedName().trim());
	          	configAttributes.add(configAttribute);
	          	resourceMap.put(menu.getUrl().trim(), configAttributes);
	          }
            
            Iterable<Permission> permissionAuthorityList = this.getAllPermissionList();
            for (Permission permission : permissionAuthorityList) {
            	System.out.println("InvocationSecurityMetadataSource permission:"+permission.getPrefixedName() +" -url:"+permission.getUrl());
            	Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                ConfigAttribute configAttribute = new SecurityConfig(permission.getPrefixedName().trim());
                configAttributes.add(configAttribute);
                resourceMap.put(permission.getUrl().trim(), configAttributes);
            }
        }
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestURL = ((FilterInvocation) object).getRequestUrl();
		if(requestURL.indexOf("?") != -1) {
			requestURL = requestURL.substring(0, requestURL.indexOf("?"));
        }
		
		if (resourceMap == null) {
			this.loadAuthorityDefine();
		}
		Iterator<String> iterator = resourceMap.keySet().iterator();
		while (iterator.hasNext()) {
			String resRUL = iterator.next();
			if (urlMatcher.match(resRUL, requestURL)) {
				return resourceMap.get(resRUL);
			}
		}
		return null;
	}
	
	public static void reFresh() {
		resourceMap = null;
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

}