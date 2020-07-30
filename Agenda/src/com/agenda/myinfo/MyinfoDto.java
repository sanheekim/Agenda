package com.agenda.myinfo;

public class MyinfoDto {
	private String member_id;
	private String member_name;
	private String member_email;
	private String member_addr;
	
	
	
	
	public MyinfoDto(String member_id, String member_email, String member_addr) {
		this.member_id = member_id;
		this.member_email = member_email;
		this.member_addr = member_addr;
	}


	public MyinfoDto() {
		
	}


	public MyinfoDto(String member_id, String member_name, String member_email, String member_addr) {
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_email = member_email;
		this.member_addr = member_addr;
	}

	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getMember_name() {
		return member_name;
	}


	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}


	public String getMember_email() {
		return member_email;
	}


	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}


	public String getMember_addr() {
		return member_addr;
	}


	public void setMember_addr(String member_addr) {
		this.member_addr = member_addr;
	}
	
	
	
	
	
	
	
}
