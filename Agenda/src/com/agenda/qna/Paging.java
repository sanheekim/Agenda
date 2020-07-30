package com.agenda.qna;

public class Paging {

	private int page = 1; // 현재 페이지 (get)
	private int totalcount; // 전체 게시물 수 (get)
	private int beginPage; // 페이지 출력 시작
	private int endPage; // 페이지 출력 끝
	private int displayRow = 10; // 한 페이지에 몇 개의 로우 (선택 set)
	private int displayPage = 10; // 한 페이지에 몇 개의 페이지 (선택 set)
	boolean prev; // prev 버튼이 보일건지 안보일건지
	boolean next; // next 버튼이 보일건지 안보일건지
	private int startNum;
	private int endNum;

	public int getStartNum() {
		System.out.println("startNum : " + (totalcount - (page - 1) * 10));
		return totalcount - (page-1) * 10;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		System.out.println("endNum : " + (((getStartNum() - 9) < 0) ? 1 : (getStartNum() - 9)));
		return ((getStartNum() - 9) < 0) ? 1 : (getStartNum() - 9);
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		// setTotalCount()를 호출해야 paging이 되기 때문에
		// paging()함수를 setTotalCount()를 호출했을 때 자동으로 호출되게 한다.
		this.totalcount = totalcount;
		paging();
	}

	public int getDisplayRow() {
		return displayRow;
	}

	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}

	public int getDisplayPage() {
		return displayPage;
	}

	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	private void paging() {
		// prev, next, beginPage, endPage를 계산해서 만든다.
		// 2+9 = 11, 11/10 = 1, 1*10 = 10
		// 10+9 = 19, 19/10 = 1, 1*10 = 10
		// 11+9 = 20, 20/10 = 2, 2*10 = 20
		// 20+9 = 29, 29/10 = 2, 2*10 = 20
		// 111+9 = 120 120/10 = 12, 12*10 = 120

		// (2+9)/10 * 10 (1번 방법)
		// endPage = ((page+(displayPage-1))/displayPage)*displayPage;

		// 1/10 0.1(올림) 1 (2번 방법)
		endPage = ((int) Math.ceil(page / (double) displayPage)) * displayPage;

		beginPage = endPage - (displayPage - 1);

		// 글 32개
		// 32/10 = 3.2 (올림) 4페이지
		// 2=? 2/10
		int totalPage = (int) Math.ceil(totalcount / (double) displayRow);

		if (totalPage < endPage) {
			endPage = totalPage;
			next = false;
		} else {
			next = true;
		}
		prev = (beginPage == 1) ? false : true;// page가 11이상에만 나온다.

	}
}
