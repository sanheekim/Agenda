package com.agenda.comm;

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

import com.agenda.qna.QNADao;


@WebServlet("/commController.do")
public class COMMController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		COMMDao dao = new COMMDao();
		QNADao qnadao = new QNADao();
		
		if (command.equals("commlist")) {
			
			int qna_no = Integer.parseInt(request.getParameter("qna_no"));
			List<COMMDto> commlist = dao.list(qna_no);
			
			System.out.println(qna_no);
			System.out.println(commlist);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qna_no", qna_no);
			map.put("commlist", commlist);
			
			request.setAttribute("commmap", map);
			dispatch("qna/qna_detail.jsp", request, response);
			
		} else if (command.equals("write")) {
			
			String comm_content = request.getParameter("comm_content");
			int qna_no = Integer.parseInt(request.getParameter("qna_no"));
			System.out.println(comm_content + " " + qna_no);
			
			COMMDto dto = new COMMDto(comm_content, qna_no);
			int res = dao.insert(dto);
			
			if(res>0) {
				response.sendRedirect("qnaController.do?command=detail&qna_no="+qna_no);
			}
			
		}  else if (command.equals("delete")) {
			int qna_no = Integer.parseInt(request.getParameter("qna_no"));
			
			int res = dao.delete(qna_no);
			System.out.println("delete : " + res);
			if (res > 0) {
				String msg = "삭제 성공!";
				jsResponse(msg, "qnaController.do?command=detail&qna_no="+qna_no, request, response);
			} else {
				String msg = "삭제 실패!";
				jsResponse(msg, "qnaController.do?command=detail&qna_no="+qna_no, request, response);
			}
		} else if (command.equals("update")) {
			
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
