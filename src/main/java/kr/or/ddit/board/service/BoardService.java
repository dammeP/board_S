package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.BoardDaoI;
import kr.or.ddit.board.vo.CBoardVO;
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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
