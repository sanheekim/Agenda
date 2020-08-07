package com.agenda.googlelogion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/googleController.do")
public class googleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public googleController() {
		System.out.println("컨트롤러 들어왔다!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 

		String command = request.getParameter("command");
		System.out.println(command);
		HttpSession session = request.getSession();

		// 로그인페이지
		if (command.equals("login")) {

			String member_id= request.getParameter("member_id");
			String member_pw= request.getParameter("member_pw");
			System.out.println(member_id+member_pw);
		}

		else if (command.equals("logout")) {

			// session 객체가 가진 값 삭제
			loginResponse("로그아웃 되셨습니다!", "main/main.jsp", response);
			session.invalidate();
			// response.sendRedirect("main/main.jsp");

		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    doGet(request, response);

	}

	public void loginResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String js = "<script type = 'text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";

		response.getWriter().append(js);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
