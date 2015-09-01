package cn.imethan.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;

/**
 * ConvertUtil.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午11:54:15
 */
public class ConvertUtil {
	
	public static List convertElementPropertyToList(Collection collection, String propertyName) {
		List list = new ArrayList();
		try {
			for (Iterator it = collection.iterator(); it.hasNext();) {
				Object obj = it.next();
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw ReflectionUtil.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	public static String convertElementPropertyToString(Collection collection, String propertyName, String separator) {
		List list = convertElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	public static Object convertStringToObject(String value, Class<?> toType) {
		try {
			return ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw ReflectionUtil.convertReflectionExceptionToUnchecked(e);
		}
	}

	public static Object[] convertStringToObjects(String values, Class<?> toType) {
		try {
			String[] strValues = StringUtils.split(values, ",");
			Object[] objects = new Object[strValues.length];
			for (int i = 0; i < strValues.length; i++) {
				objects[i] = ConvertUtils.convert(strValues[i].trim(), toType);
				System.out.println("objects[i]：" + objects[i]);
			}
			return objects;
		} catch (Exception e) {
			throw ReflectionUtil.convertReflectionExceptionToUnchecked(e);
		}
	}

	private static void registerDateConverter() {
		DateConverter dc = new DateConverter();
		dc.setUseLocaleFormat(true);
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		ConvertUtils.register(dc, Date.class);
	}

	static {
		registerDateConverter();
	}
}
