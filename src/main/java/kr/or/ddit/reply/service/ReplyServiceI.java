package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.vo.ReplyVO;

public interface ReplyServiceI {
	int insertReply(ReplyVO replyVO); // 댓글 생성
	
	List<ReplyVO> selectAllReply(String boardNo);	// 댓글 조회
	
	int updateReply(String replyNo);	// 댓글 삭제
}
