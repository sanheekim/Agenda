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
import javax.servlet.http.HttpSession;

import com.agenda.comm.COMMDao;
import com.agenda.comm.COMMDto;
import com.agenda.login.LoginDao;
import com.agenda.login.LoginDto;

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
		COMMDao commdao = new COMMDao();
		QNADao dao = new QNADao();
		
		if (command.equals("list")) { // 게시물 리스트

			String searchOption = request.getParameter("searchOption"); //검색 옵션
			String keyword = request.getParameter("keyword"); // 검색어
			int curPage = Integer.parseInt(request.getParameter("curPage")); // 페이지
			
			// 게시물 카운트
			int count = dao.listCount(searchOption, keyword);

			// 페이지 나누기 관련 처리
			QNABoardPager boardPager = new QNABoardPager(count, curPage);
			
			// 페이지당 게시물 수
			int start = boardPager.getPageBegin();
			int end = boardPager.getPageEnd();
			
			List<QNADto> list = dao.selectList(start, end, searchOption, keyword);
			
			// 데이터를 맵에 저장
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list); // list
			map.put("count", count); // 게시물 개수
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

			QNADto dto = new QNADto(qna_title, qna_content, member_id);

			int res = dao.insert(dto);

			if (res > 0) {
				response.sendRedirect("qnaController.do?command=list&curPage=1&searchOption=all&keyword=");
			} else {
				String msg = "내용 혹은 제목을 입력해주세요";
				jsResponse(msg, "qnaController.do?command=write", request, response);
			}
		} else if (command.equals("detail")) {
			int qna_no = Integer.parseInt(request.getParameter("qna_no"));
			dao.viewCount(qna_no); //조회수 처리 -> 글 상세보기 할 때마다 조회수 +1
			QNADto dto = dao.selectOne(qna_no);
			List<COMMDto> list = commdao.list(qna_no);
			
			request.setAttribute("detail", dto);
			request.setAttribute("commlist", list); // 글 상세보기에 넘겨줄 댓글 list
			dispatch("qna/qna_detail.jsp", request, response);
		} else if (command.equals("delete")) {
			int qna_no = Integer.parseInt(request.getParameter("qna_no"));
			int res = dao.delete(qna_no);
			
			if (res > 0) {
				String msg = "삭제 성공!";
				jsResponse(msg, "qnaController.do?command=list&curPage=1&searchOption=all&keyword=", request, response);
			} else {
				String msg = "삭제 실패!";
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

			QNADto dto = new QNADto(qna_no, qna_title, qna_content, member_id);

			int res = dao.update(dto);

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
