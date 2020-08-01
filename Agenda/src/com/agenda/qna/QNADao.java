package com.agenda.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class QNADao extends QNASqlMapConfig {
	
	private String namespace = "com.agenda.qna.";
	
	//게시글 리스트
	public List<QNADto> selectList(int start, int end, String searchOption, String keyword) {
		
		SqlSession session = null;
		List<QNADto> list = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		// BETWEEN #{start}, #{end}에 입력될 값
		map.put("start", start);
		map.put("end", end);
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace+"selectList", map);
			System.out.println(">>"+session+"dao 글 보기 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] 3.4.");
		} finally {
			session.close();
		}
		
		return list;
	}
	
		
	//게시글 수 countArticle(
	public int listCount(String searchOption, String keyword) {
		
		SqlSession session = null;
		int res = 0;
		
		// 검색옵션, 키워드 맵에 저장
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne(namespace+"listCount", map);
			System.out.println(">>"+session+"dao 글 카운트 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] 3.4.");
		} finally {
			session.close();
			System.out.println("dao종료");
		}
		
		return res;

	}
	
	//글상세내용
	public QNADto selectOne(int qna_no) {
		SqlSession session = null;
		QNADto dto = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace+"selectOne", qna_no);
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
	
	//글쓰기
	public int insert(QNADto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace+"insert", dto);
			if(res>0) {
				session.commit();
				System.out.println(">>"+session+"dao 글 작성 성공");
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
	
	//글수정
	public int update(QNADto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace+"update", dto);
			if(res>0) {
				session.commit();
				System.out.println(">>"+session+"dao 글 수 성공");
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
	
	//글삭제
	public int delete(int qna_no) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace+"delete", qna_no);
			if(res>0) {
				session.commit();
				System.out.println(">>"+session+"dao 글 삭제 성공");
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
