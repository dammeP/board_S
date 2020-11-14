package kr.or.ddit.login;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.service.BoardServiceI;

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
}
