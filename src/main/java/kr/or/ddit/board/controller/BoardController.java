package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.CBoardVO;
import kr.or.ddit.board.vo.FilesVO;
import kr.or.ddit.board.vo.PageVO;
import kr.or.ddit.board.vo.UsersVO;
import kr.or.ddit.fileUpload.FileUploadUtil;
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
	public String boardCategory(String cboardNo, Model model,@RequestParam(value="page",defaultValue = "1", required=false ) int page,@RequestParam(value="pageSize" ,defaultValue = "10", required=false) int pageSize,PageVO pageVO,
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
	public String boardDetail(String boardNo, BoardVO boardVO, Model model,HttpSession session) {
		
		boardVO = boardService.getBoard(boardNo);
		session.setAttribute("boardVO", boardVO);
		model.addAttribute("boardVO", boardVO);
		
		List<ReplyVO> replyList = replyService.selectAllReply(boardNo);
		List<FilesVO> filesList = boardService.selectAllFiles(boardNo);
		
		session.setAttribute("filesList", filesList);
		
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
	public String boardRegist(HttpSession session,Model model, String cboardNo) {
		
		UsersVO usersVO = (UsersVO)session.getAttribute("S_usersVO");
//		BoardVO boardVO= (BoardVO)session.getAttribute("boardVO");
		model.addAttribute("cboardNo",cboardNo);
		
		return "tiles/board/regist";
		
	}
	
	@RequestMapping(path = "regist", method = { RequestMethod.POST })
	public String boardRegist(String boardTitle, String boardContent, String cboardNo, HttpServletRequest request,
			HttpSession session, FilesVO filesVO, MultipartHttpServletRequest mhsr, Model model)
			throws IOException, ServletException {

		UsersVO usersVO = (UsersVO) session.getAttribute("S_usersVO");

		BoardVO boardVO = new BoardVO();
		boardVO.setBoardTitle(boardTitle);
		boardVO.setBoardContent(boardContent);
		boardVO.setUserId(usersVO.getUserId());
		boardVO.setCBoardNo(cboardNo);
		
		logger.debug("게시글 등록:글제목, 글 내용, cboardNo , 아이디:{},{},{},{}", boardTitle, boardContent, cboardNo,
				usersVO.getUserId());

		int insertCnt = boardService.insertBoard(boardVO);
		int insertFilesCnt = 0;

		List<MultipartFile> list = mhsr.getFiles("fileName");

		
		
		String filename = "";
		String filePath = "";
		String extension = "";

		if (list.size() > 0) {
			logger.debug("파일사이즈!!!:{}", list.size());
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getOriginalFilename() != null && !(list.get(i).getOriginalFilename().equals(""))) {
					filename = UUID.randomUUID().toString();
//					filename = list.get(i).getOriginalFilename();
					logger.debug("파일이름:{}",filename);
					
					extension = StringUtils.getFilenameExtension(list.get(i).getOriginalFilename());
					logger.debug("확장자 : {}", extension);
					filePath = "D:\\profile\\" + filename+"."+extension;
					File uplodFile = new File(filePath);
					
					String boardNo = boardVO.getBoardNo();

					filesVO = new FilesVO();
					filesVO.setFileNm(filePath);
					filesVO.setRealFileNm(list.get(i).getOriginalFilename());
					filesVO.setBoardNo(boardNo);
					
					insertFilesCnt = boardService.insertFiles(filesVO);
					logger.debug("filesVO:{}", filesVO);
					
					File uploadFile = new File(filePath);
					
					list.get(i).transferTo(uploadFile);
					
				}
			}
		}


//		|| insertFilesCnt == 1
		if (insertCnt == 1 || insertFilesCnt == 1) {
			session.setAttribute("userId", usersVO.getUserId());
			session.setAttribute("boardVO", boardVO);
			model.addAttribute("cboardNo", cboardNo);
			return "redirect:/board/category";
		} else {
			return "tiles/board/regist";
		}

	}
	
	@RequestMapping("/delete")
	public String boardDelete(String boardNo,String cboardNo, Model model) {
//		logger.debug("보드넘버 : {}",boardNo);
//		logger.debug("카테고리 보드넘버 : {}",cboardNo);
		
		int deleteCnt = boardService.deleteBoard(boardNo);
		
		if(deleteCnt == 1) {
			model.addAttribute("cboardNo",cboardNo);
			return "redirect:/board/category";
		}else {
			return "tiles/board/detail";
		}
	}
		
		
	@RequestMapping(path="/update", method = {RequestMethod.GET})
	public String boardUpdate(HttpSession session, UsersVO usersVO,String boardNo,Model model, BoardVO boardVO){
		usersVO = (UsersVO)session.getAttribute("S_usersVO");
//		List<FilesVO> filesList = (List<FilesVO>)session.getAttribute("filesList");
		List<FilesVO> filesList = boardService.selectAllFiles(boardNo);
		logger.debug("파일리스트!! : {}",filesList);
		
		boardVO = boardService.getBoard(boardNo);
		
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("filesList",filesList);
		
		return "tiles/board/update";
	}
	
	@RequestMapping(path="/update", method = {RequestMethod.POST})
	public String boardUpdate(String boardNo,String boardTitle,
							   String boardContent, BoardVO boardVO, HttpSession session,
							   				Model model,MultipartHttpServletRequest mhsr,HttpServletRequest request) throws IOException {
		
		int insertFilesCnt = 0;
		int deleteFilesCnt = 0;
		
		String[] fileNo = request.getParameterValues("fileNoUp");
		
		if(fileNo != null){
			for(String fileNo2 : fileNo) {
				if(!fileNo2.equals("")){
					logger.debug("fileNO222:{}",fileNo2);
					deleteFilesCnt = boardService.deleteFiles(fileNo2);
				}
			}
		}
		
		
		boardVO = new BoardVO();
		boardVO.setBoardNo(boardNo);
		boardVO.setBoardTitle(boardTitle);
		boardVO.setBoardContent(boardContent);
		
		int updateCnt = boardService.updateBoard(boardVO);
		
		List<MultipartFile> list = mhsr.getFiles("fileName");
		
		String filename = "";
		String filePath = "";
		String extension = "";

		if (list.size() > 0) {
			logger.debug("파일사이즈!!!:{}", list.size());
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getOriginalFilename() != null && !(list.get(i).getOriginalFilename().equals(""))) {
					filename = UUID.randomUUID().toString();
//					filename = list.get(i).getOriginalFilename();
					logger.debug("파일이름:{}",filename);
					
					extension = StringUtils.getFilenameExtension(list.get(i).getOriginalFilename());
					logger.debug("확장자 : {}", extension);
					filePath = "D:\\profile\\" + filename+"."+extension;
					File uplodFile = new File(filePath);
					
					String boNo = boardVO.getBoardNo();

					FilesVO filesVO = new FilesVO();
					filesVO.setFileNm(filePath);
					filesVO.setRealFileNm(list.get(i).getOriginalFilename());
					filesVO.setBoardNo(boNo);
					
					insertFilesCnt = boardService.insertFiles(filesVO);
					logger.debug("filesVO:{}", filesVO);
					
					File uploadFile = new File(filePath);
					
					list.get(i).transferTo(uploadFile);
					
				}
			}
		}
		
		if(updateCnt == 1 || deleteFilesCnt ==1 || insertFilesCnt == 1) {
			model.addAttribute("boardNo",boardNo);
			session.setAttribute("boardNo", boardNo);
			return "redirect:/board/detail";
		}else {
			return "tiles/board/update";
		}
		
	}
	
	@RequestMapping(path="/paRegist", method = {RequestMethod.GET})
	public String boardPaRegist(@RequestParam("boardNo")String boardNo, HttpSession session,Model model,String cboardNo) {
		BoardVO boardVO = (BoardVO)session.getAttribute("boardVO");
		UsersVO usersVO = (UsersVO)session.getAttribute("S_usersVO");
		model.addAttribute("cboardNo", cboardNo);
		
		
		return "tiles/board/paRegist";
		
	}
	
	@RequestMapping(path="/paRegist", method = {RequestMethod.POST})
	public String boardPaRegist(String boardTitle, String boardContent,
								String cboardNo,HttpServletRequest request, BoardVO boardVO,HttpSession session, 
								UsersVO usersVO,Model model, MultipartHttpServletRequest mhsr) throws Exception, IOException {
		
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
		int insertFilesCnt = 0;

		List<MultipartFile> list = mhsr.getFiles("fileName");

		
		
		String filename = "";
		String filePath = "";
		String extension = "";

		if (list.size() > 0) {
			logger.debug("파일사이즈!!!:{}", list.size());
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getOriginalFilename() != null && !(list.get(i).getOriginalFilename().equals(""))) {
					filename = UUID.randomUUID().toString();
//					filename = list.get(i).getOriginalFilename();
					logger.debug("파일이름:{}",filename);
					
					extension = StringUtils.getFilenameExtension(list.get(i).getOriginalFilename());
					logger.debug("확장자 : {}", extension);
					filePath = "D:\\profile\\" + filename+"."+extension;
					File uplodFile = new File(filePath);
					
					String boardNo = boardVO.getBoardNo();

					FilesVO filesVO = new FilesVO();
					filesVO.setFileNm(filePath);
					filesVO.setRealFileNm(list.get(i).getOriginalFilename());
					filesVO.setBoardNo(boardNo);
					
					insertFilesCnt = boardService.insertFiles(filesVO);
					logger.debug("filesVO:{}", filesVO);
					
					File uploadFile = new File(filePath);
					
					list.get(i).transferTo(uploadFile);
					
				}
			}
		}
		
		
		if (insertCnt == 1 || insertFilesCnt == 1) {
			session.setAttribute("userId", usersVO.getUserId());
			session.setAttribute("boardVO", boardVO);
			model.addAttribute("cboardNo", cboardNo);
			return "redirect:/board/category";
		}
		else {
			return "tiles/board/paRegist";
		}
	}
		
		
		
	@RequestMapping("/fileDown")
	public void fileDown(String fileNo,HttpServletRequest request, HttpServletResponse response, FilesVO filesVO,Map<String, Object> model) throws Exception {
		
		response.setContentType("image/PNG");
		
		filesVO = boardService.selectImgfiles(fileNo);
		

		response.setHeader("Content-Disposition", "attachment; filename=\"\"" + filesVO.getFileNm()+ "\"");
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
