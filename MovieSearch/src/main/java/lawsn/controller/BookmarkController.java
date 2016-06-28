package lawsn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lawsn.domain.Bookmark;
import lawsn.service.BookmarkService;

/**
 * 북마크 정보 관리를 위해 요청 정보를 받고 결과를 전달하는 컨트롤러 클래스
 *
 * @version 1.0
 * @author 오범석
 */
@Controller
public class BookmarkController {
	
	/**
	 * 북마크 관리 서비스 선언
	 */
	@Autowired
	private BookmarkService bookmarkService; 
	
	/**
	 * 북마크 목록조회
	 * <pre>
	 * uri : /bookmark/list
	 * </pre>
	 * 
	 * @param result 한 페이지에 보여질 결과 수
	 * @param pageno 검색 결과의 페이지 번호
	 * @param direction 정렬순서
	 * @param sortField 정렬필드
	 * @param model 화면에서 정보를 액세스를 위한 객체
	 * @return 북마크목록 화면 (InternalResourceViewResolver - jsp)
	 */
	@RequestMapping("/bookmark/list")
	public String getBookmarkList(
			@RequestParam(value="r", required=false, defaultValue="3") Integer result,
			@RequestParam(value="p", required=false, defaultValue="0") Integer pageno,
			@RequestParam(value="o", required=false, defaultValue="DESC") Direction direction,
			@RequestParam(value="s", required=false, defaultValue="seq") String sortField,
			Model model) {
		
		// 화면출력을 위하여 정보설정 (검색조건)
		model.addAttribute("r", result); // 페이지 당 조회 수
		model.addAttribute("p", pageno); // 페이지 번호
		model.addAttribute("o", direction); // 정렬순서
		model.addAttribute("s", sortField); // 정렬필드
		
		// 북마크조회 요청 (with 페이징)
		Page<Bookmark> list = this.bookmarkService.findAll(pageno, result, direction, sortField);
		model.addAttribute("list", list);
	
		return "bookmark/list";
	}
	
	/**
	 * 북마크 저장 요청
	 * <pre>
	 * uri : /bookmark/save
	 * </pre>
	 * 
	 * @param bookmark 저장할 북마크 정보
	 * @return 처리결과 jsonData (result)
	 */
	@RequestMapping("/bookmark/save")
	@ResponseBody
	public String saveBookmark(Bookmark bookmark) {
		
		// 북마크 저장시간을 현재시간으로 지정
		bookmark.setCreateTime(System.currentTimeMillis());
		// 북마크 저장 요청
		this.bookmarkService.crate(bookmark);
		
		// 성공응답
		return "{\"result\":\"SUCCESS\"}";
	}
	
	/**
	 * 북마크번호로 북마크 삭제 요청
	 * <pre>
	 * uri : /bookmark/delete
	 * </pre>
	 * 
	 * @param seq 북마크번호
	 * @return 처리결과 jsonData (result)
	 */
	@RequestMapping("/bookmark/delete")
	@ResponseBody
	public String deleteBookmark(@RequestParam("seq") int seq, Model model) {
		
		// 북마크 삭제 요청
		this.bookmarkService.delete(seq);
		
		// 성공응답
		return "{\"result\":\"SUCCESS\"}";
	}
	
}
