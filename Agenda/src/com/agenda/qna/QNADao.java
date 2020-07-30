package com.agenda.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class QNADao extends SqlMapConfig {
	
	private String namespace = "com.agenda.qna.";
	
//	//글목록
//	public List<QNADto> selectList(){
//		SqlSession session = null;
//		List<QNADto> list = null;
//		
//		try {
//			session = getSqlSessionFactory().openSession(false);
//			list = session.selectList(namespace+"selectList");
//			System.out.println(">>"+session);
//		} catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("[ERROR] 3.4.");
//		} finally {
//			session.close();			
//		}
//		return list;
//	}
	
	public List<QNADto> selectList(Paging boardPager){
		int startNum = boardPager.getStartNum();
		int endNum = boardPager.getEndNum();
		System.out.println("페이지의 게시물 시작번호" + startNum + " 끝번호" + endNum);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		SqlSession session = null;
		List<QNADto> boardpaging = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			boardpaging = session.selectList(namespace+"selectList", map);
			System.out.println("dao list 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] dao");
		} finally {
			session.close();
			System.out.println("dao종료");
		}
		
		return boardpaging;
		
	}
	
	public int getAllCount() {
		SqlSession session = null;
		int count = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			count = session.selectOne(namespace + "count");
			System.out.println("dao getAllCount 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] dao");
		} finally {
			session.close();
			System.out.println("dao종료");
		}

		return count;
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
