package com.agenda.loginNaver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
			
			
			System.out.println("resp값"+resp.toString());
			
			
			JSONParser parser = new JSONParser();
			JSONObject result = (JSONObject)parser.parse(resp.toString());
			
			
			System.out.println("result값+++" + result);
			
			
			((JSONObject)result.get("resp")).get("email");
			
			
			String email = (String)((JSONObject)result.get("resp")).get("email");
			String name = (String)((JSONObject)result.get("resp")).get("name");
			System.out.println(email);
			

			
			System.out.println("email : " + email);
			System.out.println("name  : " + name);			
			System.out.println(response.toString());
			
			session.setAttribute("id", name);
			session.setAttribute("email", email);
			session.setAttribute("name", name);
			
			
			

			//resultMap을 만들어서 dto에 email 이랑 name alias를 만들기 뮬어보기
			
			
			
			
		} catch (Exception e) {

			System.out.println(e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
