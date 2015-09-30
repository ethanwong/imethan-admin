package cn.imethan.common.mongodb;

import java.io.Serializable;
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
	public Page<T> findPageByParameters(Map<String, Object> parameters, Pageable pageable) {
		
		Query query = QueryUtils.dynamicGenerateQuery(SearchFilter.parse(parameters).entrySet());
		List<T> list =  mongoOperations.find(query.with(pageable),metadata.getJavaType());
		long count = mongoOperations.count(query, metadata.getJavaType());
		
		return new PageImpl<T>(list, pageable, count);
	}


	@Override
	public void baseTest() {
		System.out.println("test:"+mongoOperations.findAll(metadata.getJavaType()));
	}

}