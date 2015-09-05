package cn.imethan.common.security;

/**
 * CustomLogoutHandler.java
 *
 * @author Ethan Wong
 * @time 2015年9月4日下午10:29:43
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
 
 public class CustomLogoutHandler implements LogoutHandler {
 
     public CustomLogoutHandler() {
     }
 
     @Override
     public void logout(HttpServletRequest request,
             HttpServletResponse response, Authentication authentication) {
         System.out.println("CustomLogoutSuccessHandler.logout() is called!");
 
     }
 
 }


