package cn.imethan.admin.base.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
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

	protected Class<T> clazz;

	public MyHibernateTemplate() {
		clazz = ReflectionUtil.getClassGenricType(this.getClass(), 0);
	}
	
	/**
	 * 获取session
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:25:18
	 */
	public Session getSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}
	
	//-------------------------------------------------------------------------
	// 增加方法
	//-------------------------------------------------------------------------
	
	/**
	 * 保存
	 * @param entity 实体信息
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:25:32
	 */
	public void save(T entity) {
		this.getSession().save(entity);
	}
	
	/**
	 * 保存或者更新
	 * @param entity 实体信息
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:26:14
	 */
	public void saveOrUpdate(T entity) {
		this.getSession().saveOrUpdate(entity);
	}
	
	//-------------------------------------------------------------------------
	// 删除方法
	//-------------------------------------------------------------------------
	
	/**
	 * 根据ID删除
	 * @param id 实体ID
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:26:28
	 */
	public void deleteById(P id) {
		Assert.isNull(id, "不能删除id为空的记录");
		this.hibernateTemplate.delete(this.getById(id));
	}
	
	/**
	 * 删除
	 * @param entity 实体信息
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:26:40
	 */
	public void delete(T entity) {
		Assert.isNull(entity, "不能删除entity为空的记录");
		this.hibernateTemplate.delete(entity);
	}
	
	//-------------------------------------------------------------------------
	// 查询方法
	//-------------------------------------------------------------------------
	
	/**
	 * 根据ID获取
	 * @param id 实体ID
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:27:15
	 */
	public T getById(P id) {
		return (T) this.getSession().get(clazz, id);
	}
	
	/**
	 * 获取全部列表
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:30:05
	 */
	public List<T> getAll() {
		return this.hibernateTemplate.loadAll(clazz);
	}
	
	/**
	 * 根据过滤条件查询列表
	 * @param searchFilter 过滤条件
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:30:21
	 */
	public List<T> getByFilter(SearchFilter searchFilter, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		return getByCriterions(isCache, new Criterion[] { criterion });
	}
	
	public Page<T> getPageByFilter(SearchFilter searchFilter, boolean isCache){
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		getByCriterions(isCache, new Criterion[] { criterion });
		
		return null;
	}
	
	/**
	 * 根据过滤条件列表查询列表
	 * @param searchFilters 过滤条件列表
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:31:37
	 */
	public List<T> getByFilters(List<SearchFilter> searchFilters, boolean isCache) {
		List<Criterion> list = new ArrayList<Criterion>();
		for(SearchFilter searchFilter : searchFilters){
			Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
			list.add(criterion);
		}
		return getByCriterions(isCache, (Criterion[]) list.toArray(new Criterion[list.size()]));
	}
	
	/**
	 * 根据过滤条件和排序条件查询列表
	 * @param searchFilter 过滤条件
	 * @param order 排序条件
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:32:27
	 */
	public List<T> getByFilterAndOrder(SearchFilter searchFilter,Order order, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		return getByCriterionsAndOrders(isCache, new Criterion[] { criterion },orders);
	}
	
	/**
	 * 根据过滤条件和排序条件列表查询列表
	 * @param searchFilter 过滤条件
	 * @param orders 排序条件
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:33:05
	 */
	public List<T> getByFilterAndOrders(SearchFilter searchFilter,List<Order> orders, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		return getByCriterionsAndOrders(isCache, new Criterion[] { criterion },orders);
	}
	
	/**
	 * 根据过滤条件列表和排序条件列表查询列表
	 * @param searchFilters 过滤条件列表
	 * @param orders 排序条件列表
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:33:37
	 */
	public List<T> getByFiltersAndOrders(List<SearchFilter> searchFilters,List<Order> orders, boolean isCache) {
		List<Criterion> list = new ArrayList<Criterion>();
		for(SearchFilter searchFilter : searchFilters){
			Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
			list.add(criterion);
		}
		return getByCriterionsAndOrders(isCache, (Criterion[]) list.toArray(new Criterion[list.size()]),orders);
	}
	
	

	
	//-------------------------------------------------------------------------
	// 其他辅助方法
	//-------------------------------------------------------------------------
	
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
