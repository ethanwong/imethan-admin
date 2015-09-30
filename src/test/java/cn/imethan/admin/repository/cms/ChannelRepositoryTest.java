package cn.imethan.admin.repository.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.imethan.admin.document.cms.Channel;
import cn.imethan.common.mongodb.SearchFilter;
import cn.imethan.common.mongodb.SearchFilter.Operator;

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
		int i = 10;
		while(i>0){
			Channel channel = new Channel();
			channel.setTitle("标题"+i);;
			channel.setDescribe("描述"+i);
			channel.setCreateTime(new Date());
			System.out.println(channelRepository.save(channel));
			
			i--;
		}

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
		parameters.put(Operator.LIKE.toString()+"_title", "第一");//key:"Operator"_"field",value:values
		
		Page<Channel> page = channelRepository.findPageByParameters(parameters, pageable);
		
		System.out.println("page:"+page.getContent());
	}
	
	@Test
	public void testGetPageBySearchFilters(){
		Pageable  pageable  = new PageRequest(0,10,Direction.DESC,"createTime");
		
		// 创建searchFilter
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		SearchFilter filter1 = new SearchFilter("title", Operator.LIKE, "第二");
		SearchFilter filter2 = new SearchFilter("describe", Operator.LIKE, "第三");
		filters.add(filter1);
		filters.add(filter2);
		
		Page<Channel> page = channelRepository.findPageBySearchFilters(filters, pageable);
		
		System.out.println("page:"+page.getContent());
	}
	
	@Test
	public void testOther(){
		String name = channelRepository.getDbName();
		System.out.println("name:"+name);
		
		Pageable  pageable  = new PageRequest(0,10,Direction.DESC,"createTime");
		String title = "标题1";
		
		long count = channelRepository.countByTitle(title);
		System.out.println("count:"+count);
		
		List<Channel> list = channelRepository.findByTitle(title);
		System.out.println("list:"+list);
		
		List<Channel> list1 = channelRepository.findTop3ByTitle(title, pageable);
		System.out.println("list1:"+list1);
		
		List<Channel> list2 = channelRepository.readAllByTitleNotNull();
		System.out.println("list2:"+list2);
		
		MongoTemplate mongoTemplate = (MongoTemplate) channelRepository.getMongoOperations();
		List<Channel> list3 = mongoTemplate.findAll(channelRepository.getMongoEntityInformation().getJavaType());
		System.out.println("list3:"+list3);
		
		
		Query query = new Query(Criteria.where("title").is(title));
		Update update = new Update().set("describe", "随便更新点什么");
		Channel channel = mongoTemplate.findAndModify(query, update, Channel.class);
		System.out.println("channel:"+channel);
	}

}