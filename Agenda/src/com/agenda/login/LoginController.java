package com.agenda.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.agenda.regist.Gmail;
import com.agenda.regist.Password;
import com.agenda.regist.SHA256;



@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		System.out.println("컨트롤러로 넘어옴");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		LoginDao dao = new LoginDao();
		LoginDto dto = new LoginDto();

		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		// 로그인페이지
		if (command.equals("login")) {

			String member_id = request.getParameter("member_id");
			dto = dao.idsalt(member_id);
			String member_pw = Password.getEncrypt(request.getParameter("member_pw"),dto.getMember_salt());
			String member_enabled = request.getParameter("member_enabeld");	
			LoginDto input = new LoginDto(member_id, member_pw, member_enabled);
			
			dto = dao.login(input);

			if (dto != null) {

				session.setAttribute("logindto", dto);
				session.setMaxInactiveInterval(10 * 60);
				// User, Admin 구분하지 않고 바로 main으로 보내기
				RequestDispatcher dispatch = request.getRequestDispatcher("main/main.jsp");
				dispatch.forward(request, response);


			} else {
				System.out.println("dto is null : login form 으로 다시 보냄");
				loginResponse("아이디 또는 비밀번호가 틀렸습니다", "login/loginForm.jsp", response);

			} 

		}

		else if (command.equals("logout")) {

			// session 객체가 가진 값 삭제
			loginResponse("로그아웃 되셨습니다!", "main/main.jsp", response);
			session.invalidate();


		}

		else if (command.equals("forgotId")) {

			System.out.println(">> forgotId Controller ");
			String member_name = request.getParameter("member_name");
			String member_email = request.getParameter("member_email");

			LoginDto FindDto = new LoginDto();
			FindDto.setMember_name(member_name);
			FindDto.setMember_email(member_email);

			dto = dao.findid(FindDto);
			
		
			if (dto != null) {
				String member_id = dto.getMember_id();
				request.setAttribute("member_id", member_id);
				RequestDispatcher dispatch = request.getRequestDispatcher("login/loginSearchId.jsp");
				dispatch.forward(request, response);

			} else {

				loginResponse("찾으시는 아이디가 존재하지 않습니다.","login/loginForgotId.jsp",response);
				
			}
			
		}
		
		else if (command.equals("forgotPw")) {
			
			System.out.println("forgotPw Controller진입.");
			String member_name = request.getParameter("member_name");
			String member_email = request.getParameter("member_email");
			String member_email_valid = request.getParameter("member_email_valid");
					
			if(member_email_valid.equals(SHA256.getEncryptSaltFixed(member_email)))
			{
				System.out.println("<");
				System.out.println(SHA256.getEncryptSaltFixed(member_email));
				System.out.println(",");
				System.out.println(member_email_valid);
				System.out.println(">");
				LoginDto FindDto = new LoginDto();
				FindDto.setMember_name(member_name);
				FindDto.setMember_email(member_email);

				dto = dao.findid(FindDto);
				
				if (dto != null) {
					
					String member_id = dto.getMember_id();
					request.setAttribute("member_id",member_id);
					loginResponse("인증번호가 일치합니다. 비밀번호를 재설정 하세요.","login/loginTemp.jsp",response);
			
				} else {
		
					loginResponse("찾으시는 아이디가 존재하지 않습니다.","regist/registForm.jsp",response);					
				}
			}
			else {
				// 인증 코드 달라서 통과 X
				loginResponse("잘못된 인증번호 입니다. 다시 입력해주세요.","login/loginForgotPw.jsp",response);
			}
			
		} else if (command.equals("emailValid")) {
			System.out.println("4-1");
			response.setContentType("application/text");
			String host = "http://localhost:8787/Agenda/LoginController/";		
			String from = "rldndvxt1122@gmail.com";		//보내는사람
			String to = request.getParameter("email");
			String code = SHA256.getEncryptSaltFixed(to);

			//사용자에게 보낼 메시지
			String subject = "회원가입을 위한 이메일 인증 메일입니다.";
			String content = "인증코드 : "+code;

			Properties p = new Properties();
			p.put("mail.smtp.user", from);
			p.put("mail.smtp.host", "smtp.googlemail.com");
			p.put("mail.smtp.port", "465"); //TLS 587, SSL 465
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465"); 
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.sockerFactory.fallback", "false");
			System.out.println("4-2");
			try {
				Authenticator auth = new Gmail();
				Session ses = Session.getInstance(p, auth);
				ses.setDebug(true);
				MimeMessage msg = new MimeMessage(ses);
				msg.setSubject(subject);
				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr);
				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO, toAddr);
				msg.setContent(content, "text/html; charset=UTF8");
				Transport.send(msg);
				String msgs = "메일 발송을 성공했습니다";
				out.print(msgs);
				out.flush();
				System.out.println("4-3");
			} catch (Exception e) {
				System.out.println(e);
				String msgs = "메일 주소를 확인 해 주세요";
				out.print(msgs);
				out.flush();
				System.out.println("4-4");
			} 
		} 

		 else if (command.equals("kakaologin")) {
			 
			 String member_id = request.getParameter("member_id");
			 String member_email = request.getParameter("member_email");
			 String member_enabled = request.getParameter("member_enabled");
			 LoginDto idemail = new LoginDto(member_id, member_email,member_enabled);
			 dto = dao.kakaologin(idemail);
			 
			 System.out.println("loginController: kakao login 확인");
			 System.out.println(">>kakao:"+member_id);
			 System.out.println(">>kakao:"+member_email);
			 System.out.println(">>kakao enabled "+ member_enabled);
			 
			System.out.println("dto=" + dto);
			
			 if(dto != null ) {
				 
				 	dto.setMember_id(member_id);
				 	dto.setMember_email(member_email);
				 	dto.setMember_enabled(member_enabled);
				 	session.setAttribute("logindto", dto);
					session.setMaxInactiveInterval(10 * 60);
					loginResponse("카카오계정으로 로그인하셨습니다 .","main/main.jsp",response);
					System.out.println("DTO 있음  = mypage main으로 보냄");
					
				
			 
			 } else {
				 
				 	session.setAttribute("member_id", member_id);
				 	session.setAttribute("member_email", member_email);
					RequestDispatcher dispatch = request.getRequestDispatcher("/regist/KakaoRegistForm.jsp");    
					dispatch.forward(request, response);				 
			 }
				
		 }
		 

	}

	public void loginResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String js = "<script type = 'text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";

		response.getWriter().append(js);
	}



}
