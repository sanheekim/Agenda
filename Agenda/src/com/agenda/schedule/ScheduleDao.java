package com.agenda.schedule;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.agenda.schedule.ScheduleDto;

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
	public List<String> selectList(ScheduleDto dto) {
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
