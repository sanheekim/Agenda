package com.agenda.regist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegistController")
public class RegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public RegistController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); //get으로 와도 포스트로 보내곘다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(); //세션받는거
	    PrintWriter out = response.getWriter();		
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		RegistDao dao = new RegistDao();
		
//		if (command.equals("login")) {
//			String myid = request.getParameter("myid");
//			String mypw = request.getParameter("mypw");
//			RegistDto dto = dao.login(myid, mypw);
			
//			if (dto != null) {
//				// session scope에 담기!
//				session.setAttribute("dto", dto);
//				// session 유효 시간 : 10분동안 활동이 없으면 invalidate
//				// default : 30분 / 음수일 때 무제한 
//				session.setMaxInactiveInterval(10*60);
//				
//				if (dto.getMyrole().equals("ADMIN")) {
//					response.sendRedirect("adminmain.jsp");
//				} else if (dto.getMyrole().equals("USER")) {
//					response.sendRedirect("usermain.jsp");
//				}
//			} else {				
//				out.println("<script>alert('login 실패'); location.href='index.jsp';</script>");
//			}
		 if (command.equals("registform")){
			response.sendRedirect("registform.jsp");
			
		} else if (command.equals("idcheck")) {
			// 1.
			String member_id = request.getParameter("member_id");
			// 2.
			RegistDto dto = dao.idCheck(member_id);
			boolean idnotused = true;
			if (dto.getMember_id() != null) {
				idnotused = false;
			}
			// 3.
			// 4.
			response.sendRedirect("idcheck.jsp?idnotused="+idnotused);
		
		} else if (command.equals("registres")){
			// 1.
			String member_id = request.getParameter("member_id");
			String member_pw = request.getParameter("member_pw");
			String member_name = request.getParameter("member_name");
			String member_email = request.getParameter("member_email");
			String member_addr = request.getParameter("member_addr");
			String member_enabled = request.getParameter("member_enable");
			String member_role = request.getParameter("member_role");
			String member_token = request.getParameter("member_token");
			String member_salt = request.getParameter("member_salt")
			String member_phone = request.getParameter("member_phone"); 
			int member_no = Integer.parseInt(request.getParameter("member_no"));
			
			//System.out.println(member_no);
			
			RegistDto dto = new RegistDto(member_id, member_pw, member_name, member_email, member_addr, 
					member_enabled, member_role, member_token, member_salt, member_phone, 0);
			
			// 2.
			boolean res = dao.insertUser(dto);
			if (res) {	
				
				out.println("<scipt>alert('회원가입 성공'); location.href='index.jsp';</script>");
				
			} else {
				
				out.println("<scipt>alert('회원가입 실패'); location.href='registhform.jsp';</script>");
			
			}
//		} else if (command.equals("logout")) {
//			// session 객체가 가진 값 삭제
//			session.invalidate();
//			response.sendRedirect("index.jsp");
//		} else if (command.equals("userlistall")) {
//			
//		} else if (command.equals("userlisten")) {
//			// 1.
//			// 2.
//			List<MYMemberDto> list = dao.selectEnabled();
//			// 3.
//			request.setAttribute("enabled", list);
//			// 4.
//			pageContext.forward("userlisten.jsp");
//			
//		} else if (command.equals("updateroleform")) {
//			// 1. 
//			int myno = Integer.parseInt(request.getParameter("myno"));
//			// 2.
//			MYMemberDto dto = dao.selectUser(myno);
//			// 3.
//			request.setAttribute("dto", dto);
//			// 4.
//			pageContext.forward("updaterole.jsp");
//			
//		} else if (command.equals("updateroleres")) {
//			// 1.
//			int myno = Integer.parseInt(request.getParameter("myno"));
//			String myrole = request.getParameter("myrole");
//			// 2.
//			int res = dao.updateUserRole(myno, myrole);
//			// 3.
//			// 4.
//			if (res > 0) {
//				
//				out.println("<scipt>alert('회원가입 변경 성공'); location.href='logincontroller.jsp?command=userlisten';</script>");
//						
//			} else {
//	
//				out.println("<scipt>alert('회원가입 변경 실패'); location.href='logincontroller.jsp?command=updateroleform&myno=<%=myno%>';</script>");
//	
//			}
		}
	}

}
