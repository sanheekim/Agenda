package com.agenda.qna;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/qnaController.do")
public class qnaController extends HttpServlet {
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
			int page = 1;

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				System.out.println("페이지" + page);
			}
			
			Paging paging = new Paging();
			int count = dao.getAllCount();

			paging.setTotalcount(count);
			paging.setPage(page);

			List<QNADto> list = dao.selectList(paging);

			request.setAttribute("list", list);
			request.setAttribute("boardpaging", paging);

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
				response.sendRedirect("qnaController.do?command=list");
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
