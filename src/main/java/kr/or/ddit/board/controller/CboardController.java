package kr.or.ddit.board.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.CBoardVO;
import kr.or.ddit.board.vo.UsersVO;
import kr.or.ddit.member.model.MemberVO;

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
	public String cboardInsert(CBoardVO cboardV,Model model,HttpSession session) {
		List<CBoardVO> cboardList =(List<CBoardVO>)session.getAttribute("S_cboardList");
		model.addAttribute("cboardList",cboardList);
		
		return "tiles/cboard/insert";
	}
	
	@RequestMapping(path="/insert",method={RequestMethod.POST})
	public String cboardInsert(Model model,@RequestParam("cboardUseCheck")String cboardUseCheck, @RequestParam("cboardCategory")String cboardCategory, CBoardVO cboardVO,HttpServletRequest request,HttpSession session, UsersVO usersVO) {
		
		usersVO = (UsersVO)session.getAttribute("S_usersVO");
		List<CBoardVO> cboardList =(List<CBoardVO>)session.getAttribute("S_cboardList");
		
		cboardVO = new CBoardVO();
		cboardVO.setUserId(usersVO.getUserId());	
		cboardVO.setCboardCategory(cboardCategory);
		cboardVO.setCboardUseCheck(cboardUseCheck);
		
		int insertCnt = boardService.insertCBoard(cboardVO);
		
		if(insertCnt ==1) {
			model.addAttribute("userId", usersVO.getUserId());
			model.addAttribute("pass", usersVO.getPass());
			model.addAttribute("cboardList",cboardList);
			return "redirect:/login/process";
		}
		else {
			return "tiles/cboard/insert";
			
		}
		
	}
		
	@RequestMapping(path="/update",method={RequestMethod.POST})
	public String cboardUpdate(@RequestParam("cboardUseCheck")String cboardUseCheck, @RequestParam("cboardCategory")String cboardCategory,Model model,UsersVO usersVO, HttpSession session, CBoardVO cboardVO) {
		
		usersVO = (UsersVO)session.getAttribute("S_usersVO");
		List<CBoardVO> cboardList =(List<CBoardVO>)session.getAttribute("S_cboardList");
	
		cboardVO = new CBoardVO();
		cboardVO.setUserId(usersVO.getUserId());	
		cboardVO.setCboardCategory(cboardCategory);
		cboardVO.setCboardUseCheck(cboardUseCheck);
		
		int updateCnt = boardService.updateCBoard(cboardVO);
		
		if(updateCnt ==1) {
			model.addAttribute("userId", usersVO.getUserId());
			model.addAttribute("pass", usersVO.getPass());
			model.addAttribute("cboardList",cboardList);
			return "redirect:/login/process";
		}
		else {
			return "tiles/cboard/insert";
			
		}
	}
	
	
	
		
}
