package kr.or.ddit.board.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.CBoardVO;
import kr.or.ddit.board.vo.FilesVO;
import kr.or.ddit.board.vo.PageVO;
import kr.or.ddit.board.vo.UsersVO;
import kr.or.ddit.reply.service.ReplyServiceI;
import kr.or.ddit.reply.vo.ReplyVO;

@RequestMapping("/board")
@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name="boardService")
	private BoardServiceI boardService;
	
	@Resource(name="replyService")
	private ReplyServiceI replyService;
	
	@RequestMapping("/json")
	public String json() {
		return "jsonView";
	}

	@RequestMapping("category")
	public String boardCategory(@RequestParam("cboardNo")String cboardNo, Model model,@RequestParam(value="page",defaultValue = "1", required=false ) int page,@RequestParam(value="pageSize" ,defaultValue = "7", required=false) int pageSize,PageVO pageVO,
								 UsersVO usersVO, HttpSession session) {
		usersVO = (UsersVO)session.getAttribute("S_usersVO");
		List<CBoardVO> cboardList =(List<CBoardVO>)session.getAttribute("S_cboardList");
		
		model.addAttribute("cboardNo",cboardNo);
		model.addAttribute("page",page);
		model.addAttribute("pageSize",pageSize);
		
		pageVO = new PageVO(page, pageSize,cboardNo);
		
		Map<String , Object> map = boardService.selectPageBoard(pageVO);
		model.addAttribute("boardList", map.get("boardList"));
		model.addAttribute("pages", map.get("pages"));
		
		return "tiles/board/list";
		
	}
	
	@RequestMapping("detail")
	public String boardDetail(@RequestParam("boardNo")String boardNo, BoardVO boardVO, Model model,HttpSession session) {
		
		boardVO = boardService.getBoard(boardNo);
		session.setAttribute("boardVO", boardVO);
		model.addAttribute("boardVO", boardVO);
		
		List<ReplyVO> replyList = replyService.selectAllReply(boardNo);
		List<FilesVO> filesList = boardService.selectAllFiles(boardNo);
		
		model.addAttribute("replyList",replyList);
		model.addAttribute("filesList",filesList);

		return "tiles/board/detail";
		
	}
	
	@RequestMapping("profileImg")
	public void profileImg(@RequestParam("fileNo")String fileNo, FilesVO filesVO, Model model, HttpServletResponse response) throws Exception {
		
		response.setContentType("image/png");
		
		filesVO = boardService.selectImgfiles(fileNo);
		
		FileInputStream fis = new FileInputStream(filesVO.getFileNm());
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buffer = new byte[512];
		
		while(fis.read(buffer) != -1) { 
			sos.write(buffer);
		}
		
		fis.close();
		sos.flush();
		sos.close();
	}
	
	@RequestMapping(path="regist", method = {RequestMethod.GET})
	public String boardRegist(HttpSession session, UsersVO usersVO, @RequestParam("cboardNo")String cboardNo,Model model) {
		
		usersVO = (UsersVO)session.getAttribute("S_usersVO");
		model.addAttribute("cboardNo", cboardNo);
		
		return "tiles/board/regist";
		
	}
	
	@RequestMapping(path="regist", method = {RequestMethod.POST})
	public String boardRegist(@RequestParam("boardTitle")String boardTitle, @RequestParam("boardContent")String boardContent,
								@RequestParam("cboardNo")String cboardNo,HttpServletRequest request, BoardVO boardVO,HttpSession session
								, UsersVO usersVO,Model model) {
		
		usersVO = (UsersVO)session.getAttribute("S_usersVO");
		
		boardVO = new BoardVO();
		boardVO.setBoardTitle(boardTitle);
		boardVO.setBoardContent(boardContent);
		boardVO.setUserId(usersVO.getUserId());
		boardVO.setCBoardNo(cboardNo);
		
		int insertCnt = boardService.insertBoard(boardVO);
//		for (int i = 1; i < 6; i++) {
//			Part profile = request.getPart("fileName" + i);
//			logger.debug("file : {}", profile.getHeader("Content-Disposition"));
//
//			String realFilename = FileUploadUtil.getFilename(profile.getHeader("Content-Disposition"));
//			String filePath = "";
//			String realFileNm = "";
//
//			if (profile.getSize() > 0) {
//				String extension = FileUploadUtil.getExtenstion(realFilename);
//				filePath = "D:\\profile\\" + realFilename ;
//				realFileNm = realFilename;
//				profile.write(filePath);
//
//				if (realFileNm != null) {
//
//					String board = boardVO.getBoardNo();
//
//					logger.debug("baordNo:{}", board);
//
//					filesVO = new FilesVO();
//					filesVO.setFileNm(filePath);
//					filesVO.setRealFileNm(realFileNm);
//					filesVO.setBoardNo(board);
//
//					insertFilesCnt = boardService.insertFiles(filesVO);
//					logger.debug("insertBoardCnt : {}", insertCnt);
//					logger.debug("filesVO:{}", filesVO);
//					logger.debug("insertFileCnt : {}", insertCnt);
//				}
//
//			}
//		}
		
//		|| insertFilesCnt == 1
		if(insertCnt == 1 ) {
			session.setAttribute("userId", usersVO.getUserId());
			session.setAttribute("boardVO", boardVO);
			model.addAttribute("cboardNo",cboardNo);
			return "redirect:/board/category"; 
		}
		else {
			return "tiles/board/regist";
		}
	}	
	
	@RequestMapping("/delete")
	public String boardDelete(@RequestParam("boardNo")String boardNo, @RequestParam("cboardNo")String cboardNo, Model model) {
		logger.debug("보드넘버 : {}",boardNo);
		logger.debug("카테고리 보드넘버 : {}",cboardNo);
		
		int deleteCnt = boardService.deleteBoard(boardNo);
		
		if(deleteCnt == 1) {
			model.addAttribute("cboardNo",cboardNo);
			return "redirect:/board/category";
		}else {
			return "tiles/board/detail";
		}
	}
		
		
	@RequestMapping(path="/update", method = {RequestMethod.GET})
	public String boardUpdate(HttpSession session, UsersVO usersVO,@RequestParam("boardNo")String boardNo,Model model, BoardVO boardVO){
		usersVO = (UsersVO)session.getAttribute("S_usersVO");
		boardVO = boardService.getBoard(boardNo);
		
		model.addAttribute("boardVO", boardVO);
		
		return "tiles/board/update";
	}
	
	@RequestMapping(path="/update", method = {RequestMethod.POST})
	public String boardUpdate(@RequestParam("boardNo")String boardNo,@RequestParam("boardTitle")String boardTitle,
							   @RequestParam("boardContent")String boardContent, BoardVO boardVO, HttpSession session,
							   				Model model) {
		
		boardVO = new BoardVO();
		boardVO.setBoardNo(boardNo);
		boardVO.setBoardTitle(boardTitle);
		boardVO.setBoardContent(boardContent);
		
		int updateCnt = boardService.updateBoard(boardVO);
		
		if(updateCnt == 1) {
			model.addAttribute("boardNo",boardNo);
			session.setAttribute("boardNo", boardNo);
			return "redirect:/board/detail";
		}else {
			return "tiles/board/update";
		}
		
	}
	
	@RequestMapping(path="/paRegist", method = {RequestMethod.GET})
	public String boardPaRegist(@RequestParam("boardNo")String boardNo, HttpSession session,BoardVO boardVO,UsersVO usersVO) {
		boardVO = (BoardVO)session.getAttribute("boardVO");
		usersVO = (UsersVO)session.getAttribute("S_usersVO");
		
		return "tiles/board/paRegist";
		
	}
	
	@RequestMapping(path="paRegist", method = {RequestMethod.POST})
	public String boardPaRegist(@RequestParam("boardTitle")String boardTitle, @RequestParam("boardContent")String boardContent,
								@RequestParam("cboardNo")String cboardNo,HttpServletRequest request, BoardVO boardVO,HttpSession session, 
								UsersVO usersVO,Model model) {
		
		usersVO = (UsersVO)session.getAttribute("S_usersVO");
		boardVO = (BoardVO)session.getAttribute("boardVO");
		String paBoardNo = boardVO.getBoardNo();
		
		boardVO = new BoardVO();
		boardVO.setBoardTitle(boardTitle);
		boardVO.setBoardContent(boardContent);
		boardVO.setUserId(usersVO.getUserId());
		boardVO.setCBoardNo(cboardNo);
		boardVO.setPaBoardNo(paBoardNo);
		
		int insertCnt = boardService.insertBoardPa(boardVO);
		
		if(insertCnt == 1 ) {
			session.setAttribute("userId", usersVO.getUserId());
			session.setAttribute("boardVO", boardVO);
			model.addAttribute("cboardNo",cboardNo);
			return "redirect:/board/category"; 
		}
		else {
			return "tiles/board/paRegist";
		}
	}
		
		
		
	@RequestMapping("fileDown")
	public void fileDown(@RequestParam("boardNo")String boardNo,HttpServletRequest request, HttpServletResponse response, FilesVO filesVO) throws Exception {
		
		response.setContentType("image/png");
		
		filesVO = boardService.selectImgfiles(boardNo);

		response.setHeader("Content-Disposition", "attachment; filename=\"\"" + filesVO.getRealFileNm()+ "\"");
		response.setContentType("application/octet-stream");
		
		FileInputStream fis = new FileInputStream(filesVO.getFileNm());
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buffer = new byte[512];
		
		while(fis.read(buffer) != -1) { 
			sos.write(buffer);
		}
		
		fis.close();
		sos.flush();
		sos.close();
		
	}


}
