package kk.lawsn.ssm.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;

import kk.lawsn.ssm.vo.DataVO;

public class DaumOpenApiHelper {

	//private static String DAUM_OPENAPI_MOVIE_APIKEY = "5c48b3294791da714517306f99f1f07d"; //반갑다영화야
	private static String DAUM_OPENAPI_MOVIE_APIKEY = "88972fddecd6794e12530a7ece11ad2d"; //제한걸렸네

	/**
	 * 다음 OpenAPI 영화정보 검색요청
	 * 
	 * @param q 검색어
	 * @param result 한 페이지에 보여질 결과 수
	 * @param pageno 검색 결과의 페이지 번호
	 * @return 영화정보 결과 JsonData
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getContents(String q, String result, String pageno)
			throws URISyntaxException, ClientProtocolException, IOException {

		URI uri = new URI("https://apis.daum.net/contents/movie");
		uri = new URIBuilder(uri).addParameter("apikey", DAUM_OPENAPI_MOVIE_APIKEY)
				.addParameter("q", q)
				.addParameter("result", result)
				.addParameter("pageno", pageno)
				.addParameter("output", "json").build();

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse response = httpClient.execute(new HttpGet(uri));
		HttpEntity entity = response.getEntity();
		String jsonData = EntityUtils.toString(entity);
		return jsonData;

	}
	
	/**
	 * 에러검사
	 * 
	 * @param jsonData api출력
	 * @return 에러타입
	 * @throws ParseException 
	 */
	public static String apiError(String jsonData) throws ParseException {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
		
		String errorType = (String) jsonObject.get("errorType");
		return errorType;
	}
	
	
	/**
	 * 영화정보 총 개수
	 * 
	 * @param jsonData 영화정보
	 * @return 영화정보 총 개수
	 * @throws ParseException 
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static long getTotalCount(String jsonData) throws ParseException {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
		JSONObject channelObject = (JSONObject) jsonObject.get("channel");
		
		long totalCount = (long) channelObject.get("totalCount");
		return totalCount;
	}
	
	/**
	 * 다음 OpenAPI 영화목록
	 * 
	 * @param jsonData 다음 OpenAPI 영화검색 전문
	 * @return 영화목록
	 * @throws ParseException 
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static List<Map<String, List<DataVO>>> getItems(String jsonData) throws ParseException {
		
		List<Map<String, List<DataVO>>> items = new ArrayList<Map<String, List<DataVO>>>();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
		JSONObject channelObject = (JSONObject) jsonObject.get("channel");
		
		JSONArray itemArray = (JSONArray) channelObject.get("item");
		
		Iterator<JSONObject> itItems = itemArray.iterator();
		while(itItems.hasNext()) {
			JSONObject itemObject = itItems.next();
			
			Map<String, List<DataVO>> item = new HashMap<String, List<DataVO>>();
			item.put("thumbnail", DaumOpenApiHelper.getDatas(itemObject, "thumbnail")); //썸네일
			item.put("title", DaumOpenApiHelper.getDatas(itemObject, "title")); //영화명
			item.put("eng_title", DaumOpenApiHelper.getDatas(itemObject, "eng_title")); //영화 영문명
			item.put("org_title", DaumOpenApiHelper.getDatas(itemObject, "org_title")); //원제 영화명
			item.put("kword", DaumOpenApiHelper.getDatas(itemObject, "kword")); //영화명 키워드
			item.put("year", DaumOpenApiHelper.getDatas(itemObject, "year")); //제작년도
			item.put("trailer", DaumOpenApiHelper.getDatas(itemObject, "trailer")); //예고편
			item.put("res", DaumOpenApiHelper.getDatas(itemObject, "res")); //일반예매
			item.put("director", DaumOpenApiHelper.getDatas(itemObject, "director")); //감독
			item.put("actor", DaumOpenApiHelper.getDatas(itemObject, "actor")); //출연배우
			item.put("more_actor", DaumOpenApiHelper.getDatas(itemObject, "more_actor")); //출연배우 더보기
			item.put("nation", DaumOpenApiHelper.getDatas(itemObject, "nation")); //제작국가
			item.put("genre", DaumOpenApiHelper.getDatas(itemObject, "genre")); //장르
			item.put("open_info", DaumOpenApiHelper.getDatas(itemObject, "open_info")); //개봉정보
			item.put("homepage", DaumOpenApiHelper.getDatas(itemObject, "homepage")); //공식사이트
			item.put("video_info", DaumOpenApiHelper.getDatas(itemObject, "video_info")); //동영상정보
			item.put("video", DaumOpenApiHelper.getDatas(itemObject, "video")); //동영상1
			item.put("photo_info", DaumOpenApiHelper.getDatas(itemObject, "photo_info")); //포토 정보
			item.put("photo1", DaumOpenApiHelper.getData(itemObject, "photo1")); //포토 1
			item.put("photo2", DaumOpenApiHelper.getData(itemObject, "photo2")); //포토 2
			item.put("photo3", DaumOpenApiHelper.getData(itemObject, "photo3")); //포토 3
			item.put("photo4", DaumOpenApiHelper.getData(itemObject, "photo4")); //포토 4
			item.put("photo5", DaumOpenApiHelper.getData(itemObject, "photo5")); //포토 5
			item.put("grades", DaumOpenApiHelper.getDatas(itemObject, "grades")); //100자평 정보
			item.put("write_grades", DaumOpenApiHelper.getDatas(itemObject, "write_grades")); //평점쓰러가기
			item.put("expect_grades", DaumOpenApiHelper.getDatas(itemObject, "expect_grades")); //기대지수
			item.put("grade1", DaumOpenApiHelper.getDatas(itemObject, "grade1")); //100자평 1
			item.put("grade2", DaumOpenApiHelper.getDatas(itemObject, "grade2")); //100자평 2
			item.put("grade3", DaumOpenApiHelper.getDatas(itemObject, "grade3")); //100자평 3
			item.put("category", DaumOpenApiHelper.getDatas(itemObject, "category")); //카테고리
			item.put("event", DaumOpenApiHelper.getDatas(itemObject, "event")); //이벤트
			item.put("story", DaumOpenApiHelper.getDatas(itemObject, "story")); //줄거리
			item.put("wiki_url", DaumOpenApiHelper.getDatas(itemObject, "wiki_url")); //위키주소
			items.add(item);
		}
		
		return items;
	}
	
	/**
	 * 영화 세부정보
	 * 
	 * @param itemNode 영화정보
	 * @param fieldName 세부정보명
	 * @return 영화 세부정보
	 */
	private static List<DataVO> getDatas(JSONObject itemObject, String fieldName) {
		
		List<DataVO> datas = new LinkedList<DataVO>();

		JSONArray fieldNodes = (JSONArray) itemObject.get(fieldName);
		if(fieldNodes == null || fieldNodes.size() == 0) {
			return datas;
		}
		
		Iterator<JSONObject> itFields = fieldNodes.iterator();
		while(itFields.hasNext()) {
			
			JSONObject fieldObject = itFields.next();
			String content = (String) fieldObject.get("content");
			String link = (String) fieldObject.get("link");
			
			DataVO data = new DataVO();
			data.setContent(content);
			data.setLink(link);
			datas.add(data);
		}
		
		return datas;
	}
	
	/**
	 * 영화 세부정보
	 * 
	 * @param itemNode 영화정보
	 * @param fieldName 세부정보명
	 * @return 영화 세부정보
	 */
	private static List<DataVO> getData(JSONObject itemObject, String fieldName) {
		
		List<DataVO> datas = new LinkedList<DataVO>();
		
		JSONObject fieldObject = (JSONObject) itemObject.get(fieldName);
		if(fieldObject == null || fieldObject.size() == 0) {
			return datas;
		}

		String content = (String) fieldObject.get("content");
		String link = (String) fieldObject.get("link");
			
		DataVO data = new DataVO();
		data.setContent(content);
		data.setLink(link);
		datas.add(data);
		
		return datas;
	}
}
