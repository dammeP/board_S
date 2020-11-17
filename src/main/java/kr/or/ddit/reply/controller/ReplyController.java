package kr.or.ddit.reply.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.vo.UsersVO;
import kr.or.ddit.reply.service.ReplyServiceI;
import kr.or.ddit.reply.vo.ReplyVO;

@RequestMapping("/reply")
@Controller
public class ReplyController {
	
	@Resource(name="replyService")
	private ReplyServiceI replyService;
	
	@RequestMapping(path="/insert", method = {RequestMethod.POST})
	public String replyInsert(@RequestParam("replyC")String replyC, @RequestParam("boardNo")String boardNo,
							  UsersVO usersVO, HttpSession session, ReplyVO replyVO, Model model) {
		
		usersVO = (UsersVO)session.getAttribute("S_usersVO");
		String userId = usersVO.getUserId();
		
		replyVO = new ReplyVO();
		replyVO.setReplyContent(replyC);
		replyVO.setBoardNo(boardNo);
		replyVO.setUserId(userId);
		
		model.addAttribute("replyVO", replyVO);
		
		int insertCnt = replyService.insertReply(replyVO);
		
		if(insertCnt == 1) {
			model.addAttribute("boardNo",boardNo);
			return "redirect:/board/detail";
		} else {
			return "tiles/board/detail";
		}
	}
	
	@RequestMapping("/delete")
	public String replyDelete(@RequestParam("boardNo")String boardNo, @RequestParam("replyNo")String replyNo,Model model) {
		
		int deleteCnt = replyService.updateReply(replyNo);
		
		if(deleteCnt == 1 ) {
			model.addAttribute("boardNo",boardNo);
			return "redirect:/board/detail";
		}else {
			return "board/detail";
		}
		
	}
	

}
