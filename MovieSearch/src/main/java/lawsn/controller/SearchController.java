package lawsn.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lawsn.utils.DaumOpenApiHelper;
import lawsn.vo.DataVO;

/**
 * 영화정보를 검색하여 검색된 정보를 전달하는 클래스
 *
 * @version 1.0
 * @author 오범석
 */
@Controller
public class SearchController {
	
	/**
	 * 영화검색 메인화면
	 * 
	 * @return index.jsp
	 */
	@RequestMapping("/index")
	public String search() {
		return "index";
	}
	
	/**
	 * 검색어로 영화 정보 검색
	 * 
	 * @param q 검색어
	 * @param result 한 페이지에 보여질 결과 수
	 * @param pageno 검색 결과의 페이지 번호
	 * @param model 화면에서 정보를 엑세스를 위한 객체
	 * @return InternalResourceViewResolver (jsp)
	 */
	@RequestMapping("/search")
	public String search(@RequestParam("q") String q,
			@RequestParam(value="r", required=false, defaultValue="2") String result,
			@RequestParam(value="p", required=false, defaultValue="1") String pageno,
		    Model model) {
		
		// 화면출력을 위하여 정보설정
		model.addAttribute("q", q);
		model.addAttribute("r", result);
		model.addAttribute("p", pageno);
		
		String jsonData = DaumOpenApiHelper.getContents(q, result, pageno);
		
		// 검색 오류 처리
		if(jsonData == null) {
			return "search/list";
		}
	
		// 검색 총 개수 설정
		long totalCount = DaumOpenApiHelper.getTotalCount(jsonData);
		model.addAttribute("t", totalCount);
		
		// 검색된 정보가 있을 경우 영화정보 수신
		if(totalCount > 0L) {
			
			List<Map<String, List<DataVO>>> items = DaumOpenApiHelper.getItems(jsonData);
			model.addAttribute("items", items);
		}
		
	
		// 검색 결과 출력
		return "search/list";
	}
	
	@RequestMapping("/search2")
	@ResponseBody
	public String search2(@RequestParam("q") String q,
			@RequestParam(value="r", required=false, defaultValue="1") String result,
			@RequestParam(value="p", required=false, defaultValue="1") String pageno,
			Model model) {
		
		String jsonData = DaumOpenApiHelper.getContents(q, result, pageno);
		
		// 검색 오류 처리
		if(jsonData == null) {
			return "ERROR";
		}
		
		return jsonData;
			
	}
}
