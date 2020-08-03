package com.agenda.medisearch;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mediSearch")
public class MediSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MediSearchController() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command ="";
		command = request.getParameter("command");
		System.out.println("[" + command + "]");
		String key = "BfXgu8Nrg94kP5UMKtnT32kMX6AUp1kzvIOupvhUowIXqupdnwrP0XSpWIeXNo1zQ%2BYvNT7NAEWM%2BL5P5E3Shw%3D%3D";
		String item = request.getParameter("item");
		String encodeItem =  "&" + URLEncoder.encode("item_name","UTF-8") + "=" + URLEncoder.encode(item, "UTF-8");
		System.out.println(encodeItem);
		System.out.println(item);
		
		if(command.equals("predInfo")) {
			request.setAttribute("key", key);
			request.setAttribute("item", encodeItem);
			dispatch(request, response, "medisearch/mediDetailInfo.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);	
	}

}
