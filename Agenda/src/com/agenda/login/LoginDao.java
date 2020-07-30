package com.agenda.login;

import org.apache.ibatis.session.SqlSession;

public class LoginDao extends LoginSqlMapConfig{
	
	private String namespace="com.agenda.login.LoginMapper.";
	
	public LoginDto login(String member_id, String member_pw) {
		
		SqlSession session = null;
		LoginDto logindto = new LoginDto(member_id,member_pw);

		try {
			session = getSqlSessionFactory().openSession(false);
			logindto = session.selectOne(namespace+"login",logindto);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return logindto;
	}
	
}
