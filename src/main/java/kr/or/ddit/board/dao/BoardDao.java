package kr.or.ddit.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.CBoardVO;
import kr.or.ddit.board.vo.FilesVO;
import kr.or.ddit.board.vo.PageVO;
import kr.or.ddit.board.vo.UsersVO;

@Repository("boardDao")
public class BoardDao implements BoardDaoI{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	@Override
	public UsersVO getUsers(String userId) {
		return sqlSession.selectOne("users.getUsers", userId);
	}

	@Override
	public List<CBoardVO> selectAllCBoard() {
		return sqlSession.selectList("cboard.selectAllCBoard");
	}

	@Override
	public List<CBoardVO> selectUseCBoard() {
		return sqlSession.selectList("cboard.selectUseCBoard");
	}

	@Override
	public int insertCBoard(CBoardVO cboardVO) {
		return sqlSession.insert("cboard.insertCBoard",cboardVO);
	}

	@Override
	public int updateCBoard(CBoardVO CBoardVO) {
		return sqlSession.update("cboard.updateCBoard",CBoardVO);
	}

	@Override
	public List<BoardVO> selectPageBoard(PageVO pageVO) {
		List<BoardVO> boardPage = sqlSession.selectList("board.selectPageBoard" , pageVO);
		return boardPage;
	}

	@Override
	public int selectTotalCntBoard(String cboardNo) {
		return sqlSession.selectOne("board.selectTotalCntBoard",cboardNo);
	}

	@Override
	public BoardVO getBoard(String boardNo) {
		return sqlSession.selectOne("board.getBoard",boardNo);
	}

	@Override
	public List<FilesVO> selectAllFiles(String boardNo) {
		List<FilesVO> filesVO = sqlSession.selectList("files.selectAllFiles",boardNo);
		return filesVO;
	}

	@Override
	public FilesVO selectImgfiles(String fileNo) {
		return sqlSession.selectOne("files.getFilesImg",fileNo);
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		return sqlSession.insert("board.insertBoard",boardVO);
	}

	@Override
	public int deleteBoard(String boardNo) {
		return sqlSession.update("board.deleteBoard",boardNo);
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		return sqlSession.update("board.updateBoard",boardVO);
	}

	@Override
	public int insertBoardPa(BoardVO boardVO) {
		return sqlSession.insert("board.insertBoardPa",boardVO);
	}

	@Override
	public int insertFiles(FilesVO filesVO) {
		return sqlSession.insert("files.insertFiles",filesVO);
	}
	
	
}
