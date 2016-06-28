package lawsn.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lawsn.service.impl.DaumSearchServiceImpl;

public class DaumSearchServiceTest {

	@Autowired
	SearchService searchService;

	@Before
	public void setup(){
		// 이곳에서 SearchController를 MockMvc 객체로 만듭니다.
		searchService = new DaumSearchServiceImpl();
	}

	@Test
	public void sample0() throws ClientProtocolException, URISyntaxException, IOException, ParseException {

		if(searchService == null) {
			System.err.println("searchServiceError");
			return;
		}
		
		String contents = searchService.getContents("007", "2", "1");
		
		System.out.println("contents : " + contents);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(contents);
		JSONObject channelObject = (JSONObject) jsonObject.get("channel");

		JSONArray itemArray = (JSONArray) channelObject.get("item");

		Iterator<JSONObject> itItems = itemArray.iterator();
		while (itItems.hasNext()) {
			JSONObject itemObject = itItems.next();

			System.out.println(itemObject.toJSONString());

		}

	}

}
