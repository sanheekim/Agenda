package com.agenda.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.agenda.login.LoginDao;
import com.agenda.login.LoginDto;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {

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
		
		if(command.equals("login")) {
			
			String member_id = request.getParameter("member_id");
			String member_pw = request.getParameter("member_pw");
			
			System.out.println(member_id);
			LoginDao dao = new LoginDao();
			LoginDto dto  = dao.login(member_id, member_pw);
			
			System.out.println(dto.getMember_role());
			System.out.println(dto.getMember_id());
			System.out.println(dto.getMember_pw());
			System.out.println(dto.getMember_addr());
	
			
			
			if(dto.getMember_role().equals("ADMIN")) {
				
				//dispatcher
				//forward쓰기
				HttpSession session = request.getSession();
				session.setAttribute("dto", dto);
			    RequestDispatcher dispatch = request.getRequestDispatcher("login/loginAdmin.jsp");
				dispatch.forward(request, response);
			
			}

			else if(dto.getMember_role().equals("USER")) {
				//dispatcher
				//forward쓰기
				HttpSession session = request.getSession();
				session.setAttribute("dto", dto);
			    RequestDispatcher dispatch = request.getRequestDispatcher("login/loginUser.jsp");
				dispatch.forward(request, response);
			
			}
			
		}

	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}



}
