package com.agenda.regist;

import com.agenda.regist.RegistSqlMapConfig;

import org.apache.ibatis.session.SqlSession;

public class RegistDao extends RegistSqlMapConfig {

	private String namespace="com.agenda.regist.RegistMapper.";
	
//	public RegistDto idCheck(String member_id) {
//		
//		SqlSession session = null;
//		int res = 0;
//		
//		try {
//			session = getSqlSessionFactory().openSession(false);
//			res = session.selectOne(namespace + "selectOne", member_id);
//			if(res > 0) {
//				session.commit();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//		
//		return null;
//	}
	
//	public RegistDto idCheck(String member_id) {
//		
//		SqlSession session = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		RegistDto dto = new RegistDto();
//		try {
//			session = getSqlSessionFactory().openSession(false);
//			rs = pstm.executeQuery();
//			System.out.println("4.query 실행 및 리턴");
//			while(rs.next()) {
//				dto.setMember_id(rs.getString(1));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//		
//		return null;
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

}










