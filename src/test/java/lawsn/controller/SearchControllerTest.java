package lawsn.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/servlet-context.xml", "classpath:spring/database-context.xml"})
public class SearchControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	
	@Test
	public void searchErrorCauseRequiredParamQ() throws Exception{
		this.mockMvc.perform(get("/search"))
		.andExpect(status().is4xxClientError())
		.andDo(print());
	}

	@Test
	public void search007() throws Exception {
		 this.mockMvc.perform(get("/search?q=007"))
		 .andDo(print())
		 .andExpect(status().isOk())
		 .andExpect(model().attributeExists("t"))
		 .andExpect(model().attribute("t", 3L))
		 .andExpect(model().attributeExists("items"));
	}
	
	@Test
	public void searchNothing() throws Exception{
		this.mockMvc.perform(get("/search?q=아부라카타부라"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("t"))
		.andExpect(model().attribute("t", 0L))
		.andExpect(model().attributeDoesNotExist("items"));
	}
}