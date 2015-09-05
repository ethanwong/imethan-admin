package cn.imethan.common.security;

/**
 * CustomLoginHandler.java
 *
 * @author Ethan Wong
 * @time 2015年9月4日下午10:45:57
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomLoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		super.onAuthenticationSuccess(request, response, authentication);

		// 这里可以追加开发人员自己的额外处理
		System.out.println("CustomLoginHandler.onAuthenticationSuccess() is called!");
	}

}
