package kr.or.ddit.reply.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.reply.vo.ReplyVO;


public interface ReplyDaoI {
	
	int insertReply(ReplyVO replyVO); // 댓글 생성
	
	List<ReplyVO> selectAllReply(String boardNo);	// 댓글 조회
	
	int updateReply(String replyNo);	// 댓글 삭제
	
	
}
