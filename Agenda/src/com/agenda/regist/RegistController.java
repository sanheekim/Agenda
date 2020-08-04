package com.agenda.regist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

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
		System.out.println("설마 여기가 문제는 아니겠지;;;");
//		if (command.equals("login")) {
//			String myid = request.getParameter("myid");
//			String mypw = request.getParameter("mypw");
//			RegistDto dto = dao.login(myid, mypw);
			
//			if (dto != null) {
//				// session scope에 담기!
//				session.setAttribute("dto", dto);
//				// session 유효 시간 : 10분동안 활동이 없으면 invalidate
//				// default : 30분 / 음수일 때 무제한 
//				session.setMaxInactiveInterval(10*60);
//				
//				if (dto.getMyrole().equals("ADMIN")) {
//					response.sendRedirect("adminmain.jsp");
//				} else if (dto.getMyrole().equals("USER")) {
//					response.sendRedirect("usermain.jsp");
//				}
//			} else {				
//				out.println("<script>alert('login 실패'); location.href='index.jsp';</script>");
//			}
//		}
		
		if (command.equals("registres")){
			// 1.
			System.out.println("여기까지는 왔으면 좋겠는데");
			String member_id = request.getParameter("member_id");
			String member_pw = request.getParameter("member_pw");
			String member_name = request.getParameter("member_name");
			String member_email = request.getParameter("member_email");
			String member_email_valid = request.getParameter("member_email_valid");
			String member_addr = request.getParameter("member_addr");
			String member_enabled = request.getParameter("member_enabled");
			String member_role = request.getParameter("member_role");
			String member_token = request.getParameter("member_token");
			String member_salt = request.getParameter("member_salt");
			String member_phone = request.getParameter("member_phone"); 
			
			System.out.println(member_id);
			System.out.println(member_pw);
			System.out.println(member_name);
			System.out.println(member_email);
			System.out.println(member_email_valid);
			System.out.println(member_addr);
			System.out.println(member_enabled);
			System.out.println(member_role);
			System.out.println(member_token);
			System.out.println(member_salt);
			System.out.println(member_phone);
			
			System.out.println(SHA256.getEncryptSaltFixed(member_email));
			System.out.println(member_id);
			
			
			
			if(member_email_valid.equals(SHA256.getEncryptSaltFixed(member_email)))
			{
				System.out.println("여기가 안올거같은데?");
				//이러면 통과
				//int member_no = Integer.parseInt(request.getParameter("member_no")); registfrom에 no값은 없기때문에 null값이 뜬다.
				
				
				RegistDto dto = new RegistDto(member_id, member_pw, member_name, member_email, member_addr, 
						member_enabled, member_role, member_token, member_salt, member_phone, 0); //no는 null값이기 때문에 0
				System.out.println(dto);
				System.out.println("여기까지 오면 성공일듯");
				// 2.
				boolean res = dao.insertUser(dto);
				System.out.println(res);
				if (res) {	
					
					//out.println("<scipt>alert('회원가입 성공'); location.href='loginMain.jsp';</script>");
					registResponse("로그인 되었습니다.", "main/main.jsp", response);
					//response.sendRedirect("/regist/registForm.jsp");
					
				} else {
					
					//out.println("<scipt>alert('회원가입 실패'); location.href='registForm.jsp';</script>");
					registResponse("다시 회원가입 하세요", "regist/registForm.jsp", response);
					//response.sendRedirect("/header/resources/loginMain.jsp");
				}
			}
			else {
				//이러면 인증 코드 달라서 통과 X
				System.out.println("설마 일로 들어오냐");
			}
		} else if (command.equals("emailValid")) {
			System.out.println("여기도 오지?");
			response.setContentType("application/text");
			// Get the printwriter object from response to write the required json object to the output stream      
			// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
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
			System.out.println("여기도 오지? 2 ");
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
				System.out.println("여기도 오지? 3-1 ");
			} catch (Exception e) {
				System.out.println(e);
				String msgs = "메일 주소를 확인 해 주세요";
				out.print(msgs);
				out.flush();
				System.out.println("여기도 오지? 3-2 ");
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
