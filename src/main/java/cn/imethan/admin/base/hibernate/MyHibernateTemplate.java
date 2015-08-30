package cn.imethan.admin.base.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.util.Assert;

import cn.imethan.admin.base.hibernate.SearchFilter.MatchType;
import cn.imethan.admin.base.hibernate.SearchFilter.PropertyType;

/**
 * MyHibernateTemplate.java
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午10:39:57
 */
public class MyHibernateTemplate<T, P extends Serializable> {

	@Autowired
	protected SessionFactory sessionFactory;
	@Autowired
	protected HibernateTemplate hibernateTemplate;

	protected Class<T> clazz;

	public MyHibernateTemplate() {
		clazz = ReflectionUtil.getClassGenricType(this.getClass(), 0);
	}

	public Session getSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	public void save(T entity) {
		this.getSession().save(entity);
	}

	public void saveOrUpdate(T entity) {
		this.getSession().saveOrUpdate(entity);
	}

	public void deleteById(P id) {
		Assert.isNull(id, "不能删除id为空的记录");
		this.hibernateTemplate.delete(this.getById(id));
	}

	public void delete(T entity) {
		Assert.isNull(entity, "不能删除entity为空的记录");
		this.hibernateTemplate.delete(entity);
	}

	public T getById(P id) {
		return (T) this.getSession().get(clazz, id);
	}

	public List<T> getAll() {
		return this.hibernateTemplate.loadAll(clazz);
	}

	public List<T> getByFilter(SearchFilter searchFilter, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		return getByCriterions(isCache, new Criterion[] { criterion });
	}
	
	public List<T> getByFilters(List<SearchFilter> searchFilters, boolean isCache) {
		List<Criterion> list = new ArrayList<Criterion>();
		for(SearchFilter searchFilter : searchFilters){
			Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
			list.add(criterion);
		}
		return getByCriterions(isCache, (Criterion[]) list.toArray(new Criterion[list.size()]));
	}
	
	public List<T> getByFilterAndOrders(SearchFilter searchFilter,List<Order> orders, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		return getByCriterionsAndOrders(isCache, new Criterion[] { criterion },orders);
	}

	private List<T> getByCriterions(boolean isCache, Criterion[] criterions) {
		return createCriteria(isCache, criterions).list();
	}
	
	private List<T> getByCriterionsAndOrders(boolean isCache, Criterion[] criterions,List<Order> orders) {
		Criteria criteria = createCriteria(isCache, criterions);
		for(Order order : orders){
			criteria.addOrder(order);
		}
		return criteria.list();
	}

	private Criteria createCriteria(boolean isCache, Criterion[] criterions) {
		Criteria criteria = getSession().createCriteria(clazz);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		if (isCache) {
			criteria.setCacheable(true);
		}
		return criteria;
	}

	private Criterion buildCriterionBySearchFilter(String propertyName, Class<?> propertyClass, Object propertyValue, MatchType matchType) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = null;

		switch (matchType.ordinal() + 1) {
		case 1:
			criterion = Restrictions.eq(propertyName, propertyValue);
			break;
		case 2:
			criterion = Restrictions.ne(propertyName, propertyValue);
			break;
		case 3:
			criterion = Restrictions.le(propertyName, propertyValue);
			break;
		case 4:
			criterion = Restrictions.lt(propertyName, propertyValue);
			break;
		case 5:
			criterion = Restrictions.ge(propertyName, propertyValue);
			break;
		case 6:
			criterion = Restrictions.gt(propertyName, propertyValue);
			break;
		case 7:
			criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			break;
		case 8:
			criterion = Restrictions.not(Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE));
			break;
		case 9:
			criterion = Restrictions.isNull(propertyName);
			break;
		case 10:
			criterion = Restrictions.isNotNull(propertyName);
			break;
		case 11:
			criterion = Restrictions.isEmpty(propertyName);
			break;
		case 12:
			criterion = Restrictions.isNotEmpty(propertyName);
			break;
		case 13:
			Object[] inValues = ConvertUtil.convertStringToObjects(propertyValue.toString(), propertyClass);
			criterion = Restrictions.in(propertyName, inValues);
			break;
		case 14:
			Object[] notInValues = ConvertUtil.convertStringToObjects(propertyValue.toString(), propertyClass);
			criterion = Restrictions.not(Restrictions.in(propertyName, notInValues));
			break;
		case 15:
			Object[] betweenValues = ConvertUtil.convertStringToObjects(propertyValue.toString(), propertyClass);
			criterion = Restrictions.between(propertyName, betweenValues[0], betweenValues[1]);
			break;
		case 16:
			Object[] notBetweenValues = ConvertUtil.convertStringToObjects(propertyValue.toString(), propertyClass);
			criterion = Restrictions.not(Restrictions.between(propertyName, notBetweenValues[0], notBetweenValues[1]));
		}
		return criterion;
	}

}
