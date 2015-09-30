package cn.imethan.admin.service.cms;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.imethan.admin.document.cms.Channel;

/**
 * ChannelService.java
 *
 * @author Ethan Wong
 * @time 2014年3月2日下午4:46:01
 */
public interface ChannelService{
	
	/**
	 * 
	 * @param parameters
	 * @param pageable
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年10月1日上午4:54:22
	 */
	public Page<Channel> getPage(Map<String,Object> parameters,Pageable pageable);
	

	
}
