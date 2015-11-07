package cn.imethan.common.security.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;


/**
 * SecurityContext.java
 *
 * @author Ethan Wong
 * @time 2015年11月7日下午11:43:38
 */
public class SecurityContext {

    /**
     * 系统资源MAP.
     */
    public static Map<String, Object> resourceMap = new HashMap<String, Object>();

    /**
     * 获取当前登录用户信息
     * @return
     *
     * @author Ethan
     * @datetime 2015年11月7日 下午11:45:11
     */
    public static UserInfo getUserInfo() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }



}
