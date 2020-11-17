package kr.or.ddit.reply.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.reply.dao.ReplyDaoI;
import kr.or.ddit.reply.vo.ReplyVO;

@Transactional
@Service("replyService")
public class ReplyService implements ReplyServiceI{

	@Resource(name="replyDao")
	private ReplyDaoI replyDao;
	
	public ReplyService() {
	}
	

	@Override
	public int insertReply(ReplyVO replyVO) {
		return replyDao.insertReply(replyVO);
	}


	@Override
	public List<ReplyVO> selectAllReply(String boardNo) {
		return replyDao.selectAllReply(boardNo);
	}


	@Override
	public int updateReply(String replyNo) {
		return replyDao.updateReply(replyNo);
	}

}
