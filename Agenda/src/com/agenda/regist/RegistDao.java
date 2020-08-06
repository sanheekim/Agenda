package com.agenda.regist;

import com.agenda.regist.RegistSqlMapConfig;

import org.apache.ibatis.session.SqlSession;

public class RegistDao extends RegistSqlMapConfig {

	private String namespace="com.agenda.regist.RegistMapper.";
		
	 public RegistDto idCheck(String member_id) {
	      SqlSession session = null;
	      RegistDto dto = null;
	      
	      try {
	         session = getSqlSessionFactory().openSession(false);
	         dto = session.selectOne(namespace+"selectOne", member_id);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         session.close();
	      }
	       
	      return dto;     
	}
	 
//	@Override
//	public RegistDto getSaltId(String member_id) {
//		SqlSession session = null;
//		RegistDto dto = new RegistDto();
//		
//		try {
//			session = getSqlSessionFactory().openSession(false);
//			dto = session.selectOne(namespace + "selectOne", member_id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//		
//		return dto;
//	}
	 
	public boolean insertUser(RegistDto dto) {
		
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
		return res > 0? true : false;
	}
	
	
//	public boolean insertNaver(RegistDto naver) {
//		
//		SqlSession session = null;
//		int res = 0;
//		
//		try {
//			session = getSqlSessionFactory().openSession(false);
//			res = session.insert(namespace + "insertNaver", naver);
//			if(res > 0) {
//				session.commit();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//		return res > 0? true : false;
//	}

}










