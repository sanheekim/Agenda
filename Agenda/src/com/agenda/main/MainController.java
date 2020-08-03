package com.agenda.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainController() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		if(command.equals("mediSearch")) {
			response.sendRedirect("medisearch/mediSearch.jsp");
		}else if(command.equals("mediLocker")) {
			
		}else if(command.equals("findPharm")) {
			response.sendRedirect("pharmacy_map/pharmacy_map.jsp");
		}else if(command.equals("notice")){
			
		}else if(command.equals("qna")) {
			response.sendRedirect("qna/index.jsp");
		}
	}

}
