package lawsn.repository;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@ContextConfiguration({"classpath:servlet-context.xml", "classpath:database-context.xml"})
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
    public void write(){

    	Bookmark bookmark = new Bookmark();
    	bookmark.setTitle("제목");
    	bookmark.setGrades("10.0");
    	
    	bookmarkRepository.save(bookmark);

        System.out.println(bookmarkRepository.findAll());
        String destTitle = null;
        
        Iterator<Bookmark> a = bookmarkRepository.findAll().iterator();
        if(a.hasNext()) {
        	Bookmark aa = a.next();
        	destTitle = aa.getTitle();
        	
        }
        
        assertEquals("제목", destTitle);

    }
    
//    @Test
    public void list() {
    	
    	Bookmark bookmark = new Bookmark();
    	bookmark.setTitle("제목");
    	bookmark.setGrades("10.0");
    	bookmarkRepository.save(bookmark);
    	
    	Pageable pageable = new PageRequest(0, 3, Sort.Direction.DESC, "seq");
    	Page<Bookmark> page = bookmarkRepository.findAll(pageable);
    	List<Bookmark> list = page.getContent();
    	for(Bookmark book : list) {
    		
    		System.out.println(book.getTitle());
    	}
    	
    	assertEquals(1, list.size());
    }
 

//    @Test
    public void writeLob() throws ClientProtocolException, URISyntaxException, IOException, ParseException{


    	String contents = searchService.getContents("1", "1", "1");
    	
    	System.out.println("contents :: " + contents);
    	
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(contents);
		JSONObject channelObject = (JSONObject) jsonObject.get("channel");

		System.out.println("channelObject :: " + channelObject);
		JSONArray itemArray = (JSONArray) channelObject.get("item");

		Iterator<JSONObject> itItems = itemArray.iterator();
		JSONObject itemObject = itItems.next();
		String jsonData = itemObject.toJSONString();
		

		System.out.println("myJson :: " + jsonData);
		
		
		
		Bookmark bookmark = new Bookmark();
//		bookmark.setJsonData(jsonData);
		
    	
    	bookmarkRepository.save(bookmark);

        System.out.println(bookmarkRepository.findAll());
        String destTitle = null;
        
        Iterator<Bookmark> a = bookmarkRepository.findAll().iterator();
        if(a.hasNext()) {
        	Bookmark aa = a.next();
//        	System.out.println(aa.getJsonData());
        	
        }

    }
}