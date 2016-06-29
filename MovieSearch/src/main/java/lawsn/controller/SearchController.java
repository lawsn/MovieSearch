package lawsn.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lawsn.service.SearchService;
import lawsn.vo.DataVO;

/**
 * 영화정보를 검색하기 위해 요청 정보를 받고 결과를 전달하는 컨트롤러 클래스
 *
 * @version 1.0
 * @author 오범석
 */
@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	/**
	 * 영화검색 메인화면
	 * <pre>
	 * uri : /index
	 * </pre>
	 * 
	 * @return index.jsp
	 */
	@RequestMapping("/index")
	public String search() {
		return "index";
	}
	
	/**
	 * 검색어로 영화 정보 검색
	 * <pre>
	 * uri : /search
	 * </pre>
	 * 
	 * @param q 검색어
	 * @param result 한 페이지에 보여질 결과 수
	 * @param pageno 검색 결과의 페이지 번호
	 * @param model 화면에서 정보를 액세스를 위한 객체
	 * @return 검색결과조회 화면 (InternalResourceViewResolver - jsp)
	 */
	@RequestMapping("/search")
	public String search(@RequestParam("q") String q,
			@RequestParam(value="r", required=false, defaultValue="2") String result,
			@RequestParam(value="p", required=false, defaultValue="1") String pageno,
		    Model model) {
		
		// 화면출력을 위하여 정보설정 (검색조건)
		model.addAttribute("q", q); // 검색어
		model.addAttribute("r", result); // 페이지 당 조회 수
		model.addAttribute("p", pageno); // 페이지 번호

		// 검색 요청
		String jsonData = searchService.getContents(q, result, pageno);
		
		// 검색 오류 처리
		if(jsonData == null) {
			return "search/list";
		}
	
		// 검색 총 개수 설정
		long totalCount = searchService.getTotalCount(jsonData);
		model.addAttribute("t", totalCount);
		
		// 검색된 정보가 있을 경우 영화정보 수신
		if(totalCount > 0L) {
			
			List<Map<String, List<DataVO>>> items = searchService.getItems(jsonData);
			if(items == null || items.size() == 0) {
				// 영화정보가 없는 경우 검색건수를 0으로 처리
				model.addAttribute("t", 0L);
			}else {
				// 영화정보 목록을 화면에 전달하기 위해 지정
				model.addAttribute("items", items);
			}
		}
	
		// 검색 결과 출력
		return "search/list";
	}
}
