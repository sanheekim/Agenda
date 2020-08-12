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

		System.out.println(">> login naver info controller 진입.");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//넘어온 session을 받아줌
		//받아온 access_token을 token으로 형변환시켜주면서 header에 붙여줌
		//이 header에 붙은 token값으로 정보를 받아오는것이기때문.* "Bearer "공백있어야함
		HttpSession session = request.getSession();
		String token = (String)session.getAttribute("access_token");	
		String header = "Bearer " + token;


		try {
			
			
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();			
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

			JSONParser parser = new JSONParser();
			JSONObject result = (JSONObject)parser.parse(resp.toString());

			
			
			
			String name = (String) ((JSONObject)result.get("response")).get("name");
			String email = (String)((JSONObject)result.get("response")).get("email");
			String id = (String)((JSONObject)result.get("response")).get("id");
			
			//session에 담아 회원가입으로 보내줌
			//이때 회원가입시 sns별로 naver, kakao분리해서 db에 저장해주는것이 좋음.
			//ex) column으로 분리 member_social ="naver" 
			//해당 프로젝트에선 그냥 가입시켰음
			String command = "NaverRegist";
			session.setAttribute("member_id", id);
			session.setAttribute("member_email", email);
			session.setAttribute("member_name", name);
			session.setAttribute("command", command);
			
			
			
			if(session != null) {
				
				System.out.println("session != null ");   
				RequestDispatcher dispatch = request.getRequestDispatcher("/regist/NaverRegistForm.jsp");    
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
