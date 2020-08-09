package com.agenda.login;

public class LoginDto {
	
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_email;
	private String member_addr;
	private String member_phone;
	private String member_enabled;
	private String member_role;
	private String member_token;
	private String member_salt;
	
	
	public LoginDto() {
	
		//default constructor
	}
	
	public LoginDto(String member_id, String member_pw, String member_name, String member_email,String member_addr,
			 String member_enabled, String member_role,String member_token, String member_salt,String member_phone) {
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_email = member_email;
		this.member_addr = member_addr;
		this.member_enabled=member_enabled;
		this.member_role= member_role;
		this.member_token = member_token;
		this.member_salt = member_salt;
		this.member_phone = member_phone;
			
	}
	
	
	//login : member_id, member_pw
	public LoginDto (String member_id, String member_pw) {
		this.member_id = member_id;
		this.member_pw = member_pw;
	}
	
	public LoginDto (String member_id, String member_email,String member_enabled) {	
		this.member_id = member_id;
		this.member_email = member_email;
		this.member_enabled = member_enabled;
	}
	
	/*
	 * public LoginDto (String member_id, String member_email, String member_name) {
	 * this.member_id = member_id; this.member_email = member_email;
	 * this.member_name = member_name; }
	 */
	
	//일반유저정보보기: id,pw,name,addr,phone,email
	public LoginDto(String member_id, String member_pw, String member_name, String member_email,String member_addr,
			String member_phone) {
		
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_email = member_email;
		this.member_addr = member_addr;
		this.member_phone = member_phone;			
	}
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
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
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_enabled() {
		return member_enabled;
	}
	public void setMember_enabled(String member_enabled) {
		this.member_enabled = member_enabled;
	}
	public String getMember_role() {
		return member_role;
	}
	public void setMember_role(String member_role) {
		this.member_role = member_role;
	}
	public String getMember_salt() {
		return member_salt;
	}
	public void setMember_salt(String member_salt) {
		this.member_salt = member_salt;
	}

	public String getMember_token() {
		return member_token;
	}

	public void setMember_token(String member_token) {
		this.member_token = member_token;
	}


}
