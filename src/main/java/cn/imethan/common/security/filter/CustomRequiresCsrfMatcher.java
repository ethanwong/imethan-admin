package cn.imethan.common.security.filter;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * DefaultRequiresCsrfMatcher.java
 *
 * @author Ethan Wong
 * @time 2015年9月27日下午11:45:25
 */
class CustomRequiresCsrfMatcher implements RequestMatcher {
	//在原有的基础上添加POST支持
	private Pattern allowedMethods = Pattern.compile("^(POST|GET|HEAD|TRACE|OPTIONS)$");

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.web.util.matcher.RequestMatcher#matches(javax.
	 * servlet.http.HttpServletRequest)
	 */
	public boolean matches(HttpServletRequest request) {
		return !allowedMethods.matcher(request.getMethod()).matches();
	}
}


