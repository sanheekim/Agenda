package com.agenda.donation;

import java.util.Date;

public class dnDto {
	
	private int dona_no;
	private int dona_bill;
	private Date dona_date;
	private String member_id;
	
	public dnDto() {
		
	}
	
	public dnDto(int dona_no, int dona_bill, Date dona_date, String member_id) {
		this.dona_no = dona_no;
		this.dona_bill = dona_bill;
		this.dona_date = dona_date;
		this.member_id = member_id;
	}
	
	public int getDona_no() {
		return dona_no;
	}

	public void setDona_no(int dona_no) {
		this.dona_no = dona_no;
	}

	public int getDona_bill() {
		return dona_bill;
	}

	public void setDona_bill(int dona_bill) {
		this.dona_bill = dona_bill;
	}

	public Date getDona_date() {
		return dona_date;
	}

	public void setDona_date(Date dona_date) {
		this.dona_date = dona_date;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	

}
