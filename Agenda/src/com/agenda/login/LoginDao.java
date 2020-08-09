package com.agenda.login;

import org.apache.ibatis.session.SqlSession;

public class LoginDao extends LoginSqlMapConfig {

	private String namespace = "com.agenda.login.LoginMapper.";

	public LoginDto login(LoginDto idpw) {

		SqlSession session = null;
		LoginDto logindto = null;

		try {
			session = getSqlSessionFactory().openSession(false);
			logindto = session.selectOne(namespace + "login", idpw);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			session.close();
		}
		return logindto;

	}

	public LoginDto findid(LoginDto nameemail) {
		SqlSession session = null;
		LoginDto logindto = null;
		try {
			session = getSqlSessionFactory().openSession(false);
			logindto = session.selectOne(namespace + "findId", nameemail);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return logindto;
	}
	
	public LoginDto idsalt(String member_id) {
		SqlSession session = null;
		LoginDto logindto = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			logindto = session.selectOne(namespace+"saltId",member_id);
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return logindto;
	}
	
	public LoginDto kakaologin(LoginDto idemail) {
		SqlSession session = null;
		LoginDto logindto = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			logindto = session.selectOne(namespace+"kakaologin",idemail);
		} catch (Exception e) {
			System.out.println("kakao 로그인 dao ");
			e.printStackTrace();
		}
		return logindto;
	}
	

}
