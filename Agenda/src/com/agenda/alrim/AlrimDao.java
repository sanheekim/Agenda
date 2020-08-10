package com.agenda.alrim;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;



public class AlrimDao extends AlrimSqlMapConfig {
	private String namespace = "AlrimMapper.";
	

	public List<String> selectList(AlrimDto dto) {
		SqlSession session = null;
		List<String> list = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list =  session.selectList(namespace+"selectList",dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	
	public int delete(int sche_no) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace+"delete",sche_no);
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
