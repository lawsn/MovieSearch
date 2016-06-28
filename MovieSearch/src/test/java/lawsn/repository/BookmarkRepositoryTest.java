package lawsn.repository;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lawsn.domain.Bookmark;
import lawsn.service.SearchService;
import lawsn.service.impl.DaumSearchServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration({"classpath:spring/servlet-context.xml", "classpath:spring/database-context.xml"})
public class BookmarkRepositoryTest {

	@Autowired
	SearchService searchService;
	
    @Resource
    BookmarkRepository bookmarkRepository;
    
	@Before
	public void setup(){
		searchService = new DaumSearchServiceImpl();
	}

    @Test
    public void aTestSave(){

    	Bookmark bookmark = new Bookmark();
    	bookmark.setSeq(1);
    	bookmark.setTitle("제목");
    	bookmark.setGrades("10.0");
    	
    	bookmarkRepository.save(bookmark);

        String destTitle = null;
        
        Iterator<Bookmark> a = bookmarkRepository.findAll().iterator();
        Bookmark mybook = a.next();
        destTitle = mybook.getTitle();
        
        assertEquals("제목", destTitle);

    }
    
    @Test
    public void bTestList() {
    	
    	Pageable pageable = new PageRequest(0, 3, Sort.Direction.DESC, "seq");
    	Page<Bookmark> page = bookmarkRepository.findAll(pageable);
    	List<Bookmark> list = page.getContent();
    	assertEquals(1, list.size());
    	
    }
 
    @Test
    public void cTestDelete() {

    	Bookmark bookmark = new Bookmark();
    	bookmark.setSeq(10);
    	
    	bookmarkRepository.delete(bookmark);
    	
    }
    
    @Test
    public void dTestList() {
    	
    	Pageable pageable = new PageRequest(0, 3, Sort.Direction.DESC, "seq");
    	Page<Bookmark> page = bookmarkRepository.findAll(pageable);
    	List<Bookmark> list = page.getContent();
    	assertEquals(0, list.size());
    	
    }
 
    @Test
    public void eTestDelete() {
    	
    	Bookmark bookmark = new Bookmark();
    	bookmark.setSeq(100);
    	
    	bookmarkRepository.delete(bookmark);
    	
    }
}