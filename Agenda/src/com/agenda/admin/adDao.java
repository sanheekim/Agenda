package com.agenda.admin;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class adDao extends adSqlMapConfig {
	
	private String namespace = "com.agenda.admin.admapper.";
	
	public List<adDto> selectList() {
		
		SqlSession session = null;
		List<adDto> list = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("com.agenda.admin.adMapper.selectList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
		
	}
	
	public int update(adDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace+"update", dto);
			
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
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
			res = session.delete(namespace+"delete", member_id);
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return res;
	}
	

}
