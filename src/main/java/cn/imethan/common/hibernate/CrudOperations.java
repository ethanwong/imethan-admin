package cn.imethan.common.hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;

/**
 * CrudOperations.java
 * 接口类继承该类，实现CRUD操作
 *
 * @author Ethan Wong
 * @time 2015年8月31日下午11:06:49
 */
public abstract interface CrudOperations<T,P> {
	
	/**
	 * 保存
	 * 
	 * @param entity 实体信息
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:25:32
	 */
	public void save(T entity);
	
	/**
	 * 保存或者更新
	 * 
	 * @param entity 实体信息
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:26:14
	 */
	public void saveOrUpdate(T entity);
	
	/**
	 * 根据ID删除
	 * 
	 * @param id 实体ID
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:26:28
	 */
	public void deleteById(P id);
	
	/**
	 * 删除
	 * 
	 * @param entity 实体信息
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:26:40
	 */
	public void delete(T entity);
	
	/**
	 * 删除全部
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午10:28:42
	 */
	public void deleteAll();
	
	/**
	 * 根据ID获取
	 * 
	 * @param id 实体ID
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:27:15
	 */
	public T getById(P id);
	
	/**
	 * 根据ID列表获取
	 * @param ids ID列表
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午10:25:49
	 */
	public List<T> getByIdList(Collection<P> ids, boolean isCache);
	
	/**
	 * 获取全部列表
	 * 
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:30:05
	 */
	public List<T> getAll();
	
	/**
	 * 根据过滤条件查询列表
	 * 
	 * @param searchFilter 过滤条件
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:30:21
	 */
	public List<T> getByFilter(SearchFilter searchFilter, boolean isCache);
	
	/**
	 * 根据过滤条件列表查询列表
	 * 
	 * @param searchFilters 过滤条件列表
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:31:37
	 */
	public List<T> getByFilters(List<SearchFilter> searchFilters, boolean isCache);
	
	/**
	 * 根据过滤条件和排序条件查询列表
	 * 
	 * @param searchFilter 过滤条件
	 * @param order 排序条件
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:32:27
	 */
	public List<T> getByFilterAndOrder(SearchFilter searchFilter, Order order, boolean isCache);
	
	/**
	 * 根据过滤条件和排序条件列表查询列表
	 * 
	 * @param searchFilter 过滤条件
	 * @param orders 排序条件
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:33:05
	 */
	public List<T> getByFilterAndOrders(SearchFilter searchFilter, List<Order> orders, boolean isCache);
	
	/**
	 * 根据过滤条件列表和排序条件查询列表
	 * @param searchFilters 过滤条件列表
	 * @param order 排序条件
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午9:52:11
	 */
	public List<T> getByFiltersAndOrder(List<SearchFilter> searchFilters, Order order, boolean isCache);
	
	/**
	 * 根据过滤条件列表和排序条件列表查询列表
	 * 
	 * @param searchFilters 过滤条件列表
	 * @param orders 排序条件列表
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午8:33:37
	 */
	public List<T> getByFiltersAndOrders(List<SearchFilter> searchFilters, List<Order> orders, boolean isCache);
	
	/**
	 * 根据HQL获取分页列表
	 * @param page 分页信息
	 * @param isCache 是否缓存
	 * @param hql HQL语句
	 * @param values 参数
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午10:20:50
	 */
	public Page<T> getPageByHql(Page<T> page, boolean isCache, String hql, Map<String, ?> values) ;
	
	/**
	 * 获取分页列表
	 * @param page 分页信息
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午9:39:46
	 */
	public Page<T> getPage(Page<T> page, boolean isCache);
	
	/**
	 * 根据过滤条件获取分页列表
	 * @param page 分页信息
	 * @param searchFilter 过滤条件
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午9:32:29
	 */
	public Page<T> getPageByFilter(Page<T> page,SearchFilter searchFilter, boolean isCache);
	
	/**
	 * 根据过滤条件和排序条件获取分页列表
	 * @param page 分页信息
	 * @param searchFilter 过滤条件
	 * @param order 排序条件
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午9:45:06
	 */
	public Page<T> getPageByFilterAndOrder(Page<T> page,SearchFilter searchFilter,Order order, boolean isCache);
	
	/**
	 * 根据过滤条件和排序条件列表获取分页列表
	 * @param page 分页信息
	 * @param searchFilter 过滤条件
	 * @param orders 排序条件列表
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午9:48:15
	 */
	public Page<T> getPageByFilterAndOrders(Page<T> page,SearchFilter searchFilter,List<Order> orders, boolean isCache);
	
	/**
	 * 根据过滤条件列表和排序条件列表获取分页列表
	 * @param page 分页信息
	 * @param searchFilters 过滤条件列表
	 * @param orders 排序条件列表
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午9:49:51
	 */
	public Page<T> getPageByFiltersAndOrders(Page<T> page,List<SearchFilter> searchFilters,List<Order> orders, boolean isCache);
	
	/**
	 * 根据过滤条件列表获取分页列表
	 * @param page 分页信息
	 * @param searchFilter 过滤条件
	 * @param isCache 是否缓存
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年8月31日 下午9:32:29
	 */
	public Page<T> getPageByFilters(Page<T> page,List<SearchFilter> searchFilters, boolean isCache) ;
}


