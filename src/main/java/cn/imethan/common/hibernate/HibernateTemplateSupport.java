package cn.imethan.common.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.util.Assert;

import cn.imethan.common.hibernate.SearchFilter.MatchType;
import cn.imethan.common.utils.ConvertUtil;
import cn.imethan.common.utils.ReflectionUtil;

/**
 * HibernateTemplateSupport.java
 * 
 * DAO实现类继承该类，实现CRUD操作
 *
 * @author Ethan Wong
 * @time 2015年8月29日下午10:39:57
 */
public class HibernateTemplateSupport<T, P extends Serializable> implements CrudOperations<T,P> {

	@Autowired
	protected SessionFactory sessionFactory;
	@Autowired
	protected HibernateTemplate hibernateTemplate;

	protected Class<T> clazz;//实体类

	public HibernateTemplateSupport() {
		clazz = ReflectionUtil.getClassGenricType(this.getClass(), 0);
	}

	/**
	 * 获取session
	 * 
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:25:18
	 */
	public Session getSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}
	
	/**
	 * 创建HQL查询
	 * @param hql
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午10:22:36
	 */
	public Query createQuery(String hql){
		return this.getSession().createQuery(hql);
	}

	// -------------------------------------------------------------------------
	// 增加方法
	// -------------------------------------------------------------------------
	@Override
	public void save(T entity) {
		this.getSession().save(entity);
	}
	
	@Override
	public void saveOrUpdate(T entity) {
		this.getSession().saveOrUpdate(entity);
	}

	// -------------------------------------------------------------------------
	// 删除方法
	// -------------------------------------------------------------------------
	@Override
	public void deleteById(P id) {
		this.hibernateTemplate.delete(this.getById(id));
	}

	@Override
	public void delete(T entity) {
		Assert.isNull(entity, "不能删除entity为空的记录");
		this.hibernateTemplate.delete(entity);
	}
	
	@Override
	public void deleteAll(){
		String entityName = this.hibernateTemplate.getSessionFactory().getClassMetadata(this.clazz).getEntityName();
	    String DELETE_ALL = "delete from " + entityName;
	    getSession().createQuery(DELETE_ALL);
	  }

	// -------------------------------------------------------------------------
	// 查询方法
	// -------------------------------------------------------------------------

	@Override
	public T getById(P id) {
		return (T) this.getSession().get(clazz, id);
	}
	
	@Override
	public List<T> getByIdList(Collection<P> ids, boolean isCache) {
		String idName = this.hibernateTemplate.getSessionFactory().getClassMetadata(this.clazz).getIdentifierPropertyName();
		return getByCriterions(isCache, new Criterion[] { Restrictions.in(idName, ids) });
	}
	
	@Override
	public List<T> getAll() {
		return this.hibernateTemplate.loadAll(clazz);
	}

	@Override
	public List<T> getByFilter(SearchFilter searchFilter, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		return getByCriterions(isCache, new Criterion[] { criterion });
	}
	
	@Override
	public List<T> getByFilters(List<SearchFilter> searchFilters, boolean isCache) {
		List<Criterion> list = new ArrayList<Criterion>();
		for (SearchFilter searchFilter : searchFilters) {
			Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
			list.add(criterion);
		}
		return getByCriterions(isCache, (Criterion[]) list.toArray(new Criterion[list.size()]));
	}
	
	@Override
	public List<T> getByFilterAndOrder(SearchFilter searchFilter, Order order, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		return getByCriterionsAndOrders(isCache, new Criterion[] { criterion }, orders);
	}

	@Override
	public List<T> getByFilterAndOrders(SearchFilter searchFilter, List<Order> orders, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		return getByCriterionsAndOrders(isCache, new Criterion[] { criterion }, orders);
	}
	
	@Override
	public List<T> getByFiltersAndOrder(List<SearchFilter> searchFilters, Order order, boolean isCache) {
		List<Criterion> list = new ArrayList<Criterion>();
		for (SearchFilter searchFilter : searchFilters) {
			Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
			list.add(criterion);
		}
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		return getByCriterionsAndOrders(isCache, (Criterion[]) list.toArray(new Criterion[list.size()]), orders);
	}
	
	@Override
	public List<T> getByFiltersAndOrders(List<SearchFilter> searchFilters, List<Order> orders, boolean isCache) {
		List<Criterion> list = new ArrayList<Criterion>();
		for (SearchFilter searchFilter : searchFilters) {
			Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
			list.add(criterion);
		}
		return getByCriterionsAndOrders(isCache, (Criterion[]) list.toArray(new Criterion[list.size()]), orders);
	}
	
	@Override
	public Page<T> getPageByHql(Page<T> page, boolean isCache, String hql, Map<String, ?> values) {
		Assert.notNull(page, "page不能为空");
		Query query = this.getSession().createQuery(hql).setProperties(values);
		long totalCount = countSqlResult(hql, isCache, values);
		page.setTotalCount(totalCount);
		if (isCache) {
			query.setCacheable(true);
		}
		page.setList(query.list());		
		return page;
	}
	
	@Override
	public Page<T> getPage(Page<T> page, boolean isCache) {
		return getPageByCriterions(page, isCache, new Criterion[0],null);
	}
	
	@Override
	public Page<T> getPageByFilter(Page<T> page,SearchFilter searchFilter, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(searchFilter);
		return this.getPageByCriterions(page, isCache, new Criterion[] { criterion },searchFilters);
	}
	
	@Override
	public Page<T> getPageByFilterAndOrder(Page<T> page,SearchFilter searchFilter,Order order, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		return this.getPageByCriterionsAndOrders(page, isCache, new Criterion[] { criterion },orders,null);
	}
	
	@Override
	public Page<T> getPageByFilterAndOrders(Page<T> page,SearchFilter searchFilter,List<Order> orders, boolean isCache) {
		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
		return this.getPageByCriterionsAndOrders(page, isCache, new Criterion[] { criterion },orders,null);
	}
	
	@Override
	public Page<T> getPageByFiltersAndOrders(Page<T> page,List<SearchFilter> searchFilters,List<Order> orders, boolean isCache) {
		List<Criterion> list = new ArrayList<Criterion>();
		for (SearchFilter searchFilter : searchFilters) {
			Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
			list.add(criterion);
		}
		
		return this.getPageByCriterionsAndOrders(page, isCache, (Criterion[]) list.toArray(new Criterion[list.size()]),orders,null);
	}
	
	@Override
	public Page<T> getPageByFilters(Page<T> page,List<SearchFilter> searchFilters, boolean isCache) {
		List<Criterion> list = new ArrayList<Criterion>();
		for (SearchFilter searchFilter : searchFilters) {
			Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
			list.add(criterion);
		}
		return this.getPageByCriterions(page, isCache, (Criterion[]) list.toArray(new Criterion[list.size()]),null);
	}

	// -------------------------------------------------------------------------
	// 其他辅助方法
	// -------------------------------------------------------------------------
	private Page<T> getPageByCriterions(Page<T> page, boolean isCache, Criterion[] criterions,List<SearchFilter> searchFilters) {
		Criteria criteria = createCriteria(isCache, criterions);
		
//		page.setTotalCount((Long) criteria.setProjection(Projections.rowCount()).uniqueResult());
//		criteria.setProjection(null);  
		
		if(searchFilters == null || searchFilters.isEmpty()){
			page.setTotalCount(this.countCriteriaResult(criteria));
		}else{
			page.setTotalCount(this.countFiltersResult(searchFilters, isCache));
		}
		
		criteria.setFirstResult(page.getFirstResult());
		criteria.setMaxResults(page.getMaxResults());
		List<T> list = criteria.list();
		page.setList(list);
		
		return page;
	}
	
	private Page<T> getPageByCriterionsAndOrders(Page<T> page, boolean isCache, Criterion[] criterions,List<Order> orders,List<SearchFilter> searchFilters) {
		Criteria criteria = createCriteria(isCache, criterions);
		for (Order order : orders) {
			criteria.addOrder(order);
		}
		
		criteria.setFirstResult(page.getFirstResult());
		criteria.setMaxResults(page.getMaxResults());
		List<T> list = criteria.list();
		page.setList(list);
		if(searchFilters == null || searchFilters.isEmpty()){
			page.setTotalCount(this.countCriteriaResult(criteria));
		}else{
			page.setTotalCount(this.countFiltersResult(searchFilters, isCache));
		}
		return page;
	}

	private long countSqlResult(String hql, boolean isCache, Map<String, ?> values) {
		String countHql = prepareCountHql(hql);
		try {
			Query query = this.getSession().createQuery(countHql);
			query.setCacheable(isCache);
			Long count = (Long) query.uniqueResult();
			return count.longValue();
		} catch (Exception e) {
			throw new RuntimeException("HQL不能自动统计个数,该HQL语句是:" + countHql, e);
		}

	}
	
	private String prepareCountHql(String orgHql) {
		String fromHql = orgHql;

		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;
		return countHql;
	}

	private long countCriteriaResult(Criteria criteria) {
		CriteriaImpl impl = (CriteriaImpl) criteria;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtil.getFieldValue(impl, "orderEntries");
			ReflectionUtil.setFieldValue(impl, "orderEntries", new ArrayList<Object>());
		} catch (Exception e) {
			throw new IllegalArgumentException("抛出的异常:" + e.getMessage());
		}

		// 执行Count查询
		Long totalCountObject = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = totalCountObject != null ? totalCountObject.longValue() : 0L;

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		criteria.setProjection(projection);

		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}

		if (transformer != null) {
			criteria.setResultTransformer(transformer);
		}

		try {
			ReflectionUtil.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new IllegalArgumentException("抛出的异常:" + e.getMessage());
		}

		return totalCount;
	}

//	private long countFilterResult(SearchFilter searchFilter, boolean isCache) {
//		Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
//		Criteria criteria = createCriteria(isCache, new Criterion[] { criterion });
//		return countCriteriaResult(criteria);
//	}

	private long countFiltersResult(List<SearchFilter> searchFilters, boolean isCache) {
		List<Criterion> list = new ArrayList<Criterion>();
		for (SearchFilter searchFilter : searchFilters) {
			Criterion criterion = buildCriterionBySearchFilter(searchFilter.getPropertyName(), searchFilter.getPropertyClass(), searchFilter.getMatchValue(), searchFilter.getMatchType());
			list.add(criterion);
		}
		Criteria criteria = createCriteria(isCache, (Criterion[]) list.toArray(new Criterion[list.size()]));
		return countCriteriaResult(criteria);
	}

	private List<T> getByCriterions(boolean isCache, Criterion[] criterions) {
		return createCriteria(isCache, criterions).list();
	}

	private List<T> getByCriterionsAndOrders(boolean isCache, Criterion[] criterions, List<Order> orders) {
		Criteria criteria = createCriteria(isCache, criterions);
		for (Order order : orders) {
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