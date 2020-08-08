package com.agenda.donation;

import java.io.IOException;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.agenda.login.LoginDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/dnController")
public class dnController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 결제 통지 받기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println(command);
		
		if (command.equals("donation")) {
		
		// JSON문자열을 string 변수에 담음 - getParameter의 괄호는 dnpay.js의 62번째 줄 date 이름이랑 같아야 한다.
		String obj = request.getParameter("obj");
		System.out.println(obj);
		
		// dnPay.js의 JSON형태의 결제 결과값들을 파싱
		JsonElement element = JsonParser.parseString(obj);
		
		// JSON element 값을 JSON object 형태로 바꿔서 꺼냄
		JsonObject tmp = element.getAsJsonObject();
		
		// json 형태인 {"key":"value"}에서 key를 호출하기
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
		
		// 세팅
		dnDto dto = new dnDto();
		dto.setDona_bill(dona_bill);
		dto.setDona_date(dona_date);
		
		// dto에 위 값들을 꺼내서 dao(후원내역 조회에 data를 insert할 수 있는 쿼리 있음, 여기에 member_id를 받아서 같이 넣기. selectList 쿼리도 있음.)에 넣기
		dnDao dao = new dnDao();
		
		// db에 넣기
		int dona_db = dao.insert(dto);
		System.out.println(dona_db); //dona_db:1 즉, db에 저장 성공
		
		// db에 있는 값 꺼내서 후원내역 테이블에서 보여주려면
		// db에 저장된 값(dona_db>0)을 dnpay.js의 msg에 응답시켜주기
		response.getWriter().print(dona_db);
		
		}
		
		else if (command.equals("dnlist")) {
		
		// 이후 1. adreceipt.jsp에서 <script>에 ajax 써서 body에 있는 테이블에 값 불러오거나
		// 2. 컨트롤러 통해서 adreceipt.jsp에 뿌리기
			String member_id = request.getParameter("member_id");
		
			dnDao dao = new dnDao();
			
			dnDto dto = new dnDto();
			dto = dao.selectOne(member_id);
			request.setAttribute("dto", dto);
				
			RequestDispatcher dispatch = request.getRequestDispatcher("myinfo/myreceipt.jsp");
			dispatch.forward(request, response);
				
		}
		
		else if (command.equals("alldnlist")) {
			
			dnDao dao = new dnDao();
			
			List <dnDto> list = dao.selectList();
			request.setAttribute("list", list);
				
			RequestDispatcher dispatch = request.getRequestDispatcher("admin/adreceipt.jsp");
			dispatch.forward(request, response);
		}
		
		}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
}
