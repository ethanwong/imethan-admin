package cn.imethan.common.mongodb;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
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
	
	void baseTest();
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
}
