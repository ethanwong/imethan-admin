package cn.imethan.admin.repository.cms;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import cn.imethan.admin.document.cms.Channel;
import cn.imethan.admin.repository.cms.custom.ChannelRepositoryCustom;
import cn.imethan.common.mongodb.BaseRepository;

/**
 * ChannelRepository.java
 *
 * @author Ethan Wong
 * @time 2014年3月2日下午4:44:45
 */
@Repository
public interface ChannelRepository extends MongoRepository<Channel, String>,
				ChannelRepositoryCustom<Channel, String>, BaseRepository<Channel, String> {
	
	/**
	 * @see http://docs.spring.io/spring-data/data-mongo/docs/1.8.0.RELEASE/reference/html/
	 *
	 * @author Ethan Wong
	 * @datetime 2015年10月1日上午5:21:48
	 */
	
	Long countByTitle(String title);
	
	Long deleteByTitle(String title);

	List<Channel> removeByTitle(String title);
	
	List<Channel> findByTitle(String title);
	
	List<Channel> findByTitle(String title, Sort sort);

	Page<Channel> findByTitle(String title, Pageable pageable);
	
	//Slice<Channel> findByTitle(String title, Pageable pageable);
	
	//List<Channel> findByTitle(String title, Pageable pageable);
	
	List<Channel> findTop3ByTitle(String title, Pageable pageable);
	
	List<Channel> findFirst10ByTitle(String title, Sort sort);


	List<Channel> readAllByTitleNotNull();
	
}
