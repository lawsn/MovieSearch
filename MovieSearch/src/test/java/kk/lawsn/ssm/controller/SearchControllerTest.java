package kk.lawsn.ssm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class SearchControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setup(){
		// 이곳에서 SearchController를 MockMvc 객체로 만듭니다.
		this.mockMvc = MockMvcBuilders.standaloneSetup(new SearchController()).build();
	}

	@Test
	public void call() throws Exception{
		this.mockMvc.perform(get("/search?q=미생")).andDo(print()).andExpect(status().isOk());
	}
	
//	@Test
//	public void call() throws Exception{
//		this.mockMvc.perform(get("/search?q=")).andDo(print()).andExpect(status().isOk());
//	}
}
