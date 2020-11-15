package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.BoardDaoI;
import kr.or.ddit.board.vo.CBoardVO;
import kr.or.ddit.board.vo.PageVO;
import kr.or.ddit.board.vo.UsersVO;

@Transactional
@Service("boardService")
public class BoardService implements BoardServiceI{
	
	@Resource(name="boardDao")
	private BoardDaoI boardDao;
	
	public BoardService() {

	}

	@Override
	public UsersVO getUsers(String userId) {
		return boardDao.getUsers(userId);
	}

	@Override
	public List<CBoardVO> selectAllCBoard() {
		return boardDao.selectAllCBoard();
	}

	@Override
	public List<CBoardVO> selectUseCBoard() {
		return boardDao.selectUseCBoard();
	}

	@Override
	public int insertCBoard(CBoardVO cboardVO) {
		return boardDao.insertCBoard(cboardVO);
	}
	
//	@Override
//	public Map<String, Object> selectPageBoard(PageVO pageVO) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("boardList", boardDao.selectPageBoard(pageVO));
////		SqlSession sqlSession = MybatisUtil.getSqlSession();
//		
//		int totalCnt =boardDao.selectTotalCntBoard(pageVO.getCboardNo()); 
//		int pages = (int)Math.ceil((double)totalCnt/10);
//		map.put("pages", pages);
//		
//		return map;
//	}
	
	
	
	
}
