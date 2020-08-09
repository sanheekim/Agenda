package com.agenda.myinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MyinfoController")
public class MyinfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyinfoController() {
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		MyinfoDao dao = new MyinfoDao();
		String command = request.getParameter("command");
		System.out.println("command :" + command);
		if(command.equals("myinfo")) {
			String member_id = request.getParameter("member_id");
			MyinfoDto dto = dao.selectOne(member_id);
			
			session.setAttribute("dto",dto); //dto.변수 값을 뷰에 뿌려줄려고 
			
			RequestDispatcher dispatch = request.getRequestDispatcher("myinfo/myinfo.jsp");
			dispatch.forward(request, response);
			
			
				
			
		}
		else if(command.equals("myinfoUpdateform")) {
			String member_id = request.getParameter("member_id");
			MyinfoDto dto = new MyinfoDto();
			dto = dao.selectOne(member_id);
			
			//request.setAttribute("dto",dto); //dto.변수 값을 뷰에 뿌려줄려고 
			
			RequestDispatcher dispatch = request.getRequestDispatcher("myinfo/myinfoUpdate.jsp");
			dispatch.forward(request, response);
			//response.sendRedirect("myinfo/myinfoUpdate.jsp");
			
			
			
			
		}
		else if(command.equals("myinfoUpdate")) {
			String member_id = request.getParameter("member_id");
			String email = request.getParameter("email");
			String addr = request.getParameter("addr");
			
			int res = dao.update(new MyinfoDto(member_id,email,addr));
			System.out.println(res);
			
			if(res > 0) {
				System.out.println("들어옴");
				response.sendRedirect("MyinfoController?command=myinfo&member_id="+member_id);//command가 list인걸로 다시요청해라
				
			}else {
				response.sendRedirect("myinfo/myinfoUpdate.jsp");
			}
			
		}
		else if(command.contentEquals("myinfoDelete")) {
			String member_id = request.getParameter("member_id");
			
			int res = dao.delete(member_id);
			System.out.println(res);
			
			if(res > 0) {
				System.out.println("들어옴");
				session.invalidate();
				loginResponse("탈퇴 되었습니다.", "main/main.jsp", response);
				
			}else {
				loginResponse("탈퇴 실패하였습니다.", "MyinfoController?command=myinfo&member_id="+member_id, response);
			}
		}
			
		}
	
	public void loginResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String js = "<script type = 'text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";

		response.getWriter().append(js);
	}
	
	}
