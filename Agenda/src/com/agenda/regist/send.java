package com.agenda.regist;
import java.io.IOException; 
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/send")
public class send extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Properties props = System.getProperties();
			props.put("mail.smtp.user", "rldndvxt1122");	//보낼 구글아이디
			props.put("mail.smtp.host", "smtp.gmail.com");	//구글 STMP
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			Authenticator auth = new MyAuthentication();
			
			Session session = Session.getDefaultInstance(props, auth);
			MimeMessage msg = new MimeMessage(session);
			
			try {
				msg.setSentDate(new Date());
				
				InternetAddress from = new InternetAddress("rldndvxt1122@gmail.com");
				
				// 이메일 발신자
				msg.setFrom(from);
				
				// 이메일 수신자
				String email = request.getParameter("rldndvxt1122@gmail.com"); //사용자가 입력한 이메일 받아오기
				InternetAddress to = new InternetAddress(email);
				msg.setRecipient(Message.RecipientType.TO, to);
				
				// 이메일 제목
				msg.setSubject("비밀번호 인증번호", "UTF-8");
				
				// 이메일 내용
				String code = request.getParameter("code_check"); //인증번호 값 받기
				request.setAttribute("code", code);
				msg.setText(code, "UTF-8");
				
				//이메일 헤더
				msg.setHeader("content-Type", "text/html");
				
				// 메일보내기
				javax.mail.Transport.send(msg);
				System.out.println("보냄!");
				
			} catch (AddressException addr_e) {
				addr_e.printStackTrace();
			} catch (MessagingException msg_e) {
				msg_e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("인증번호 확인하는 페이지");
			rd.forward(request, response);
	}

	class MyAuthentication extends Authenticator {
		
		PasswordAuthentication pa;
		
		public MyAuthentication() {
			
			String id = "rldndvxt1122";		//구글 아이디
			String pw = "qkr941122!";		//구글 비밀번호
			
			pa = new PasswordAuthentication(id, pw);
		}
		
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		} 
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	public void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>" + 
					"alert('" + msg + "');" +
					"location.href='" + url + "';" +
					"</script>";
		
		response.getWriter().append(s);
		 
	}
}	
	


