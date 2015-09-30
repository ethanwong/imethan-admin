package cn.imethan.admin.service.cms.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.imethan.admin.document.cms.Channel;
import cn.imethan.admin.repository.cms.ChannelRepository;
import cn.imethan.admin.service.cms.ChannelService;

/**
 * ChannelServiceImpl.java
 *
 * @author Ethan Wong
 * @time 2014年3月2日下午4:45:52
 */
@Service
public class ChannelServiceImpl implements ChannelService {
	
	private Logger logger = LogManager.getLogger(ChannelServiceImpl.class);  
	
	@Autowired
	private ChannelRepository channelRepository;

	@Override
	public Page<Channel> getPage(Map<String, Object> parameters, Pageable pageable) {
		return channelRepository.findPageByParameters(parameters, pageable);
	}


}
