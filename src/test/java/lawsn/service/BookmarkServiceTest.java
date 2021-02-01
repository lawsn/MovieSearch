package lawsn.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lawsn.domain.Bookmark;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-context-test.xml"})
public class BookmarkServiceTest {

	@Autowired
	BookmarkService bookmarkService;
	
	
	@Before
	public void setup() {
		
    	Bookmark bookmark = new Bookmark();
    	bookmark.setTitle("제목");
    	bookmark.setGrades("10.0");
    	
    	bookmarkService.crate(bookmark);
	}
	
	@After
	public void tearDown() {
		System.out.println("END");
	}
	
	@Test
	public void aTestSave() throws ParseException {
    	
    	Bookmark select = bookmarkService.findOne(1);
    	assertEquals("제목", select.getTitle());
    	
	}
	
	@Test
	public void bTestList() throws ParseException {
		
		Integer pageno = 0;
		Integer result = 10;
		Direction direction = Sort.Direction.DESC;
		String sortField = "seq";
		
		Page<Bookmark> page = bookmarkService.findAll(pageno, result, direction, sortField);
		
		assertNotNull(page);
		
		assertNotNull(page.getContent().get(0));
		assertEquals("제목", page.getContent().get(0).getTitle());
		
		assertEquals(1, page.getContent().size());
	}

}
