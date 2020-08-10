package com.agenda.alrim;

public class AlrimDto {
	


	private int sche_no;
	private int pres_no;
	private String member_id;
	private String sche_content;
	private String sche_time;//일정 날짜 
	private String sche_regdate;
	
	
	public AlrimDto(String member_id) {
		this.member_id = member_id;
	}
	public AlrimDto(int sche_no) {
		this.sche_no = sche_no;
		
	}

	
	public AlrimDto() {
	
	}
	
	public AlrimDto(int sche_no, String member_id) {
		this.sche_no = sche_no;
		this.member_id = member_id;
	}

	public AlrimDto(int sche_no, int pres_no,String member_id, String sche_content, String sche_time, String sche_regdate) {
		
		this.sche_no = sche_no;
		this.pres_no = pres_no;
		this.member_id = member_id;
		this.sche_content = sche_content;
		this.sche_time = sche_time;
		this.sche_regdate = sche_regdate;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getSche_no() {
		return sche_no;
	}
	public void setSche_no(int sche_no) {
		this.sche_no = sche_no;
	}
	public int getPres_no() {
		return pres_no;
	}
	public void setPres_no(int pres_no) {
		this.pres_no = pres_no;
	}
	public String getSche_content() {
		return sche_content;
	}
	public void setSche_content(String sche_content) {
		this.sche_content = sche_content;
	}
	public String getSche_time() {
		return sche_time;
	}
	public void setSche_mdate(String sche_time) {
		this.sche_time = sche_time;
	}
	public String getSche_regdate() {
		return sche_regdate;
	}
	public void setSche_regdate(String sche_regdate) {
		this.sche_regdate = sche_regdate;
	}
	@Override
	public String toString() {
		return "<" + pres_no + ", " + member_id + ", " + sche_time + ">";
	}
	
	
	
	
	
}
