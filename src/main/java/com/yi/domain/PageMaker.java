package com.yi.domain;

public class PageMaker {
	private int totalCount; // 게시글 전체 갯수
	private int startPage; // 현재 보이는 페이지 시작 번호
	private int endPage; // 현재 보이는 페이지 끝 번호
	private boolean prev; // 이전 10개 존재여부
	private boolean next; // 이후 10개 존재 여부
	private int displayPageNum = 10; // 보이는 페이지 번호 갯수
	private Criteria cri;
	
	private void calcData() {
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		startPage = (endPage - displayPageNum)+1;
		int tempEndPage = (int) (Math.ceil(totalCount/(double)cri.getPerPageNum()));
		
		if(endPage>tempEndPage) {
			endPage = tempEndPage;
		}
		if(startPage==1) {
			prev = false;
		}else {
			prev = true;
		}
		
		if(endPage*cri.getPerPageNum()>=totalCount) {
			next = false;
		}else {
			next = true;
		}
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
	
	
	
}
