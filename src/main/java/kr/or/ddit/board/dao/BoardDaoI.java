package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.CBoardVO;
import kr.or.ddit.board.vo.FilesVO;
import kr.or.ddit.board.vo.PageVO;
import kr.or.ddit.board.vo.UsersVO;

public interface BoardDaoI {
	UsersVO getUsers(String userId);
	
	List<CBoardVO> selectAllCBoard();	// CBoard 전체 리스트 조회
	
	List<CBoardVO> selectUseCBoard();	// CBoard 사용가능한 리스트만 조회
	
	int insertCBoard(CBoardVO cboardVO);  // 게시판 생성
	
	int updateCBoard(CBoardVO CBoardVO);	// 게시판 사용/미사용 수정

	List<BoardVO> selectPageBoard(PageVO pageVO);	// Board의 리스트를 페이지로 출력
	
	int selectTotalCntBoard(String cboardNo); // Board의 총 게시물 수
	
	BoardVO getBoard(String boardNo);	// boardNo에 해당하는 게시물 조회
	
	List<FilesVO> selectAllFiles(String boardNo);		// Files 전체 리스트 조회
	
	FilesVO selectImgfiles(String fileNo);	// Files 사진 조회
	
	int insertBoard(BoardVO boardVO);	// 게시글 생성
	
	int deleteBoard(String boardNo);	// 게시글 삭제
	
	int updateBoard(BoardVO boardVO);	// 게시글 수정
	
	int insertBoardPa(BoardVO boardVO); 	// 답글 생성
	
	int insertFiles(FilesVO filesVO);	// 첨부파일 추가
	
	int deleteFiles(String fileNo);	// 첨부파일 삭제
}
