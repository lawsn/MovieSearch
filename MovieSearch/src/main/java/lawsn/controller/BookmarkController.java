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

@Controller
public class BookmarkController {
	
	@Autowired
	private BookmarkService bookmarkService; 
	
	@RequestMapping("/bookmark/list")
	public String getBookmarkList(
			@RequestParam(value="r", required=false, defaultValue="3") Integer result,
			@RequestParam(value="p", required=false, defaultValue="0") Integer pageno,
			@RequestParam(value="o", required=false, defaultValue="DESC") Direction direction,
			@RequestParam(value="s", required=false, defaultValue="seq") String sortField,
			Model model) {
		
		model.addAttribute("o", direction);
		model.addAttribute("s", sortField);
		
		Page<Bookmark> list = this.bookmarkService.findAll(pageno, result, direction, sortField);
		model.addAttribute("list", list);
	
		return "bookmark/list";
	}
	
	@RequestMapping("/bookmark/view")
	public String getBookmark(@RequestParam("seq") int seq, Model model) {
		
		Bookmark bookmark = this.bookmarkService.findOne(seq);
		model.addAttribute(bookmark);
		
		return "bookmark/view";
	}
	
	@RequestMapping("/bookmark/save")
	@ResponseBody
	public String saveBookmark(Bookmark bookmark,
			Model model) {
		
		bookmark.setCreateTime(System.currentTimeMillis());
		Bookmark newBookmark = this.bookmarkService.crate(bookmark);
		model.addAttribute("save", newBookmark);
		
		return "SUCCESS";
	}
	
	@RequestMapping("/bookmark/delete")
	@ResponseBody
	public String deleteBookmark(@RequestParam("seq") int seq, Model model) {
		
		this.bookmarkService.delete(seq);
		
		return "SUCCESS";
	}
	
}
