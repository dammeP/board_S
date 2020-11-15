package kr.or.ddit.login;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.CBoardVO;
import kr.or.ddit.board.vo.UsersVO;

@RequestMapping("/login")
@Controller
public class LoginController {
	
	@Resource(name="boardService")
	private BoardServiceI boardService;
	
	@RequestMapping("/json")
	public String json() {
		return "jsonView";
		
	}
	
	@RequestMapping("view")
	public String loginView() {
		return "login/view";
	}
	
	@RequestMapping("/process")
	public String main(@RequestParam("userid")String userId, @RequestParam("pass")String pass, 
						UsersVO usersVO, CBoardVO cboardVO, Model model,HttpSession session) {
		
		usersVO = boardService.getUsers(userId);
		List<CBoardVO> cboardList = boardService.selectAllCBoard();
		
		if(usersVO != null && usersVO.getPass().equals(pass)) {
			session.setAttribute("usersVO", usersVO);
			session.setAttribute("cboardList", cboardList);
			model.addAttribute("cboardList",cboardList);
			return "tiles/main/main";
			
		}
		else {
			return "login/view";
			
		}
		
		
	}
}
