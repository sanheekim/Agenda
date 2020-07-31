package com.agenda.login;

import org.apache.ibatis.session.SqlSession;

public class LoginDao extends LoginSqlMapConfig{
	
	private String namespace="com.agenda.login.LoginMapper.";
	
	public LoginDto login(LoginDto idpw) {
		
		SqlSession session = null;
		LoginDto logindto = null;
		
		
		try {
			session = getSqlSessionFactory().openSession(false);
			logindto = session.selectOne(namespace+"login", idpw);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			session.close();
		}
		return logindto;
	}
	
}
