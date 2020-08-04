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

		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		COMMDao dao = new COMMDao();
		
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
			
		} else if (command.equals("commwrite")) {
			
			String member_id = request.getParameter("member_id");
			String comm_content = request.getParameter("comm_content");
			int qna_no = Integer.parseInt(request.getParameter("qna_no"));
			System.out.println(member_id + " " + comm_content + " " + qna_no);
			
			COMMDto dto = new COMMDto();
			dto.setMember_id(member_id);
			dto.setComm_content(comm_content);
			dto.setQna_no(qna_no);
			
			int res = dao.insert(dto);
			System.out.println("write : " + res);
			
		}  else if (command.equals("commdelete")) {
			int comm_no = Integer.parseInt(request.getParameter("comm_no"));
			System.out.println(comm_no);
			
			int res = dao.delete(comm_no);
			System.out.println("delete : " + res);
			
			
		} else if (command.equals("commupdate")) {
			int comm_no = Integer.parseInt(request.getParameter("comm_no"));
			String comm_content = request.getParameter("comm_content");
			System.out.println(comm_no + " " + comm_content);
			
			COMMDto dto = new COMMDto();
			dto.setComm_no(comm_no);
			dto.setComm_content(comm_content);
			
			int res = dao.update(dto);
			System.out.println("update : " + res);
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
