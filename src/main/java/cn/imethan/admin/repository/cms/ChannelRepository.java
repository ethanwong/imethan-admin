package cn.imethan.admin.repository.cms;

import org.springframework.data.mongodb.repository.MongoRepository;
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

}
