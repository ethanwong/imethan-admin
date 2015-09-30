package cn.imethan.common.mongodb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * BaseRepository.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年9月30日下午6:06:15
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
	
	
	/**
	 * Get MongoOperations{@link MongoOperations}.
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年10月1日上午5:38:14
	 */
	public MongoOperations getMongoOperations();
	
	/**
	 * Get MongoEntityInformation{@link MongoEntityInformation}.
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年10月1日上午5:39:49
	 */
	public MongoEntityInformation<T, ID> getMongoEntityInformation();
	
	/**
	 * Find page by parameters.
	 * 
	 * @param parameters
	 * @param pageable
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年10月1日上午2:00:05
	 */
	Page<T> findPageByParameters(Map<String, Object> parameters, Pageable pageable);
	
	/**
	 * Find page by SearchFilter{@link SearchFilter}.
	 * 
	 * @param searchFilter
	 * @param pageable
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年10月1日上午4:37:11
	 */
	Page<T> findPageBySearchFilter(SearchFilter searchFilter, Pageable pageable);
	
	/**
	 * Find page by SearchFilter{@link SearchFilter} list.
	 * 
	 * @param searchFilters
	 * @param pageable
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年10月1日上午4:37:39
	 */
	Page<T> findPageBySearchFilters(List<SearchFilter> searchFilters, Pageable pageable);
}
