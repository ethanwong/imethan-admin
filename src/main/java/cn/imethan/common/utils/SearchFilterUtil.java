package cn.imethan.common.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import cn.imethan.common.hibernate.SearchFilter;

/**
 * SearchFilterUtil.java
 *
 * @author Ethan Wong
 * @time 2015年11月7日下午10:53:58
 */
public class SearchFilterUtil {
    
	/**
	 * 从HttpRequest中创建SearchFilter列表,默认Filter属性名前缀为search.
	 * 
	 * @param request
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年11月7日 下午10:54:42
	 */
    public static List<SearchFilter> buildByHttpRequest(final ServletRequest request) {
        return buildByHttpRequest(request, "search");
    }

    /**
     * 从ServletRequest中创建SearchFilter列表 SearchFilter命名规则为Filter属性前缀_比较类型属性类型_属性名.
     * eg. filter_EQS_name filter_LIKES_name_OR_email.
     * 
     * @param request ServletRequest请求参数.
     * @param filterPrefix Filter属性名前缀.
     * @return
     * 
	 * @author Ethan
	 * @datetime 2015年11月7日 下午10:54:42
     */
    public static List<SearchFilter> buildByHttpRequest(final ServletRequest request, final String filterPrefix) {
        List<SearchFilter> filterList = new ArrayList<SearchFilter>();

        // 从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
        Map<String, Object> filterParamMap = SearchFilterUtil.getParametersStartingWith(request, filterPrefix + "_");

        // 分析参数Map,构造SearchFilter列表
        for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
            String filterName = entry.getKey();
            String value = (String) entry.getValue();
            // 如果value值为空,则忽略此filter.
            if (StringUtils.isNotBlank(value)) {
                SearchFilter filter = new SearchFilter(filterName, value);
                filterList.add(filter);
            }
        }

        return filterList;
    }
    
    /**
     * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
     * 返回的结果的Parameter名已去除前缀.
     * 
     * @param request http请求.
     * @param prefix 前缀.
     * @return
     * 
	 * @author Ethan
	 * @datetime 2015年11月7日 下午10:54:42
     */
    public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
        Validate.notNull(request, "Request must not be null");
        @SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null) {
            prefix = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values == null || values.length == 0) {
                    // Do nothing, no values found at all.
                } else if (values.length > 1) {
                    params.put(unprefixed, values);
                } else {
                    params.put(unprefixed, values[0]);
                }
            }
        }
        return params;
    }

}
