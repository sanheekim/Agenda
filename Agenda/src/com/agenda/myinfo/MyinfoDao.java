package com.agenda.myinfo;

import org.apache.ibatis.session.SqlSession;

public class MyinfoDao extends MyinfoSqlMapConfig {
	private String namespace = "myInfomapper.";
	public MyinfoDto selectOne(String member_id) {
		SqlSession session = null;
		MyinfoDto dto = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace+"selectOne",member_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return dto;
	}
	
	public int update(MyinfoDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace+"update",dto);
			if(res > 0) {
				session.commit(); //openSession(true)이면 commit 필요없다
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		return res;
	}
	public int delete(String member_id) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace+"delete",member_id);
			if(res > 0) {
				session.commit(); //openSession(true)이면 commit 필요없다
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		return res;
	}
}
