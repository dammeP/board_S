package kr.or.ddit.board.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.CBoardVO;

@RequestMapping("cboard")
@Controller
public class CboardController {

	@Resource(name="boardService")
	private BoardServiceI boardService;
	
	@RequestMapping("/json")
	public String json() {
		return "jsonView";
	}
	
	@RequestMapping(path="/insert",method={RequestMethod.GET})
	public String cboardInsert(CBoardVO cboardV,Model model) {
		List<CBoardVO> cboardList = boardService.selectAllCBoard();
		model.addAttribute("cboardList",cboardList);
		
		return "tiles/cboard/insert";
	}
	
	@RequestMapping(path="/insert",method={RequestMethod.POST})
	public String cboardInsert(Model model,@RequestParam("cboardUseCheck")String cboardUseCheck, @RequestParam("cboardCategory")String cboardCategory, CBoardVO cboardVO,String userId) {
		
		List<CBoardVO> cboardList = boardService.selectAllCBoard();
		
		cboardVO = new CBoardVO();
		cboardVO.setUserId(userId);	// 세션 연결해야되나?
		cboardVO.setCboardCategory(cboardCategory);
		cboardVO.setCboardUseCheck(cboardUseCheck);
		
		int insertCnt = boardService.insertCBoard(cboardVO);
		
		if(insertCnt ==1) {
			model.addAttribute("cboardList",cboardList);
			return "redirect:/login/process";
		}
		else {
			return "tiles/cboard/insert";
			
		}
	}
	
	
	
		
}
