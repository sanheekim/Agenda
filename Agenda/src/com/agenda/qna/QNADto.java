package com.agenda.qna;

import java.util.Date;

public class QNADto {

	private int qna_no;
	private String qna_title;
	private String qna_content;
	private Date qna_regdate;
	private int qna_hit;
	private String qna_delflag;
	private String member_id;
	private int qna_recnt;

	public QNADto() {

	}

	public QNADto(int qna_no, String qna_title, String qna_content, Date qna_regdate, int qna_hit, String qna_delflag,
			String member_id,int qna_recnt) {
		this.qna_no = qna_no;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_regdate = qna_regdate;
		this.qna_hit = qna_hit;
		this.qna_delflag = qna_delflag;
		this.member_id = member_id;
		this.qna_recnt = qna_recnt;
	}

	// 글쓰기
	public QNADto(String qna_title, String qna_content, String member_id) {
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.member_id = member_id;

	}

	public QNADto(int qna_no, String qna_title, String qna_content, String member_id) {
		this.qna_no = qna_no;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.member_id = member_id;
	}


	public int getQna_no() {
		return qna_no;
	}

	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}

	public String getQna_title() {
		return qna_title;
	}

	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public Date getQna_regdate() {
		return qna_regdate;
	}

	public void setQna_regdate(Date qna_regdate) {
		this.qna_regdate = qna_regdate;
	}

	public int getQna_hit() {
		return qna_hit;
	}

	public void setQna_hit(int qna_hit) {
		this.qna_hit = qna_hit;
	}

	public String getQna_delflag() {
		return qna_delflag;
	}

	public void setQna_delflag(String qna_delflag) {
		this.qna_delflag = qna_delflag;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getQna_recnt() {
	    return qna_recnt;
	}

	public void setQna_recnt(int qna_recnt) {
	    this.qna_recnt = qna_recnt;
	}
	
}