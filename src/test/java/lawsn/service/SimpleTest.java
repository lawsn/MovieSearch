package lawsn.service;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;

public class SimpleTest {
	
	@Test (expected = NullPointerException.class)
	public void parseJsonErrorDueToJsonTextSingleQuotation() {
		
		
		String requestgetparameter = "{'channel':{'result':10,'title':'Search SCC Open API','page':1,'totalCount':0,'q':'555','item':[],'debug':{},'pk':''}}";
		
		Object jsonobject = JSONValue.parse(requestgetparameter);
		JSONObject jsonobj = (JSONObject) jsonobject;
		
		jsonobj.get("channel");
		
	}

	@Test
	public void parseJson() {
		
		String requestgetparameter = "{\"channel\":{\"result\":10,\"title\":\"Search SCC Open API\",\"page\":1,\"totalCount\":0,\"q\":\"555\",\"item\":[],\"debug\":{},\"pk\":\"\"}}";

		Object jsonobject = JSONValue.parse(requestgetparameter);
		JSONObject jsonobj = (JSONObject) jsonobject;
		JSONObject channel = (JSONObject) jsonobj.get("channel");
		
		assertEquals(10L, channel.get("result"));

	}

}
