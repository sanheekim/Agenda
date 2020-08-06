package com.agenda.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class NoticeDao extends NoticeSqlMapConfig {

	private String namespace = "com.agenda.notice.";

	// 공지사항 리스트 보기
	public List<NoticeDto> selectList(int start, int end, String searchOption, String keyword) {
		
		SqlSession session = null;
		List<NoticeDto> list = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		// BETWEEN #{start},#{end}에 입력될 값
		map.put("start", start);
		map.put("end", end);

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace + "selectList", map);
			System.out.println(">>" + session + "dao글보기 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error: list Dao 3.4");
		} finally {
			System.out.println("글보기 dao종료");
			session.close();
		}

		return list;
	}
	
	// 게시글 수 countArticle
		public int listCount(String searchOption, String keyword) {

			SqlSession session = null;
			int res = 0;

			// 검색옵션, 키워드 맵에 저장
			Map<String, String> map = new HashMap<String, String>();
			map.put("searchOption", searchOption);
			map.put("keyword", keyword);

			System.out.println(searchOption);
			System.out.println(keyword);
			try {
				session = getSqlSessionFactory().openSession(false);
				res = session.selectOne(namespace + "listCount", map);
				System.out.println(">>" + session + "dao글 카운트 성공");
			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("[listcount error] 3,4");
			} finally {
				session.close();
				System.out.println("dao글 카운트 종료");
			}

			return res;
		}

		// 공지사항 게시글 상세 내용 보기 
		public NoticeDto selectOne(int notice_no) {
			SqlSession session = null;
			NoticeDto dto = null;

			try {
				session = getSqlSessionFactory().openSession(false);
				dto = session.selectOne(namespace + "selectOne", notice_no);
				System.out.println(">>" + session + "글 상세 내용 보기 성공 ");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("공지사항 게시글 보기 error 3,4");
			} finally {

				session.close();
				System.out.println("글 상세내용 보기 dao종료");
			}

			return dto;
		}
		
		
		
	// 공지사항 글 쓰기
	public int insert(NoticeDto dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace + "insert", dto);
			if (res > 0) {
				session.commit();
				System.out.println("공지사항 insert 성공");
			}
		} catch (Exception e) {
			System.out.println("error: [insert dao] error 3.4");
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("insert dao종료");
		}
		return res;

	}
	
	
	//글 수정
	public int update(NoticeDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace+"update",dto);
			if(res>0) {
				session.commit();
				System.out.println(">>"+session+"공지사항 글 수정 성공");
			}
		} catch (Exception e) {
			System.out.println("공지사항 글 수정 error 3,4");
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("공지사항 글 수정 dao 종료 ");
		} return res;
		
	}
	

	//공지사항 글 삭제 
	public int delete(int notice_no) {
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(false);
		res = session.delete(namespace+"delete",notice_no);
		
		if(res>0) {
			try {
				session.commit();
				System.out.println(">>"+session+"dao글 삭제 성공");
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println(">> error dao delete 3.4");
			}finally {
				session.close();
				System.out.println("delete dao 종료");
			}
			
		}
		return res;
	}
	
	//공지사항 게시판 조회수
	public int noticeViewCount(int notice_no) {
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(false);
		res = session.update(namespace+"noticeviewcount",notice_no);
		
		if(res>0) {
			try {
				session.commit();
				System.out.println(">>"+session+"noticeBoard 조회수 성공");
			} catch (Exception e) {
				System.out.println(">> error Dao NoticeBoard조회수 3.4");
				e.printStackTrace();
			} finally {
				session.close();
				System.out.println("noticeBoard hit dao 종료");
			}
		}
		return res;
	}
	

}