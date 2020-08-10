package com.agenda.regist;

import java.io.IOException; 
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.mail.Authenticator;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.Address;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import java.util.Properties;

@WebServlet("/RegistController")
public class RegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public RegistController() {
    	System.out.println("컨트롤러에는 들어온거지?");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); //get으로 와도 포스트로 보내곘다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(); //세션받는거
	    PrintWriter out = response.getWriter();		
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		RegistDao dao = new RegistDao();
		
		if (command.equals("idcheck")) {
			// 1.
			String member_id = request.getParameter("member_id");
			// 2.
			RegistDto dto = dao.idCheck(member_id);
			boolean idnotused = true;
			if (dto != null) {
				idnotused = false;
			}
			// 3.
			// 4.
			response.sendRedirect("regist/idcheck.jsp?idnotused="+idnotused);
		
		}else if (command.equals("registres")){
			// 1.
			String member_id = request.getParameter("member_id");
			String member_salt = Password.generateSalt();
			String member_pw = Password.getEncrypt(request.getParameter("member_pw"), member_salt);
			String member_name = request.getParameter("member_name");
			String member_email = request.getParameter("member_email");
			String member_email_valid = request.getParameter("member_email_valid");
			String member_addr = request.getParameter("member_addr");
			String member_enabled = request.getParameter("member_enabled");
			String member_role = request.getParameter("member_role");
			String member_token = request.getParameter("member_token");
			String member_phone = request.getParameter("member_phone"); 
			
			System.out.println(member_id);
			System.out.println(member_pw);
			System.out.println(member_salt);
			System.out.println(member_name);
			System.out.println(member_email);
			System.out.println(member_email_valid);
			System.out.println(member_addr);
			System.out.println(member_enabled);
			System.out.println(member_role);
			System.out.println(member_token);
			System.out.println(member_phone);
			
			System.out.println(SHA256.getEncryptSaltFixed(member_email));
			System.out.println(member_id);
			
			if(member_email_valid.equals(SHA256.getEncryptSaltFixed(member_email)))
			{
				System.out.println("1");
				//int member_no = Integer.parseInt(request.getParameter("member_no")); registfrom에 no값은 없기때문에 null값이 뜬다.
				
				
				RegistDto dto = new RegistDto(member_id, member_pw, member_name, member_email, member_addr, 
						member_enabled, member_role, member_token, member_salt, member_phone, 0); //no는 null값이기 때문에 0
				System.out.println(dto);
				System.out.println("2");
				// 2.
				boolean res = dao.insertUser(dto);
				System.out.println(res);
				if (res) {	
					
					//out.println("<scipt>alert('회원가입 성공'); location.href='loginMain.jsp';</script>");
					registResponse(member_id + " 님 " + " 아괜다 회원이 되신걸 축하드립니다. 다시 로그인 해주시길 바랍니다.", "main/main.jsp", response);
					//response.sendRedirect("/regist/registForm.jsp");
					
				} else {
					
					//out.println("<scipt>alert('회원가입 실패'); location.href='registForm.jsp';</script>");
					registResponse("다시 회원가입 하세요", "regist/registForm.jsp", response);
					//response.sendRedirect("/header/resources/loginMain.jsp");
				}
			}
			else {
				//이러면 인증 코드 달라서 통과 X
				System.out.println("인증코드가 올바르지 않음");
			}
						
		} else if (command.equals("emailValid")) {
			System.out.println("4-1");
			response.setContentType("application/text");
			String host = "http://localhost:8787/Agenda/RegistController/";		
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
	}
	public void registResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String js = "<script type = 'text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";

		response.getWriter().append(js);
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
}
