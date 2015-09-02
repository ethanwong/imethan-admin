package cn.imethan.security.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
 * MenuControllerTest.java
 *
 * @author Ethan Wong
 * @time 2015年9月2日上午10:40:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/main/applicationContext.xml", "classpath:/main/spring-mvc.xml" })
public class MenuControllerTest {

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
		// this.mockMvc.perform(post("/security/menu/json").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		// .andExpect(status().isOk())
		// .andExpect(content().contentType("application/json"))
		// .andExpect(jsonPath("$.name").value("Lee"));

		mockMvc.perform(post("/security/menu/json")).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
	}

}
