package kr.or.ddit.reply.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.vo.CBoardVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.reply.vo.ReplyVO;

@Repository("replyDao")
public class ReplyDao implements ReplyDaoI{
	private static final Logger logger = LoggerFactory.getLogger(ReplyDao.class);
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;


	@Override
	public int insertReply(ReplyVO replyVO) {
		
		return sqlSession.insert("reply.insertReply",replyVO);
	}

	@Override
	public List<ReplyVO> selectAllReply(String boardNo) {
		
		List<ReplyVO> replyList = sqlSession.selectList("reply.selectAllReply",boardNo);
		
		return replyList;
		
	}

	@Override
	public int updateReply(String replyNo) {
		
		return sqlSession.insert("reply.updateReply",replyNo);
	}

}
