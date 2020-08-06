package com.agenda.admin;

import java.io.IOException;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.agenda.admin.adDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
			
			String member_id = request.getParameter("member_id");
			System.out.println(member_id);
			
			dto = dao.selectOne(member_id);
			request.setAttribute("dto", dto);
			System.out.println(dto);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("adrolemanager.jsp");
			dispatch.forward(request, response);
		}
		
		else if(command.contentEquals("updateres")) {
			
			String member_id = request.getParameter("member_id");
			String member_role = request.getParameter("member_role");
			
			int res = dao.update(member_id, member_role);
			if (res > 0){
				System.out.println("등급 변경 성공!");
				JOptionPane.showMessageDialog(null, "등급 변경 성공!");
				RequestDispatcher dispatch = request.getRequestDispatcher("adController?command=allMember");
				dispatch.forward(request, response);
			} else {
				System.out.println("등급 변경 실패");
				JOptionPane.showMessageDialog(null, "등급 변경 실패");
				RequestDispatcher dispatch = request.getRequestDispatcher("adController?command=allMember");
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
