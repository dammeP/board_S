package kr.or.ddit.board.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.PageVO;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Resource(name="boardService")
	private BoardServiceI boardService;
	
	@RequestMapping("/json")
	public String json() {
		return "jsonView";
	}

//	@RequestMapping("category")
//	public String boardCategory(@RequestParam("cboardNo")String cboardNo, Model model,@RequestParam(value="page",defaultValue = "1", required=false ) int page,@RequestParam(value="pageSize" ,defaultValue = "7", required=false) int pageSize,PageVO pageVO) {
//		model.addAttribute("page",page);
//		model.addAttribute("pageSize",pageSize);
//		
//		pageVO = new PageVO(page, pageSize,cboardNo);
//		
//		Map<String , Object> map = boardService.selectPageBoard(pageVO);
//		request.setAttribute("boardList", map.get("boardList"));
//		request.setAttribute("pages", map.get("pages"));
		
		
//	}
}
