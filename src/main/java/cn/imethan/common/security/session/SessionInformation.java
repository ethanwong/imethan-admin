package cn.imethan.common.security.session;

import java.io.Serializable;
import java.util.Date;

import org.springframework.util.Assert;

/**
 * MySessionInformation.java
 *
 * @author Ethan Wong
 * @time 2015年9月15日下午10:14:26
 */
public class SessionInformation implements Serializable {

	private static final long serialVersionUID = 8702796273316564601L;
	
	// ~ Instance fields
	// ================================================================================================

	private Date lastRequest;
	private final Object principal;
	private final String sessionId;
	private boolean expired = false;
	private final String ip;

	// ~ Constructors
	// ===================================================================================================

	public SessionInformation(Object principal, String sessionId, Date lastRequest,String ip) {
		Assert.notNull(principal, "Principal required");
		Assert.hasText(sessionId, "SessionId required");
		Assert.notNull(lastRequest, "LastRequest required");
		this.principal = principal;
		this.sessionId = sessionId;
		this.lastRequest = lastRequest;
		this.ip = ip;
	}

	// ~ Methods
	// ========================================================================================================

	public void expireNow() {
		this.expired = true;
	}

	public Date getLastRequest() {
		return lastRequest;
	}

	public Object getPrincipal() {
		return principal;
	}

	public String getSessionId() {
		return sessionId;
	}

	public boolean isExpired() {
		return expired;
	}

	/**
	 * Refreshes the internal lastRequest to the current date and time.
	 */
	public void refreshLastRequest() {
		this.lastRequest = new Date();
	}

	public String getIp() {
		return ip;
	}
}
