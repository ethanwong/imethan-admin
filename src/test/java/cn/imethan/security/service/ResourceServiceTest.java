package cn.imethan.security.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import cn.imethan.security.entity.Resource;

/**
 * ResourceServiceTest.java
 *
 * @author Ethan Wong
 * @time 2015年9月1日下午2:49:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/main/applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)  
public class ResourceServiceTest {
	
	@Autowired
	private ResourceService resourceService;
	
	@Test
	public void testGetRootResource(){
		List<Resource> list = resourceService.getRootResource();
		System.out.println("list:"+list);
	}

}


