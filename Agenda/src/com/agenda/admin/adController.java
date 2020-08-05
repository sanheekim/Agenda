package com.agenda.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agenda.myinfo.MyinfoDto;

@WebServlet("/adController")
public class adController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		adDto dto = new adDto();
		adDao dao = new adDao();
		
		String command = request.getParameter("command");
		System.out.println(command);
		
		if(command.contentEquals("allMember")) {
			
			List <adDto> list = dao.selectList();
			request.setAttribute("list", list);
			
			System.out.println(list);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("admin/admanager.jsp");
			dispatch.forward(request, response);

		}
		
		else if(command.contentEquals("allUpdate")) {
			
			String member_role = request.getParameter("member_role");
			
			int res = dao.update(dto);
			System.out.println(res);
			
			if(res>0) {
				System.out.println("수정 중");
				response.sendRedirect("adController?command=admin&member_role="+member_role);
				
			} else {
				response.sendRedirect("admin/admanager.jsp");
			}
			
		}
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
