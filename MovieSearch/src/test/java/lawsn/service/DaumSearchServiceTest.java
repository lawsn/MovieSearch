package lawsn.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import lawsn.service.impl.DaumSearchServiceImpl;
import lawsn.vo.DataVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaumSearchServiceTest {

	private static SearchService searchService;

	@BeforeClass
	public static void setup() {
		searchService = new DaumSearchServiceImpl();
	}

	@Test
	public void aTestSearch007() throws ParseException {
		
		String jsonData = searchService.getContents("007", "2", "1");
		long totalCount = searchService.getTotalCount(jsonData);
		
		assertEquals(3L, totalCount);
		
		List<Map<String, List<DataVO>>> list = searchService.getItems(jsonData);
		assertEquals(2, list.size()); // 한페이지에 2건만 조회하도록 하였음.
		
	}
	
	@Test
	public void aTestSearch007Page2() throws ParseException {
		
		String jsonData = searchService.getContents("007", "2", "2");
		long totalCount = searchService.getTotalCount(jsonData);
		
		assertEquals(3L, totalCount);
		
		List<Map<String, List<DataVO>>> list = searchService.getItems(jsonData);
		assertEquals(1, list.size()); // 2페이지 조회 건수.
		
	}

}
