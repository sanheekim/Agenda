package com.agenda.qna;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/qnaController.do")
public class QNAController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		QNADao dao = new QNADao();
		
		if (command.equals("list")) {
			
			String searchOption = request.getParameter("searchOption");
			String keyword = request.getParameter("keyword");
			int curPage = Integer.parseInt(request.getParameter("curPage"));
			
			System.out.println("서치옵션 : " + searchOption + " 키워드 : " + keyword + " 현재 페이지 : " + curPage);
				
			// 레코드의 갯수 계산
			
			int count = dao.listCount(searchOption, keyword);
			System.out.println("글 개수 : " + count);

			// 페이지 나누기 관련 처리
			QNABoardPager boardPager = new QNABoardPager(count, curPage);
			
			// 페이지당 게시물 수 - 완료
			int start = boardPager.getPageBegin();
			int end = boardPager.getPageEnd();
			
			List<QNADto> list = dao.selectList(start, end, searchOption, keyword);
			
			// 데이터를 맵에 저장
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list); // list
			map.put("count", count); // 레코드의 갯수
			map.put("searchOption", searchOption); // 검색옵션
			map.put("keyword", keyword); // 검색키워드
			map.put("boardPager", boardPager);
			
			request.setAttribute("map", map);
			dispatch("qna/qna_list.jsp", request, response);
			
		} else if (command.equals("write")) {
			response.sendRedirect("qna/qna_insert.jsp");
		} else if (command.equals("writeres")) {
			String qna_title = request.getParameter("qna_title");
			String qna_content = request.getParameter("qna_content");
			String member_id = request.getParameter("member_id");
			System.out.println("Controller -> 제목 : " + qna_title + " 내용 : "  + qna_content + " 아이디 : " + member_id);

			QNADto dto = new QNADto(qna_title, qna_content, member_id);

			int res = dao.insert(dto);

			if (res > 0) {
				response.sendRedirect("qnaController.do?command=list&curPage=1&searchOption=all&keyword=");
			} else {
				String msg = "작성 실패!";
				jsResponse(msg, "qnaController.do?command=write", request, response);
			}
		} else if (command.equals("detail")) {
			int qna_no = Integer.parseInt(request.getParameter("qna_no"));
			QNADto dto = dao.selectOne(qna_no);
			request.setAttribute("detail", dto);
			dispatch("qna/qna_detail.jsp", request, response);
		} else if (command.equals("delete")) {
			int qna_no = Integer.parseInt(request.getParameter("qna_no"));
			int res = dao.delete(qna_no);
			System.out.println("delete : " + res);
			if (res > 0) {
				String msg = "삭제 성공!";
				jsResponse(msg, "qnaController.do?command=list", request, response);
			} else {
				String msg = "수정 실패!";
				jsResponse(msg, "qnaController.do?command=detail", request, response);
			}
		} else if (command.equals("update")) {
			int qna_no = Integer.parseInt(request.getParameter("qna_no"));
			QNADto dto = dao.selectOne(qna_no);
			request.setAttribute("update", dto);
			dispatch("qna/qna_update.jsp", request, response);
		} else if (command.equals("updateres")) {
			int qna_no = Integer.parseInt(request.getParameter("qna_no"));
			String qna_title = request.getParameter("qna_title");
			String qna_content = request.getParameter("qna_content");
			String member_id = request.getParameter("member_id");
			System.out.println("제목 : " + qna_title + " 내용 : "  + qna_content + " 아이디 : " + member_id);

			QNADto dto = new QNADto(qna_no, qna_title, qna_content, member_id);

			int res = dao.update(dto);
			System.out.println("update : " + res );

			if (res > 0) {
				dispatch("qnaController.do?command=detail&qna_no=" + qna_no, request, response);
			} else {
				String msg = "수정 실패!";
				jsResponse(msg, "qnaController.do?command=update", request, response);
			}
		}

	}

	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	public void jsResponse(String msg, String url, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String alert = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";

		response.getWriter().append(alert);
	}

}