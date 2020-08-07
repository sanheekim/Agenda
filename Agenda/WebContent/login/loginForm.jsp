<!-- @format -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.security.SecureRandom"%>
<%@ page import="java.math.BigInteger"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="loginForm.css" rel="stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="387206565373-pirulfq47eamrs5ge97dg64f1r8sv7cq.apps.googleusercontent.com">
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
	document
			.addEventListener(
					"DOMContentLoaded",
					function() {
						// @details 카카오톡 Developer API 사이트에서 발급받은 JavaScript Key
						Kakao.init("14450773e72f51b9201cbbec7c53a0a4");
						// @breif 카카오 로그인 버튼을 생성합니다.
						Kakao.Auth
								.createLoginButton({
									container : "#kakao-login-btn",
									success : function(authObj) {
										// console.log( authObj );
										Kakao.API
												.request({
													url : "/v2/user/me",
													success : function(res) {
														console.log(res);
														// @breif 아이디
														document
																.getElementById("kakaoIdentity").innerHTML = res.id;
														// @breif 생일(월,일)
														document
																.getElementById("kakaoemail").innerHTML = res.kakao_account.email;
													},
													fail : function(error) {
														alert(JSON
																.stringify(error));
													}
												});
									},
									fail : function(error) {

										alert(JSON.stringify(error));
									}
								});
					});
</script>

<!-- 구글 로그인 -->
<script>
function checkLoginStatus(){
    var loginBtn = document.querySelector('#loginBtn');
    var nameTxt = document.querySelector('#name');
    //로그인 성공하면
    if(gauth.isSignedIn.get()){
      console.log('logined');
      loginBtn.value = 'Logout';
      var profile = gauth.currentUser.get().getBasicProfile();
      nameTxt.innerHTML = 'Welcome <strong>'+profile.getName()+'</strong> ';
      
		location.href = "googleController.do?command=login&member_id="
				+ profile.getId() + "&member_pw=" + profile.getEmail();
		
    } else { // 실패하면
      console.log('logouted');
      loginBtn.value = 'Login';
      nameTxt.innerHTML = '';
    }
  }
  function init(){
    console.log('init');
    gapi.load('auth2', function() {
      console.log('auth2');
      window.gauth = gapi.auth2.init({
        client_id:'387206565373-pirulfq47eamrs5ge97dg64f1r8sv7cq.apps.googleusercontent.com'
      })
      gauth.then(function(){
        console.log('googleAuth success');
        checkLoginStatus();
      }, function(){
        console.log('googleAuth fail');
      });
    });
  }
  
  
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


			<a href="${pageContext.request.contextPath}/main/main.jsp"><img
				class="login__icon" src="imgs/favicon.png" alt="logo" /></a>

                                                                                                                                                                                       

			<div class="login__title">로그인</div>

		</div>

		<div class=login__bottom>

			<form action="../LoginController" method="post">

				<input type="hidden" name="command" value="login" />

			<div class="login__submit">
					<div class="login__submit__id">

						<input type="text" placeholder="아이디" name="member_id" required />
					</div>

					<div class="login__submit__pwd">

						<input type="password" placeholder="비밀번호" name="member_pw"
							required />
					</div>


				<div class="login__submit__remember">
					<span class="forgotid">
					<a href="${pageContext.request.contextPath}/login/loginForgotId.jsp">아이디</a></span>
					<span class="forgotpw"><a href="#">비밀번호</a>를 잊으셨습니까?</span>
				</div>

				<div>
					<button class="login__submit__button" type="submit" value="loginbtn">로그인</button>
				</div>
					
							
				<div >
					<button class="registbtn" onclick="location.href='${pageContext.request.contextPath}/regist/registForm.jsp'">회원가입</button>
				</div>

			</div>



			</form>

		</div>


		<div class="login__submit__api">

			<div class="login__submit__kakao">
				<a href="#"><img id="kakao-login-btn" src="imgs/카카오버튼.png" /></a>

			</div>
			
			<!-- 구글 로그인 시작-->
<!-- 			<div class="g-signin2" data-width="220" data-height="45"
						data-onsuccess="onSignIn" data-longtitle="true"></div> -->
			<!-- 구글 로그인 끝 -->
			<span id="name"></span><input type="button" id="loginBtn"
				value="checking..." onclick="
				if(this.value === 'Login'){
				 gauth.signIn({
		      }).then(function(){
		        console.log('gauth.signIn()');
		        checkLoginStatus();
		      });
		       } else {
		      gauth.signOut().then(function(){
		        console.log('gauth.signOut()');
		        checkLoginStatus();
		      });
		    }
		  ">

			<!-- 			<div class="login__submit__google">
				<a href="#"><img id=google__btn src="imgs/구글버튼.png" /></a>
			</div> -->
	
			<div class="login__submit__naver">
				<a href="<%=apiURL%>"><img id=naver__btn src="imgs/네아로버튼.png" /></a>
			</div>


		</div>
		<div>
			카카오 아이디 : <span id="kakaoIdentity"></span>
		</div>

		<div>
			메일 : <span id="kakaoemail"></span>
		</div>


		


	</div>
	 <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
</body>
</html>
