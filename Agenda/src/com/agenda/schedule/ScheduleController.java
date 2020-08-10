package com.agenda.schedule;

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

import com.agenda.schedule.ScheduleDao;

@WebServlet("/ScheduleController")
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ScheduleController() {
   
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
		ScheduleDao dao = new ScheduleDao();
		
		if(command.equals("insert")) {
	
		String member_id = request.getParameter("member_id");
		String sche_time = request.getParameter("sche_time");
		String sche_content = request.getParameter("sche_content");
		
		/*RequestDispatcher dispatch = request.getRequestDispatcher("schedule/Alrim.jsp");
		dispatch.forward(request, response);*/
		
		//json형태로 바꿔준다 {"key":"value"}
		//json-simple-1.1.jar
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sche_time", sche_time);
		map.put("sche_content", sche_content);
		map.put("member_id", member_id);
		
		request.setAttribute("map", map);
		int res = dao.insert(map);
		
		System.out.println("입력몇개?" + res);
		
		//응답
		}
	
		else if(command.equals("Alrim")) {
			String member_id = request.getParameter("member_id");
			System.out.println( "  :  " + member_id);
			
			ScheduleDto dto = new ScheduleDto();
			dto = new ScheduleDto(member_id);
			
			List<String> list = dao.selectList(dto);
			
			
			System.out.println(list.toString());
			//String[] string = list.toArray(new String[list.size()]);
			session.setAttribute("list", list);
			
				
		
			
			
			//dispatch("schedule/Alrim.jsp",request,response);
			
			
			
			
			
			
			
		}
		


		
		
		
	}
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
