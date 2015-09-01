package cn.imethan.common.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import cn.imethan.common.utils.ConvertUtil;

/**
 * SearchFilter.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午11:35:35
 */
public class SearchFilter {

	public enum MatchType {
		EQ, NE, GT, GE, LT, LE, GTOREQ, LTOREQ, LIKE, NOTLIKE, ISNULL, NOTNULL, ISEMPTY, NOTEMPTY, IN, NOTIN, BETWEEN, NOTBETWEEN;
	}

	public enum PropertyType {
		S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class), E(Enum.class);

		private Class<?> clazz;

		private PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return this.clazz;
		}
	}

	public static final String OR_SEPARATOR = "_OR_";
	public static final String _SEPARATOR = "_";
	public static final String COMMA_SEPARATOR = ",";
	public static final String SEMICOLON_SEPARATOR = ";";
	public static final String EMPTY_SEPARATOR = "";
	private MatchType matchType = null;
	private Object matchValue = null;
	private Class<?> propertyClass = null;
	private String[] propertyNames = null;

	public SearchFilter() {
		
	}

	public SearchFilter(String filterName, String filterValue) {
		String firstPart = StringUtils.substringBefore(filterName, "_");
		String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
		String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());
		try {
			this.matchType = ((MatchType) Enum.valueOf(MatchType.class, matchTypeCode));
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称:" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
		}
		try {
			this.propertyClass = ((PropertyType) Enum.valueOf(PropertyType.class, propertyTypeCode)).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称:" + filterName + "没有按规则编写,无法得到属性值类型.", e);
		}

		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		Assert.isTrue(StringUtils.isNotBlank(propertyNameStr), "filter名称:" + filterName + "没有按规则编写,无法得到属性名称.");
		this.propertyNames = StringUtils.splitByWholeSeparator(propertyNameStr, "_OR_");

		if ((!filterValue.equals("")) && (filterValue.contains(","))) {
			this.matchValue = filterValue;
		} else if ((!filterValue.equals("")) && (filterValue.contains(";"))) {
			this.matchValue = filterValue;
		} else if ((!filterValue.equals("")) && (filterValue.contains("_"))){
			this.matchValue = filterValue;
		} else{
			this.matchValue = ConvertUtil.convertStringToObject(filterValue, this.propertyClass);
		}
	}

	public static List<SearchFilter> buildSearchParams(Map<String, Object> searchParams) {
		List<SearchFilter> filterList = new ArrayList<SearchFilter>();

		for (Map.Entry entry : searchParams.entrySet()) {
			String filterName = (String) entry.getKey();
			String filterValue = (String) entry.getValue();

			if (StringUtils.isNotBlank(filterValue)) {
				SearchFilter filter = new SearchFilter(filterName, filterValue);
				filterList.add(filter);
			}
		}
		return filterList;
	}

	public MatchType getMatchType() {
		return this.matchType;
	}

	public Object getMatchValue() {
		return this.matchValue;
	}

	public Class<?> getPropertyClass() {
		return this.propertyClass;
	}

	public String[] getPropertyNames() {
		return this.propertyNames;
	}

	public String getPropertyName() {
		Assert.isTrue(this.propertyNames.length == 1, "只有一个查询属性在filter中.");
		return this.propertyNames[0];
	}

	public boolean hasMultiProperties() {
		return this.propertyNames.length > 1;
	}
}
