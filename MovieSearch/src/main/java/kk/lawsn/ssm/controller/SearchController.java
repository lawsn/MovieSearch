package kk.lawsn.ssm.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kk.lawsn.ssm.utils.DaumOpenApiHelper;
import kk.lawsn.ssm.vo.DataVO;

@Controller
public class SearchController {
	
	@RequestMapping("/index")
	public String search() {
		return "index";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("q") String q,
			@RequestParam(value="r", required=false, defaultValue="2") String result,
			@RequestParam(value="p", required=false, defaultValue="1") String pageno,
		    Model model) {
		
		model.addAttribute("q", q);
		model.addAttribute("r", result);
		model.addAttribute("p", pageno);
		
		try {
			String jsonData = DaumOpenApiHelper.getContents(q, result, pageno);
		
			long totalCount = DaumOpenApiHelper.getTotalCount(jsonData);
			model.addAttribute("t", totalCount);
			
			if(totalCount > 0L) {
				
				List<Map<String, List<DataVO>>> items = DaumOpenApiHelper.getItems(jsonData);
				model.addAttribute("items", items);
			}
			
		} catch (URISyntaxException | IOException | ParseException e) {
			
			return "search/list";
		}
	
		return "search/list";
	}
	
	@RequestMapping("/search2")
	@ResponseBody
	public String search2(@RequestParam("q") String q,
			@RequestParam(value="r", required=false, defaultValue="1") String result,
			@RequestParam(value="p", required=false, defaultValue="1") String pageno,
			Model model) {
		
		try {
			String jsonData = DaumOpenApiHelper.getContents(q, result, pageno);
			return jsonData;
		} catch (URISyntaxException | IOException e) {
			
			return "ERROR";
		}
	}
}
