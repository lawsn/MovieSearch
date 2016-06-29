package lawsn.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
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
@ContextConfiguration(locations = {"classpath:spring/servlet-context.xml", "classpath:spring/database-context.xml"})
public class BookmarkServiceTest {

	@Autowired
	BookmarkService bookmarkService;
	
	
	@BeforeClass
	public static void setup() {
	}
	
	@Test
	public void aTestSave() throws ParseException {
		
    	Bookmark bookmark = new Bookmark();
    	bookmark.setSeq(5);
    	bookmark.setTitle("제목");
    	bookmark.setGrades("10.0");
    	
    	Bookmark create = bookmarkService.crate(bookmark);
    	
    	assertNotNull(create);
    	assertEquals("제목", create.getTitle());
    	
    	Bookmark select = bookmarkService.findOne(1);
    	assertEquals("제목", select.getTitle());
    	
	}
	
	@Test
	public void bTestList() throws ParseException {
		
		
    	Bookmark bookmark = new Bookmark();
    	bookmark.setSeq(2);
    	bookmark.setTitle("제목");
    	bookmark.setGrades("10.0");
    	
    	bookmarkService.crate(bookmark);
    	
		Integer result = 10;
		Integer pageno = 1;
		Direction direction = Sort.Direction.DESC;
		String sortField = "seq";
		
		Page<Bookmark> page = bookmarkService.findAll(result, pageno, direction, sortField);
		
		assertNotNull(page);
		
		assertNotNull(page.getContent().get(0));
		assertEquals("제목", page.getContent().get(0).getTitle());
		
		assertEquals(1, page.getContent().size());
	}

}
