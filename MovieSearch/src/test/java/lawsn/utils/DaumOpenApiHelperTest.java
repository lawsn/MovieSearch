package lawsn.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import lawsn.utils.DaumOpenApiHelper;

public class DaumOpenApiHelperTest {

	@Test
	public void sample0() throws ClientProtocolException, URISyntaxException, IOException, ParseException {

		String contents = DaumOpenApiHelper.getContents("007", "2", "1");
		
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
