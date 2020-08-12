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
	
	public List<dnDto> selectOne(String member_id) {
		
		SqlSession session = null;
		List<dnDto> list = null;

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace+"selectOne", member_id);
			System.out.println("dao>>" + list);
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

}
