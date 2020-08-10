package com.agenda.alrim;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.agenda.alrim.AlrimDao;

@WebServlet("/AlrimController")
public class AlrimController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AlrimController() {
   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		ScheduleDao dao = new ScheduleDao();
		String command = request.getParameter("command");
		System.out.println("command :" + command);
		
		if(command.equals("insert")) {
			String mdate = request.getParameter("mdate");
			String time = request.getParameter("time");
			String content = request.getParameter("content");
			
			ScheduleDto dto = new ScheduleDto();
			dto.setSche_mdate(mdate);
			dto.setSche_content(content);
			dto.set */
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String command = request.getParameter("command");
		System.out.println("command : " + command);
		AlrimDao dao = new AlrimDao();
		
		
		if(command.equals("Alrimlist")) {
			String member_id = request.getParameter("member_id");
			System.out.println( "  :  " + member_id);
			
			AlrimDto dto = new AlrimDto();
			dto = new AlrimDto(member_id);
			
			List<String> list = dao.selectList(dto);
			
			
			System.out.println(list.toString());
			//String[] string = list.toArray(new String[list.size()]);
			session.setAttribute("list", list);
			
				
		
			
			
			dispatch("schedule/AlrimList.jsp",request,response);
			
			
			}
		else if(command.equals("delete")) {
			int sche_no = Integer.parseInt(request.getParameter("sche_no"));
			String member_id = request.getParameter("member_id");
			
			
			int res = dao.delete(sche_no);
			System.out.println(res);
			
			if(res > 0) {
				dispatch("AlrimController?command=Alrimlist&member_id="+member_id,request,response);
			}
			else {
				loginResponse("삭제에 실패하셨습니다.","schedule/AlrimList",response);
			}
		}
		
		
		


		
		
		
	}
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	public void loginResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String js = "<script type = 'text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";

		response.getWriter().append(js);
	}

}
