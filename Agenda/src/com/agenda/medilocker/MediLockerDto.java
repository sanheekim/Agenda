package com.agenda.medilocker;

import java.sql.Date;

public class MediLockerDto {
	private String member_id;
	private int pres_no;
	private String pres_mediname;
	private Date pres_date;
	private String pres_view;
	private String pres_name;
	private String pres_medinameadd;
	
	public MediLockerDto() {
		
	}

	public MediLockerDto(String member_id, int pres_no, String pres_mediname, Date pres_date, String pres_view, String pres_name) {
		this.member_id = member_id;
		this.pres_no = pres_no;
		this.pres_mediname = pres_mediname;
		this.pres_date = pres_date;
		this.pres_view = pres_view;
		this.pres_name = pres_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getPres_no() {
		return pres_no;
	}

	public void setPres_no(int pres_no) {
		this.pres_no = pres_no;
	}

	public String getPres_mediname() {
		return pres_mediname;
	}

	public void setPres_mediname(String pres_mediname) {
		this.pres_mediname = pres_mediname;
	}

	public Date getPres_date() {
		return pres_date;
	}

	public void setPres_date(Date pres_date) {
		this.pres_date = pres_date;
	}

	public String getPres_view() {
		return pres_view;
	}

	public void setPres_view(String pres_view) {
		this.pres_view = pres_view;
	}

	public String getPres_name() {
		return pres_name;
	}

	public void setPres_name(String pres_name) {
		this.pres_name = pres_name;
	}

	public String getPres_medinameadd() {
		return pres_medinameadd;
	}

	public void setPres_medinameadd(String pres_medinameadd) {
		this.pres_medinameadd = pres_medinameadd;
	}
	
	
	
	
}
