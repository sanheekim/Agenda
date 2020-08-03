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
	private Object member_id;

	public LoginController() {
		System.out.println("컨트롤러로 넘어옴");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		LoginDao dao = new LoginDao();
		LoginDto dto = new LoginDto();

		String command = request.getParameter("command");
		System.out.println(command);
		HttpSession session = request.getSession();
		// 로그인페이지
		if (command.equals("login")) {

			String member_id = request.getParameter("member_id");
			String member_pw = request.getParameter("member_pw");

//			dao.login(member_id, member_pw);

			LoginDto input = new LoginDto(member_id, member_pw);
			dto = dao.login(input);
			System.out.println("dto=" + dto);

			System.out.println(member_id);
			System.out.println(member_pw);

			if (dto != null) {

				session.setAttribute("dto", dto);
				session.setMaxInactiveInterval(10 * 60);

				System.out.println("DTO 있음  = mypage main으로 보냄");
				// User, Admin 구분하지 않고 바로 main으로 보내기
				RequestDispatcher dispatch = request.getRequestDispatcher("header/loginMain.jsp");
				dispatch.forward(request, response);
				// response.sendRedirect("header/loginMain.jsp");

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

			} else {
				System.out.println("dto is null : login form 으로 다시 보냄");
				loginResponse("아이디 또는 비밀번호가 틀렸습니다", "login/loginForm.jsp", response);
				// response.sendRedirect("login/loginForm.jsp");

			}

		}

		else if (command.equals("logout")) {

			// session 객체가 가진 값 삭제
			loginResponse("로그아웃 되셨습니다!", "main/main.jsp", response);
			session.invalidate();
			// response.sendRedirect("main/main.jsp");

		}

		else if (command.equals("forgotId")) {

			System.out.println("hi! you can find your id ");
			String member_name = request.getParameter("member_name");
			String member_email = request.getParameter("member_email");

			System.out.println(member_name);
			System.out.println(member_email);
			
			LoginDto FindDto = new LoginDto();
			FindDto.setMember_name(member_name);
			FindDto.setMember_email(member_email);

			dto = dao.findid(FindDto);

			request.setAttribute("member_id", member_id);

			if (member_id == null) {

				loginResponse("찾으시는 아이디가 존재하지 않습니다.","login/loginForgotId.jsp",response);
				//RequestDispatcher dispatch = request.getRequestDispatcher("login/loginSearchFalse.jsp");
				//dispatch.forward(request, response);
			

			} else {

				RequestDispatcher dispatch = request.getRequestDispatcher("login/loginSearchId.jsp");
				dispatch.forward(request, response);

			}
		}

		/*
		 * else if (command.equals("naverlogin")) {
		 * 
		 * //naverlogin을 눌렀을때 naver id로 계정이 가입되어있는지 확인 하고 있다면 바로 로그인, 없다면 회원가입폼으로 넘겨야함 }
		 */

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
