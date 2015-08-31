package cn.imethan.admin.base.hibernate;

/**
 * SimpleSearch.java
 *
 * @author Ethan Wong
 * @time 2015年8月31日下午11:06:49
 */
public abstract interface SimpleSearch<T> {
	
	public void save(T entity);
}


