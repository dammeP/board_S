package kr.or.ddit.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.vo.CBoardVO;
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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
