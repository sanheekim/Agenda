<!-- @format -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link href="loginForm.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">

		
	</script>
    <title>Document</title>
  </head>
  <body>
  
    <%
    String clientId = "aKYqnvW_wCpjcTsDuFRA";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://127.0.0.1:8787/Agenda/LoginNaver", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
   	System.out.println("로그인페이지");
 %>
 
  
    <div class="login">

     <div class="login__top">

      
          <img class="login__icon" src="login/imgs/favicon.png" alt="logo"  href="" />
          
          
        <div class="login__title">로그인</div>
  
    </div>

    <div class=login__bottom>
    
			<form action="../LoginController" method="post">
			
				<input type="hidden" name="command" value="login" />
				
				<div class="login__submit">
					<div class="login__submit__id">
					<!-- 	<label for="username"></label> --> <input type="text" placeholder="아이디" name="member_id" required />
					</div>
					
					<div class="login__submit__pwd">
						<!-- <label for="password"></label> -->
						<input type="password" placeholder="비밀번호" name="member_pw" required />
					</div>
					
					
					<div class="login__submit__remember">
						<label for=""> <input type="checkbox" checked="checked"
							name="remember" /> 로그인 유지하기
						</label> <span class="forgotpw"><a href="#">비밀번호</a>를 잊으셨습니까?</a></span>
					</div>
					
					<div class="login__submit__button">
						<button type="submit" value="loginbtn">로그인</button>
					</div>

				</div>
				
							
			
			</form>

		</div>



      <div class="login__submit__api">
      
        <div class="login__submit__kakao">
             <a href="#"><img id=kakao__btn  src="imgs/카카오버튼.png"/></a>
         </div>
         
          <div class="login__submit__google">
           		<a href="#"><img id=google__btn  src="imgs/구글버튼.png"/></a>
           </div>
         
            <div class="login__submit__naver">
              	<a href="<%=apiURL%>"><img id=naver__btn  src="imgs/네아로버튼.png"/></a>
            </div>
            
            
        </div>
      </div>

  </body>
</html>
