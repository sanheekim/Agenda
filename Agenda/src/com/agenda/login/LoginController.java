package com.agenda.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
System.out.println("컨트롤러로 넘어옴");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");		
		System.out.println(command);
		
		//로그인페이지
		if(command.equals("login")) {
			
			String member_id = request.getParameter("member_id");
			String member_pw = request.getParameter("member_pw");

			LoginDao dao = new LoginDao();
			LoginDto dto  = dao.login(member_id, member_pw);
			
			
			HttpSession session = request.getSession();
			
			
			if(dto != null) {
				
				
				session.setAttribute("dto",dto);
				session.setMaxInactiveInterval(10*60);
				
				
				// User, Admin 구분하지 않고 바로 main으로 보내기
				RequestDispatcher dispatch = request.getRequestDispatcher("header/loginMain.jsp");
				dispatch.forward(request, response);
				//response.sendRedirect("main/main.jsp");
				
				
//			if(dto.getMember_role().equals("ADMIN")) {				
//				
//		    RequestDispatcher dispatch = request.getRequestDispatcher("login/loginAdmin.jsp");
//			dispatch.forward(request, response);
//				response.sendRedirect(".login/loginAdmin.jsp");
//			}

//			else if(dto.getMember_role().equals("USER")) {
//				dispatcher
//				forward쓰기
//
//				RequestDispatcher dispatch = request.getRequestDispatcher("login/loginUser.jsp");
//				dispatch.forward(request,response);			
//				response.sendRedirect(".login/loginUser.jsp");
//			}			
			}else {
				response.sendRedirect("login/logingForm.jsp");
			}
			

		}

	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
