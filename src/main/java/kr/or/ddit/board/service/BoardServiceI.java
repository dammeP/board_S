package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.CBoardVO;
import kr.or.ddit.board.vo.FilesVO;
import kr.or.ddit.board.vo.PageVO;
import kr.or.ddit.board.vo.UsersVO;

public interface BoardServiceI {

	UsersVO getUsers(String userId);
		
	List<CBoardVO> selectAllCBoard();	// CBoard 전체 리스트 조회
	
	List<CBoardVO> selectUseCBoard();	// CBoard 사용가능한 리스트만 조회
	
//	Map<String , Object> selectPageBoard(PageVO pageVO);	// Board의 리스트를 페이지로 출력
	
	int insertCBoard(CBoardVO cboardVO);  // 게시판 생성
	
	
}
