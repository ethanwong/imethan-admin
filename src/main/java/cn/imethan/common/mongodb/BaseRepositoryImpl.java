package cn.imethan.common.mongodb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

/**
 * BaseRepositoryImpl.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年9月30日下午6:08:09
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID>implements BaseRepository<T, ID> {

	private MongoOperations mongoOperations;
	private MongoEntityInformation<T, ID> metadata;
	

	public BaseRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
		this.mongoOperations = mongoOperations;
		this.metadata = metadata;
	}
	

	@Override
	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	@Override
	public MongoEntityInformation<T, ID> getMongoEntityInformation() {
		return metadata;
	}

	@Override
	public Page<T> findPageByParameters(Map<String, Object> parameters, Pageable pageable) {
		
		Query query = QueryUtils.dynamicGenerateQuery(SearchFilter.parse(parameters).entrySet());
		List<T> list =  mongoOperations.find(query.with(pageable),metadata.getJavaType());
		long count = mongoOperations.count(query, metadata.getJavaType());
		
		return new PageImpl<T>(list, pageable, count);
	}
	
	public Page<T> findPageBySearchFilter(SearchFilter searchFilter, Pageable pageable){
		
		List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
		searchFilters.add(searchFilter);
		
		return this.findPageBySearchFilters(searchFilters, pageable);
		
	}
	public Page<T> findPageBySearchFilters(List<SearchFilter> searchFilters, Pageable pageable){
		
		Query query = QueryUtils.dynamicGenerateQuery(searchFilters);
		
		List<T> list =  mongoOperations.find(query.with(pageable),metadata.getJavaType());
		long count = mongoOperations.count(query, metadata.getJavaType());
		
		return new PageImpl<T>(list, pageable, count);
	}
}