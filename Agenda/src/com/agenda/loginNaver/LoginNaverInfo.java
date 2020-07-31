package com.agenda.loginNaver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/LoginNaverInfo.do")
public class LoginNaverInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginNaverInfo() {

		System.out.println("--------------loginNaverInfo로 넘어옴---------------");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		System.out.println("----session 값 +++"+session);
		String token = (String)session.getAttribute("access_token");
		System.out.println("token 값 +++:"+token);
		String header = "Bearer " + token;
		
		
		System.out.println("header 값 +++"+header);

		try {
			
			
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			
			
			
			
			int responseCode = con.getResponseCode();
			System.out.println("responseCode 값 +++" +responseCode);
			
			BufferedReader br;
			
			
			

			if (responseCode == 200) { //정상호출
				
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
			} else { //에러발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer resp  = new StringBuffer();
			
			while((inputLine = br.readLine())!=null){
				resp.append(inputLine);
			}
			br.close();
			
			
			System.out.println("resp값="+resp.toString());
			JSONParser parser = new JSONParser();
			JSONObject result = (JSONObject)parser.parse(resp.toString());
			System.out.println("result값=" + result);
			
			
			
			String name = (String) ((JSONObject)result.get("response")).get("name");
			System.out.println(name);
			
			String email = (String)((JSONObject)result.get("response")).get("email");
			System.out.println(email);
			
			String id = (String)((JSONObject)result.get("response")).get("id");
			System.out.println(id);
		
			
			session.setAttribute("member_id", name);
			session.setAttribute("member_email", email);
			session.setAttribute("member_name", name);
			
			System.out.println(session);
			
			if(session != null) {
				
				System.out.println("session이 null이 아니면 넘어가라 ");
				RequestDispatcher dispatch = request.getRequestDispatcher("regist/registForm.jsp");
				dispatch.forward(request, response);		
			}
			
			
		} catch (Exception e) {

		
			System.out.println(e);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
