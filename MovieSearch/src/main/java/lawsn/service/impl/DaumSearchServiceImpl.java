package lawsn.service.impl;

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
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import lawsn.service.SearchService;
import lawsn.vo.DataVO;

/**
 * 다음 Open API 영화컨텐츠를 사용하는 검색서비스 구현 클래스
 *
 * @version 1.0
 * @author 오범석
 */
@Service("searchService")
public class DaumSearchServiceImpl implements SearchService {

	/**
	 * 다음 Open API 영화컨텐츠 등록 앱 API KEY
	 * APP : 반갑다영화야
	 */
//	private static String DAUM_OPENAPI_MOVIE_APIKEY = "5c48b3294791da714517306f99f1f07d";

	/**
	 * 다음 Open API 영화컨텐츠 등록 앱 API KEY
	 * APP : 제한걸렸네
	 */
	private static String DAUM_OPENAPI_MOVIE_APIKEY = "88972fddecd6794e12530a7ece11ad2d";

	@Override
	public String getContents(String q, String result, String pageno) {
		try {
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
			
			// 오류 일 경우 null
			if(this.isApiError(jsonData)) {
				return null;
			}

			// 정상데이터 전달
			return jsonData;
			
		} catch (URISyntaxException | IOException e) {
			// 오류 일 경우 null
			return null;
		}
	}
	
	/**
	 * 에러검사
	 * <p>
	 * 다음 OPEN API 표준 코드 오류 (errorType) 설명
	 * 200	정상	정상 처리된 경우
	 * 401	AccessDeniedError	jsonp를 지원하지 않는 API를 jsonp로 호출한 경우
	 * 401	Unauthorized	appkey를 사용가능한 whitelist가 아닌곳에서 호출한 경우
	 * 403	NotAuthorizedError	등록되지 않은 appkey사용
	 * 404	ResourceNotFound	API path가 잘못되어 존재하지 않는 API를 호출한 경우
	 * 409	MissingParameter	필수 파라미터를 입력하지 않은 경우
	 * 429	RequestThrottled	사용 가능한 Quata이상으로 API호출시
	 * 500	기타	API 서비스 내부 시스템 오류
	 * 504	RequestTimeout	API 서비스 연결 실패. 서비스 시스템 과부하 또는 장애로 인한 서비스 연결 실패
	 * </p>
	 * 
	 * @param jsonData 다음 Open API 영화검색 출력정보
	 * @return 에러여부 (true:에러)
	 * @throws ParseException 
	 */
	private boolean isApiError(String jsonData) {
		
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
			
			// errorType이 있으면 오류로 판단
			String errorType = (String) jsonObject.get("errorType");
			if(errorType == null) {
				return false;
			}

			return true;
			
		} catch (ParseException e) {
			// 파싱에러도 영화검색 오류로 처리
			return true;
		}
		
	}

	@Override
	public long getTotalCount(String jsonData) {
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
			JSONObject channelObject = (JSONObject) jsonObject.get("channel");
			
			// 총 개수 필드
			long totalCount = (long) channelObject.get("totalCount");
			return totalCount;
			
		} catch (ParseException e) {
			// 파싱에러시 검색건수 0건으로 처리
			return 0L;
		}
	}

	@Override
	public List<Map<String, List<DataVO>>> getItems(String jsonData) {
		try {
			List<Map<String, List<DataVO>>> items = new ArrayList<Map<String, List<DataVO>>>();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
			
			// 다음 Open API 영화검색 ROOT - channel
			JSONObject channelObject = (JSONObject) jsonObject.get("channel");
	
			// 검색 영화정보 item
			JSONArray itemArray = (JSONArray) channelObject.get("item");
			Iterator<?> itItems = itemArray.iterator();
			
			// 영화 정보를 담을 변수 선언
			Map<String, List<DataVO>> item = null;
			
			while(itItems.hasNext()) {
				Object obj = itItems.next();
				if(obj instanceof JSONObject == false) {
					continue;
				}
				
				JSONObject itemObject = (JSONObject) obj;
				Iterator<?> keys = itemObject.keySet().iterator();
				
				item = new HashMap<String, List<DataVO>>();
				while(keys.hasNext()) {
					String key = (String) keys.next();
					
					// 다음 Open API 세부데이터 정보 추출
					item.put(key, this.getDataList(itemObject, key));
				}
				
				if(item != null) {
					items.add(item);
				}
				
			}
			
			return items;
			
		} catch (ParseException e) {
			// 파싱에러시 없는거로 처리
			return null;
		}
	}

	/**
	 * 영화 세부정보
	 * 
	 * @param itemNode 영화정보
	 * @param fieldName 세부정보명
	 * @return 영화 세부정보 목록
	 */
	private List<DataVO> getDataList(JSONObject itemNode, String fieldName) {
		
		List<DataVO> datas = new LinkedList<DataVO>();
		
		Object node = (Object) itemNode.get(fieldName);
		if(node == null) {
			return datas;
		}
		
		if(node instanceof JSONArray) {
			// 다건정보
			Iterator<?> itFields = ((JSONArray) node).iterator();
			while(itFields.hasNext()) {
				DataVO data = parseObjectToData(itFields.next());
				if(data != null) {
					datas.add(data);
				}
			}		
			
		}else if(node instanceof JSONObject) {
			// 단건정보
			DataVO data = parseObjectToData(node);
			if(data != null) {
				datas.add(data);
			}
			
		}else if(node instanceof String) {
			// 문자열정보(?)
			DataVO data = new DataVO();
			data.setContent((String) node);
			datas.add(data);
			
		}
		
		return datas;
	}
	
	/**
	 * 영화 세부정보 저장 후 전달
	 * 
	 * @param fieldObject 영화정보 데이터
	 * @return DataVO 영화정보 (내용, 링크)
	 */
	private DataVO parseObjectToData(Object fieldObject) {
		
		DataVO data = null;
		
		if(fieldObject instanceof JSONObject) {
			
			// 세부정보 내용
			String content = (String) ((JSONObject) fieldObject).get("content");
			// 세부정보 링크
			String link = (String) ((JSONObject) fieldObject).get("link");
			
			data = new DataVO();
			data.setContent(content);
			data.setLink(link);
		}
		
		return data;
		
	}
	
}
