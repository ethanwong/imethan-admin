package cn.imethan.admin.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * ChannelControllerTest.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年10月1日下午8:58:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/main/applicationContext-main.xml", "classpath:/main/spring-mvc.xml" })
public class ChannelControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		// this.mockMvc = MockMvcBuilders.standaloneSetup(new
		// MenuController()).build();
	}

	@Test
	public void jsonTest() throws Exception {
		mockMvc.perform(post("/security/user/json").param("pageNo", "1").param("pageSize", "10")).andDo(MockMvcResultHandlers.print()).andReturn();
	}

}
