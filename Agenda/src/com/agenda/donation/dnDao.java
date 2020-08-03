package com.agenda.donation;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class dnDao extends dnSqlMapConfig {
	
	private String namespace="com.agenda.donation.dnMapper.";
	
	public List<dnDto> selectList() {
		
		SqlSession session = null;
		List<dnDto> list = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("com.agenda.donation.dnMapper.selectList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public int insert(dnDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace+"insert", dto);
			if(res>0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}
	
	public dnDto selectOne(int dona_no) {
		
		SqlSession session = null;
		dnDto dto = null;

		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace+"selectOne", dona_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	return dto;
	}
	
	public int delete(int dona_no) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace+"delete", dona_no);
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
