package kr.or.ddit.board.vo;

import java.sql.Date;

public class BoardVO {
	private String boardNo;
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private String CBoardNo;
	private String userId;
	private String boardUseCheck;
	private String paBoardNo;
	
	public BoardVO() {
	}

	
	
	public BoardVO(String boardNo, String boardTitle, String boardContent, Date boardDate, String cBoardNo,
			String userId, String boardUseCheck, String paBoardNo) {
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.CBoardNo = cBoardNo;
		this.userId = userId;
		this.boardUseCheck = boardUseCheck;
		this.paBoardNo = paBoardNo;
	}



	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public String getCBoardNo() {
		return CBoardNo;
	}
	public void setCBoardNo(String cBoardNo) {
		CBoardNo = cBoardNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBoardUseCheck() {
		return boardUseCheck;
	}
	public void setBoardUseCheck(String boardUseCheck) {
		this.boardUseCheck = boardUseCheck;
	}
	public String getPaBoardNo() {
		return paBoardNo;
	}
	public void setPaBoardNo(String paBoardNo) {
		this.paBoardNo = paBoardNo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CBoardNo == null) ? 0 : CBoardNo.hashCode());
		result = prime * result + ((boardContent == null) ? 0 : boardContent.hashCode());
		result = prime * result + ((boardDate == null) ? 0 : boardDate.hashCode());
		result = prime * result + ((boardNo == null) ? 0 : boardNo.hashCode());
		result = prime * result + ((boardTitle == null) ? 0 : boardTitle.hashCode());
		result = prime * result + ((boardUseCheck == null) ? 0 : boardUseCheck.hashCode());
		result = prime * result + ((paBoardNo == null) ? 0 : paBoardNo.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardVO other = (BoardVO) obj;
		if (CBoardNo == null) {
			if (other.CBoardNo != null)
				return false;
		} else if (!CBoardNo.equals(other.CBoardNo))
			return false;
		if (boardContent == null) {
			if (other.boardContent != null)
				return false;
		} else if (!boardContent.equals(other.boardContent))
			return false;
		if (boardDate == null) {
			if (other.boardDate != null)
				return false;
		} else if (!boardDate.equals(other.boardDate))
			return false;
		if (boardNo == null) {
			if (other.boardNo != null)
				return false;
		} else if (!boardNo.equals(other.boardNo))
			return false;
		if (boardTitle == null) {
			if (other.boardTitle != null)
				return false;
		} else if (!boardTitle.equals(other.boardTitle))
			return false;
		if (boardUseCheck == null) {
			if (other.boardUseCheck != null)
				return false;
		} else if (!boardUseCheck.equals(other.boardUseCheck))
			return false;
		if (paBoardNo == null) {
			if (other.paBoardNo != null)
				return false;
		} else if (!paBoardNo.equals(other.paBoardNo))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardDate=" + boardDate + ", CBoardNo=" + CBoardNo + ", userId=" + userId + ", boardUseCheck="
				+ boardUseCheck + ", paBoardNo=" + paBoardNo + "]";
	}
	
	
	
	
	

}
