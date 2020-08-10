package com.agenda.medilocker;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MediLockerDao extends MediLockerSqlMapConfig {
	private String namespace = "MediLockerRegist.";
	
	public List<MediLockerDto> selectList(String member_id) {
		
		SqlSession session = null;
		List<MediLockerDto> list = new ArrayList<MediLockerDto>();
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace+"selectList",member_id);
		} catch (Exception e) {
			System.out.println("[ERROR] 3. 4.SelectList");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public int insert(MediLockerDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace+"insert",dto);
			
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("[ERROR] 3. 4.");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}
	
	public int update(MediLockerDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace+"update",dto);
			
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("[ERROR] 3. 4.");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}
	
	public int delete(int pres_no) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace+"delete", pres_no);
			
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("[ERROR] 3. 4. delete");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}
}
