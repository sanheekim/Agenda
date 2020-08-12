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
	
		// 세션얻기
		HttpSession session = request.getSession();
		// cliendIt, clientSectet 등록한 apllication에서 제공받은 것을 기입.
		String clientId = "aKYqnvW_wCpjcTsDuFRA";
		String clientSecret = "AbpHHWfTB9";
		
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		
		// 네이버 로그인에 기입한 call back URI 기입.
				//(네이버는 localhost제공 하지않음,127.0.0.1사용).
		String redirectURI = URLEncoder.encode("http://127.0.0.1:8787/Agenda/LoginNaver", "UTF-8");


		StringBuffer apiURL = new StringBuffer();
		apiURL.append("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&");
		apiURL.append("client_id=" + clientId);
		apiURL.append("&client_secret=" + clientSecret);
		apiURL.append("&redirect_uri=" + redirectURI);
		apiURL.append("&code=" + code);
		apiURL.append("&state=" + state);
		// access_tokne, refresh_token은 나중에 이용.
		String access_token = "";
		String refresh_token = ""; 
	
		
		try {
			
			URL url = new URL(apiURL.toString());
			//검색에서는 URL url = new URL(apiURL);로 쓰는경우가 있음
			//경우에따라 형 변환 시켜서 쓰기.
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			//만든 callback URI에 맞춰서 정상 처리 되면 access_token, refresh_token을 넘겨줌.
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

				//access_token,refresh_token을 정상적으로 받아오면
				//session에 담아서 회원정보조회API에 넘겨줌
				//네이버 로그인은 성공 -> 회원조회를 위해 회원조회 API로 넘겨줌
		
				session.setAttribute("access_token", access_token);
				session.setAttribute("refresh_token", refresh_token);

				response.sendRedirect("LoginNaverInfo.do");
			}
			
			if(access_token!=null) {
				
				try {
				
				 System.out.println(">> access_token is not null");


				}catch (Exception e) {
					System.out.println(e);
				}
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
