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
		
		dnDao dao = new dnDao();
		
		String command = request.getParameter("command");
		System.out.println(command);
		
		if (command.equals("getid01")) {
			
			String member_id = request.getParameter("member_id");
			System.out.println(member_id);
			response.sendRedirect("main/mainpay1.js?member_id="+request.getParameter("member_id"));
			
		}
		
		else if (command.equals("donation")) {
		
		// mainpay.js에서 보낸 member_id를 받음
		String member_id = request.getParameter("member_id");
		System.out.println("도네이션 컨트롤러 : " + member_id);
		
		// mainpay.js에서 보낸 obj(JSON문자열)를 받아서 string 변수에 담음
		String obj = request.getParameter("obj");
		System.out.println(obj);
		
		// mainpay.js의 JSON형태의 결제 결과값들을 파싱
		JsonElement element = JsonParser.parseString(obj);
		
		// JSON element 값을 JSON object 형태로 바꿔서 꺼냄
		JsonObject tmp = element.getAsJsonObject();
		
		// json 형태인 {"key":"value"}에서 key("price", "pruchased_at")를 호출한 뒤 변수에 담아줌
		int dona_bill = tmp.get("price").getAsInt();
		String dona_date2 = tmp.get("purchased_at").getAsString();
		
		// String형태의 dano_date2라는 변수를 Date로 형변환
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dona_date = null;
		try {
			dona_date = (Date) transFormat.parse(dona_date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(dona_bill);
		System.out.println(dona_date);
		
		// dto에 세팅
		dnDto dto = new dnDto();
		dto.setDona_bill(dona_bill);
		dto.setDona_date(dona_date);
		dto.setMember_id(member_id);
		
		// db에 넣기
		int dona_db = dao.insert(dto);
		System.out.println(dona_db); //dona_db:1 즉, db에 저장 성공
		
		// db에 있는 값 꺼내서 후원내역 테이블에서 보여주려면
		// db에 저장된 값(dona_db>0)을 mainpay.js의 msg에 응답 시키기
		// 아래 코드를 통해서 다시 mainpay.js ajax의 success로 돌아감
		response.getWriter().print(dona_db);
		
		}
		
		// 개인 후원내역조회 - 메인페이지에서 후원하고 나면 결제창 꺼지면서 자동으로 바로 넘어감
		else if (command.equals("dnlist")) {
			
			String member_id = request.getParameter("member_id");
			System.out.println("리스트 아이디 : " + member_id);
		
			List<dnDto> list = dao.selectOne(member_id);
			request.setAttribute("donationList", list);
			System.out.println(list);
				
			RequestDispatcher dispatch = request.getRequestDispatcher("myinfo/myreceipt.jsp");
			dispatch.forward(request, response);
				
		}
		
		// 전체 후원내역 조회 - 관리자 계정으로 로그인해서 관리자페이지에서만 볼 수 있음
		else if (command.equals("alldnlist")) {
			
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
