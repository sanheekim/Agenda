package com.agenda.comm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


public class COMMDao extends COMMSqlMapConfig {

	private String namespace = "com.agenda.comm.mapper.";
	
	
	// 1. 댓글 입력 create
	public int insert(COMMDto dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace + "insertReply", dto);
			if (res > 0) {
				session.commit();
				System.out.println(">>" + session + "dao 댓글 작성 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] 3.4.");
		} finally {
			session.close();
			System.out.println("dao종료");
		}

		return res;
	}

	
	// 2. 댓글 목록
	public List<COMMDto> list(int qna_no) {

		SqlSession session = null;
		List<COMMDto> list = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qna_no", qna_no);

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace + "listReply",qna_no);
			System.out.println(">>" + session + "dao 댓글 목록 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] 3.4.");
		} finally {
			session.close();
		}

		return list;
	}
	
	

	// 4. 댓글 수정
	public int update(COMMDto dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace + "updateReply", dto);
			if (res > 0) {
				session.commit();
				System.out.println(">>" + session + "dao 댓글 수정 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] 3.4.");
		} finally {
			session.close();
			System.out.println("dao종료");
		}

		return res;
	}

	// 5. 댓글 삭제
	public int delete(int comm_no) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace + "deleteReply", comm_no);
			if (res > 0) {
				session.commit();
				System.out.println(">>" + session + "dao 댓글 삭제 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] 3.4.");
		} finally {
			session.close();
			System.out.println("dao종료");
		}

		return res;
	}
	

}
