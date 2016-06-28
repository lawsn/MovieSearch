package lawsn.controller;

//이부분 추가하셔야 합니다.
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/servlet-context.xml", "classpath:spring/database-context.xml"})
public class StandAloneTest {

	private MockMvc mockMvc;
	
	// 테스트 메소드 실행전 셋업 메소드입니다.
	@Before
	public void setup(){
		// 이곳에서 SearchController를 MockMvc 객체로 만듭니다.
		this.mockMvc = MockMvcBuilders.standaloneSetup(new SearchController()).build();
	}

	@Test
	public void test() throws Exception{
		// SearchController의 "/" 매핑으로 정의합니다.
		this.mockMvc.perform(get("/search?q=바보"))
		// 처리 내용을 출력합니다.
		.andDo(print())
		// 상태값은 OK가 나와야 합니다.
//		.andExpect(status().isOk())
		// "serverTime"이라는 attribute가 존재해야 합니다.
//		.andExpect(model().attributeExists("totalCount"))
		;
	}
}