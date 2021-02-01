package lawsn.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;

import lawsn.vo.DataVO;

/**
 * 영화를 검색하는 서비스 인터페이스
 *
 * @version 1.0
 * @author 오범석
 */
public interface SearchService {

	/**
	 * 영화정보 검색요청 후 json 정보 수신
	 * 
	 * @param q 검색어
	 * @param result 한 페이지에 보여질 결과 수
	 * @param pageno 검색 결과의 페이지 번호
	 * @return 영화정보 결과 JsonData
	 */
	public String getContents(String q, String result, String pageno);

	
	/**
	 * 검색된 영화정보 총 개수 반환
	 * 
	 * @param jsonData 영화검색 출력정보
	 * @return 영화정보 총 개수
	 * @throws ParseException
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public long getTotalCount(String jsonData);
	
	/**
	 * 영화목록
	 * 
	 * @param jsonData 영화검색 출력정보
	 * @return 영화목록
	 * @throws ParseException 
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public List<Map<String, List<DataVO>>> getItems(String jsonData);

}
