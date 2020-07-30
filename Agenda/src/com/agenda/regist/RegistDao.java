package com.agenda.regist;

import com.agenda.regist.RegistSqlMapConfig;

import org.apache.ibatis.session.SqlSession;

public class RegistDao extends RegistSqlMapConfig {

	private String namespace="com.agenda.regist.RegistMapper.";
	
	public RegistDto idCheck(String member_id) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne(namespace + "selectOne", member_id);
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return null;
	}

	public int insertUser(RegistDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace + "insert", dto);
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}

}










