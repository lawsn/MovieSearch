package lawsn.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

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
import org.springframework.web.util.NestedServletException;

import lawsn.repository.BookmarkRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/servlet-context.xml", "classpath:spring/database-context.xml"})
public class BookmarkControllerTest {

	@Autowired
	private WebApplicationContext context;
	
    @Resource
    BookmarkRepository bookmarkRepository;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void aTestSave() throws Exception {
		
		this.mockMvc.perform(get("/bookmark/save")
				.param("title", "기본")
				.param("titleLink", "http://daum.net")
				.param("grades", "9.9")
				.param("openInfo", "2016.07.01")
				)
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void bTestList() throws Exception {
		
		 this.mockMvc.perform(get("/bookmark/list"))
		 .andDo(print())
		 .andExpect(status().isOk())
		 .andExpect(model().attributeExists("list"));
		 
	}
	
	@Test (expected = NestedServletException.class)
	public void cTestDelete() throws Exception {
		
		this.mockMvc.perform(get("/bookmark/delete").param("seq", "2"))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void dTestDelete() throws Exception {
		
		this.mockMvc.perform(get("/bookmark/delete").param("seq", "1"))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
}