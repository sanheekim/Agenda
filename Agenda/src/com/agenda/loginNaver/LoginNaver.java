package com.agenda.loginNaver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/LoginNaver")
public class LoginNaver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginNaver() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet첫 줄");
		// 세션얻기
		HttpSession session = request.getSession();

		String clientId = "aKYqnvW_wCpjcTsDuFRA";
		String clientSecret = "AbpHHWfTB9";
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("http://127.0.0.1:8787/Agenda/LoginNaver", "UTF-8");

		StringBuffer apiURL = new StringBuffer();
		apiURL.append("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&");
		apiURL.append("client_id=" + clientId);
		apiURL.append("&client_secret=" + clientSecret);
		apiURL.append("&redirect_uri=" + redirectURI);
		apiURL.append("&code=" + code);
		apiURL.append("&state=" + state);
		String access_token = "";
		String refresh_token = ""; // 나중에 이용
		System.out.println("token받기전");
		
		try {
			URL url = new URL(apiURL.toString());
			// URL url = new URL (apiURL);
			// 예제에서 URL url = new URL (apiURL); 로 나와있었음
			// The constructor URL(StringBuffer) is undefined
			// changeType of'apiURL' to 'String' 으로 에러뜸
			// 임의로 stringbuffer를 string으로 형변환시켜줌 -> 물어보장

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);

			if (responseCode == 200) { // 정상 호출

				br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			} else { // 에러 발생

				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));

			}

			String inputLine;
			StringBuffer res = new StringBuffer();

			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}

			br.close();

			if (responseCode == 200) {

				System.out.println(res.toString());
				JSONParser parsing = new JSONParser();

				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject resObj = (JSONObject)jsonObj.get("response");

				access_token = (String) jsonObj.get("access_token");
				refresh_token = (String) jsonObj.get("refresh_token");

				System.out.println("access_token값+++ :" +access_token);
				System.out.println("refresh_token값+++"+refresh_token);
				System.out.println("token");
		
				session.setAttribute("access_token", access_token);
				session.setAttribute("refresh_token", refresh_token);

				response.sendRedirect("LoginNaverInfo.do");
			}
			
			if(access_token!=null) {
				//token이 null값이 아니라면 성공한거니까 이제 값을 받아오면됨
				try {
				
				 System.out.println("access_token is not null!이면 밑에 줄 실행시켜라");


				}catch (Exception e) {
					System.out.println(e);
				}
			}
			

			
		} catch (Exception e) {
			System.out.println(e);
		}
		// dto add dto 에 담기
		// session add session 에 담기
		// string token header add
		// dispatch-> class study smaple 수업시간 보고 디스패치 하고
		// dispatch -> naver -> after login
		// 디스패치하고 네이버 ? 로그인 후 화면으로 보내주기

	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
