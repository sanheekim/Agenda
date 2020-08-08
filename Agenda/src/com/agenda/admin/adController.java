package com.agenda.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		if(command.contentEquals("adminpage")) {
	
			response.sendRedirect("admin/adlayout.jsp?member_id="+request.getParameter("member_id"));

		}
		
		else if(command.contentEquals("admyinfo")) {
			
			String member_id = request.getParameter("member_id");
			System.out.println(member_id);
			
			dto = dao.selectOne(member_id);
			request.setAttribute("dto", dto);
			System.out.println(dto);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("admin/admyinfo.jsp");
			dispatch.forward(request, response);
					
		}
		
		else if(command.contentEquals("allMember")) {
			
			List <adDto> list = dao.selectList();
			request.setAttribute("list", list);
			
			System.out.println(list);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("admin/admanager.jsp");
			dispatch.forward(request, response);

		}
		
		else if(command.contentEquals("allUpdate")) {
			
			String member_id = request.getParameter("member_id");
			System.out.println(member_id);
			
			dto = dao.selectOne(member_id);
			request.setAttribute("dto", dto);
			System.out.println(dto);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("admin/adrolemanager.jsp");
			dispatch.forward(request, response);
		}
		
		else if(command.contentEquals("updateres")) {
			
			String member_id = request.getParameter("member_id");
			String member_role = request.getParameter("member_role");
			System.out.println(member_id);
			System.out.println(member_role);
			
			//dto에 세팅
			dto = new adDto(member_id, member_role);
			
			//dto 대신 adDao에서 맵에 담아도 됨
			int res = dao.update(dto);
			System.out.println(res);
			
			if (res > 0){
				System.out.println("등급 변경 성공!");
				RequestDispatcher dispatch = request.getRequestDispatcher("./adController?command=allMember");
				dispatch.forward(request, response);
			} else {
				System.out.println("등급 변경 실패");
				RequestDispatcher dispatch = request.getRequestDispatcher("admin/adrolemanager.jsp");
				dispatch.forward(request, response);
			}
		}
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
