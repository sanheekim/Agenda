<!-- @format -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
    <div class="login">

     <div class="login__top">

      
          <img class="login__icon" src="/imgs/favicon.png" alt="logo"  href="" />
          
          
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
          <button type="submit">카카오계정으로 로그인</button></div>
         
          <div class="login__submit__google">
            <button type="submit">구글 계정으로 로그인</button></div>
         
            <div class="login__submit__naver">
              <button type="submit">네이버계정으로 로그인</button></div>
              </div>
      </div>

  </body>
</html>
