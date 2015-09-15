package cn.imethan.common.security.filter;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * SecurityAccessDecisionManager.java
 *
 * @author Ethan Wong
 * @time 2015年9月10日下午11:40:11
 */
public class SecurityAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			String needPermission = configAttribute.getAttribute();
			System.out.println("SecurityAccessDecisionManager haveAuthorities:"+authentication.getAuthorities());
			System.out.println("SecurityAccessDecisionManager needPermission:"+needPermission);
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				if (needPermission.equals("ROLE_"+grantedAuthority.getAuthority())) {
					return;
				}
			}
			throw new AccessDeniedException("No Access");
		}
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}