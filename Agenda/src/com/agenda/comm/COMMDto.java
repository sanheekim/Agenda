package com.agenda.comm;

import java.util.Date;

public class COMMDto {

//	 COMM_NO, COMM_CONTENT, COMM_REGDATE, COMM_STEP, QNA_NO, MEMBER_ID

	private int comm_no;
	private String comm_content;
	private Date comm_regdate;
	private int qna_no;
	private String member_id;
	
	public COMMDto() {
	}

	public COMMDto(int comm_no, String comm_content, Date comm_regdate, int qna_no, String member_id) {
		this.comm_no = comm_no;
		this.comm_content = comm_content;
		this.comm_regdate = comm_regdate;
		this.qna_no = qna_no;
		this.member_id = member_id;
	}

	public COMMDto(String comm_content, int qna_no) {
		this.comm_content = comm_content;
		this.qna_no = qna_no;
	}

	public int getComm_no() {
		return comm_no;
	}

	public void setComm_no(int comm_no) {
		this.comm_no = comm_no;
	}

	public String getComm_content() {
		return comm_content;
	}

	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}

	public Date getComm_regdate() {
		return comm_regdate;
	}

	public void setComm_regdate(Date comm_regdate) {
		this.comm_regdate = comm_regdate;
	}


	public int getQna_no() {
		return qna_no;
	}

	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	

}