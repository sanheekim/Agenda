package com.agenda.googlelogion;

import org.apache.ibatis.session.SqlSession;

public class googleLoginDao extends googleLoginSqlMapConfig {

	private String namespace = "com.agenda.login.LoginMapper.";

	public googleLoginDto login(googleLoginDto idpw) {

		SqlSession session = null;
		googleLoginDto logindto = null;

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

	public googleLoginDto findid(googleLoginDto nameemail) {
		SqlSession session = null;
		googleLoginDto logindto = null;
		try {
			session = getSqlSessionFactory().openSession(false);
			logindto = session.selectOne(namespace + "findId", nameemail);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return logindto;
	}
}
