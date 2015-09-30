package cn.imethan.admin.repository.cms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.imethan.admin.document.cms.Channel;

/**
 * ChannelRepositoryTest.java
 *
 * @author suncco
 * @time 2014年3月12日下午2:39:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/main/applicationContext.xml",
		"classpath*:/mongodb/applicationContext-mongodb.xml"
})
public class ChannelRepositoryTest {
	
	@Autowired
	private ChannelRepository channelRepository;
	
	@Test
	public void testSave(){
		Channel channel = new Channel();
		channel.setTitle("第二个栏目"+new Date());;
		channel.setDescribe("第二个栏目的描述");
		channel.setCreateTime(new Date());
		System.out.println(channelRepository.save(channel));
	}
	
	@Test
	public void testPage(){
		Pageable  pageable  = new PageRequest(0,10,Direction.DESC,"createTime");
		Page<Channel> page = channelRepository.findAll(pageable);
		System.out.println(page.getContent());
	}
	
	@Test
	public void testGetPageByParameters(){
		Pageable  pageable  = new PageRequest(0,10,Direction.DESC,"createTime");
		Map<String,Object> parameters = new HashMap<String,Object>();
		
		Page<Channel> page = channelRepository.findPageByParameters(parameters, pageable);
		
		System.out.println("page:"+page);
	}
	
	@Test
	public void testOther(){
//		channelRepository.baseTest();
		String name = channelRepository.getDbName();
		System.out.println("name:"+name);
	}

}


