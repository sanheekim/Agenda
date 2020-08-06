package com.agenda.googlelogion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/googleController")
public class googleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public googleController() {
		System.out.println("컨트롤러 들어왔다!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		googleLoginDao dao = new googleLoginDao();
		googleLoginDto dto = new googleLoginDto();

		String command = request.getParameter("command");
		System.out.println(command);
		HttpSession session = request.getSession();
		// 로그인페이지
		if (command.equals("login")) {

			String member_id = request.getParameter("member_id");
			String member_pw = request.getParameter("member_pw");
			String member_enabled = request.getParameter("member_enabeld");
//			dao.login(member_id, member_pw);

			googleLoginDto input = new googleLoginDto(member_id, member_pw, member_enabled);
			dto = dao.login(input);
			System.out.println("dto=" + dto);

			System.out.println(member_id);
			System.out.println(member_pw);

			if (dto != null) {

				session.setAttribute("logindto", dto);
				session.setMaxInactiveInterval(10 * 60);

				System.out.println("DTO 있음  = mypage main으로 보냄");
				// User, Admin 구분하지 않고 바로 main으로 보내기
				RequestDispatcher dispatch = request.getRequestDispatcher("main/main.jsp");
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
			
			googleLoginDto FindDto = new googleLoginDto();
			FindDto.setMember_name(member_name);
			FindDto.setMember_email(member_email);

			dto = dao.findid(FindDto);
			
		
			if (dto != null) {
				String member_id = dto.getMember_id();
				request.setAttribute("member_id", member_id);
				RequestDispatcher dispatch = request.getRequestDispatcher("login/loginSearchId.jsp");
				dispatch.forward(request, response);

			} else {
				
				//response.sendRedirect("login/loginForgotId.jsp");
				
				loginResponse("찾으시는 아이디가 존재하지 않습니다.","login/loginForgotId.jsp",response);
				
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
