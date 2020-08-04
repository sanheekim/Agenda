package com.agenda.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agenda.donation.dnDao;
import com.agenda.donation.dnDto;
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
		
		String command = request.getParameter("command");
		System.out.println(command);
		
		if (command.equals("alldonation")) {
			
			String obj = request.getParameter("obj");
			System.out.println(obj);
			
			JsonElement element = JsonParser.parseString(obj);
			
			JsonObject tmp = element.getAsJsonObject();
			
			int dona_bill = tmp.get("price").getAsInt();
			String dona_date2 = tmp.get("purchased_at").getAsString();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dona_date = null;
			try {
				dona_date = (Date) transFormat.parse(dona_date2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println(dona_bill);
			System.out.println(dona_date);
			
			dnDto dto = new dnDto();
			dto.setDona_bill(dona_bill);
			dto.setDona_date(dona_date);
			
			dnDao dao = new dnDao();
			
			int dona_db = dao.insert(dto);
			System.out.println(dona_db);
			
			response.getWriter().print(dona_db);
			
		} else if (command.equals("alldnlist")) {
			
			// 이후 1. adreceipt.jsp에서 <script>에 ajax 써서 body에 있는 테이블에 값 불러오거나
			// 2. 컨트롤러 통해서 adreceipt.jsp에 뿌리기
			
				dnDao dao = new dnDao();
			
				List <dnDto> list = dao.selectList();
				request.setAttribute("list", list);
					
				RequestDispatcher dispatch = request.getRequestDispatcher("myinfo/myreceipt.jsp");
				dispatch.forward(request, response);
					
			}
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
