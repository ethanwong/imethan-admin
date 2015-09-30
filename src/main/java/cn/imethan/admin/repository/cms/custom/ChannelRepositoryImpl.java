package cn.imethan.admin.repository.cms.custom;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * ChannelRepositoryImpl.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年9月30日上午10:44:20
 */
public class ChannelRepositoryImpl<T, ID extends Serializable> implements ChannelRepositoryCustom<T,ID>{
	
	@Autowired
	private MongoTemplate  mongoTemplate;
	
	public String getDbName() {
		return mongoTemplate.getDb().getName();
	}
}
