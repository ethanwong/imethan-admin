package cn.imethan.admin.base.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.util.Assert;

import cn.imethan.admin.base.hibernate.SearchFilter.MatchType;

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
	@Autowired
	protected MyHibernateDaoSupport myHibernateDaoSupport;
	protected Class<T> clazz;

	public MyHibernateTemplate() {
		clazz = this.getSuperClassGenricType(this.getClass(), 0);
	}

	public Session getSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	public void save(T entity) {
		this.getSession().save(entity);
	}

	public T getById(P id) {
		return (T) this.getSession().get(clazz, id);
	}

	public List<T> getAll() {
		return this.hibernateTemplate.loadAll(clazz);
	}

	public List<T> findByFilter(SearchFilter searchFilter, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());

		return findByCriterions(isCache, new Criterion[] { criterion });
	}

	private List<T> findByCriterions(boolean isCache, Criterion[] criterions) {
		return createCriteria(isCache, criterions).list();
	}

	public Criteria createCriteria(boolean isCache, Criterion[] criterions) {
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

		switch (matchType.ordinal()+1) {
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

	public Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if ((index >= params.length) || (index < 0)) {
			return Object.class;
		}
		if (!params[index].getClass().isAssignableFrom(Class.class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

}
