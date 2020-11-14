package kr.or.ddit.board.vo;

public class PageVO {
	private int page;
	private int pageSize;
	private String cboardNo;
	
	
	//s,o,r,h
	
	@Override
	public String toString() {
		return "PageVO [page=" + page + ", pageSize=" + pageSize + ", cboardNo=" + cboardNo + "]";
	}
	public PageVO(int page, int pageSize, String cboardNo) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.cboardNo = cboardNo;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getCboardNo() {
		return cboardNo;
	}
	public void setCboardNo(String cboardNo) {
		this.cboardNo = cboardNo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cboardNo == null) ? 0 : cboardNo.hashCode());
		result = prime * result + page;
		result = prime * result + pageSize;
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
		PageVO other = (PageVO) obj;
		if (cboardNo == null) {
			if (other.cboardNo != null)
				return false;
		} else if (!cboardNo.equals(other.cboardNo))
			return false;
		if (page != other.page)
			return false;
		if (pageSize != other.pageSize)
			return false;
		return true;
	}
	

	
}
