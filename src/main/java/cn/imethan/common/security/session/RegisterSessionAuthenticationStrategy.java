package cn.imethan.common.security.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.util.Assert;

/**
 * MyRegisterSessionAuthenticationStrategy.java
 *
 * @author Ethan Wong
 * @time 2015年9月15日下午10:10:47
 */
public class RegisterSessionAuthenticationStrategy implements SessionAuthenticationStrategy {

	private final SessionRegistry sessionRegistry;

	/**
	 * @param sessionRegistry
	 *            the session registry which should be updated when the
	 *            authenticated session is changed.
	 */
	public RegisterSessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
		Assert.notNull(sessionRegistry, "The sessionRegistry cannot be null");
		this.sessionRegistry = sessionRegistry;
	}

	/**
	 * In addition to the steps from the superclass, the sessionRegistry will be
	 * updated with the new session information.
	 */
	public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		
		String ip = ((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress();
		
		sessionRegistry.registerNewSession(request.getSession().getId(), authentication.getPrincipal(), ip);
	}
}
