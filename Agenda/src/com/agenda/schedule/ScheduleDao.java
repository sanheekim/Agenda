package com.agenda.schedule;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.agenda.alrim.AlrimDto;


public class ScheduleDao extends ScheduleSqlMapConfig {
	private String namespace = "ScheduleMapper.";
	
	public int insert(Map<String,Object> map) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace+"insert",map);
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
	public List<String> selectList(String member_id) {
		SqlSession session = null;
		List<String> list = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list =  session.selectList(namespace+"selectList",member_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	public ScheduleDto selectOne(String member_id) {
		SqlSession session = null;
		ScheduleDto dto = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace+"selectOne",member_id);
			System.out.println(">>"+session+"dao 글 보기 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] 3.4.");
		} finally {
			session.close();
			System.out.println("dao종료");
		}
		
		return dto;
	}
	
	
	public int update(ScheduleDto dto) {
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
}
