package com.agenda.notice;

import java.sql.Date;

public class NoticeDto {

//AG_NOTICE
//NOTICE_NO,NOTICE_STEP,NOTICE_TITLE,NOTICE_CONTENT,NOTICE_REGDATE,NOTICE_HIT,NOTICE_DELFLAG,MEMBER_ID
	
	
	private int notice_no;
	private int notice_step;
	private String notice_title;
	private String notice_content;
	private Date notice_regdate;
	private int notice_hit;
	private char notice_delflag;
	private String member_id;
	
	public NoticeDto() {
		//default Constructor;
	}
	
	//공지사항 리스트 읽기
	public NoticeDto(int notice_no, String notice_title,String notice_content,
			Date notice_regdate, int notice_hit, char notice_delflag,String member_id) {
		this.notice_no=notice_no;
		this.notice_title=notice_title;
		this.notice_content=notice_content;
		this.notice_regdate=notice_regdate;
		this.notice_hit=notice_hit;
		this.notice_delflag=notice_delflag;
		this.member_id=member_id;
	}
	
	//공지사항 글 쓰기 = 관리자만 글 쓰기 가능함 = member_role = ADMIN..을 어디서 할까
	public NoticeDto(String notice_title, String notice_content,String member_id) {
		this.notice_title=notice_title;
		this.notice_content=notice_content;
		this.member_id=member_id;
	}
	
	//공지사항 글 수정 = 관리자만 수정가능함 = member_role = "ADMIN"
	
	public NoticeDto(int notice_no,String notice_title,String notice_content, String member_id) {
		this.notice_no= notice_no;
		this.notice_title=notice_title;
		this.notice_content = notice_content;
		this.member_id=member_id;
	}

	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public int getNotice_step() {
		return notice_step;
	}
	public void setNotice_step(int notice_step) {
		this.notice_step = notice_step;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public Date getNotice_regdate() {
		return notice_regdate;
	}
	public void setNotice_regdate(Date notice_regdate) {
		this.notice_regdate = notice_regdate;
	}
	public int getNotice_hit() {
		return notice_hit;
	}
	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}
	public char getNotice_delflag() {
		return notice_delflag;
	}
	public void setNotice_delflag(char notice_delflag) {
		this.notice_delflag = notice_delflag;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	
	
}