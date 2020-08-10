<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style type="text/css">

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function idSearch(){

	 var frm = document.idfindscreen;
	  if (frm.member_name.value.length < 1) {
	   alert("이름을 입력해주세요");
	   return;
	  }
	  
	  if (frm.member_email.value.length < 1) {
	   alert("이메일을 입력해주세요");
	   return;
	  }
	  
	  if (frm.member_email_valid.length < 1) {
		  alert("인증번호를 입력해주세요");
		  return;
	  }

	/*   frm.method = "post";
	  frm.action = "${pageContext.request.contextPath}/login/loginSearchId.jsp"; //넘어간화면
	  frm.submit();  //post방식으로 넘긴다. */

}

function sendEmail() {
	var email = document.getElementById("email").value
	$.ajax({
		url: "/Agenda/LoginController?command=emailValid&email="+email,
		type: "POST",
		success : function(data){
			alert(data);
		},
		error : function(error){
			alert("서버 에러")
		}
	});
}
</script>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/login/loginForgotPw.css">
<body>
	<jsp:include page="../header/header.jsp" />
<section class= "first" >
	<div id="forgotPw">
	
	
		<div class="forgotId__top">
			<h2 class="forgotPw__title">비밀번호 찾기</h2>		
		</div>

		<div class=forgotPw__bottom>

			<form name="idfindscreen" action="../LoginController" method="post" >
			<input type="hidden" name="command" value="forgotPw" />

				<div class="forgotPw__submit">
		
					<div class="forgotPw__sub__title">가입시 입력하신 이름과 이메일을 통해 인증번호를 보내드립니다</div>
						
					<div class="forgotPw__submit__name">					
						<div class="name__title"> 회원정보에 등록한 이름</div>
						<input type="text" placeholder="이름을 입력하세요" name="member_name" required />				
					</div>

					<div class="forgotPw__submit__id">			
						<div class="email__title">회원정보에 등록한 아이디</div>
						<input type="text"  placeholder="아이디를 입력하세요" name="member_id" required>	
					</div>
					
					<div class="forgotPw__submit__email">
						<div class="email__title">회원정보에 등록한 이메일</div>
						<input type="email"  placeholder="이메일을 입력하세요" name="member_email" id="email" required>		
					</div>
					
					<div>
						<div>
						<button class="forgotPw__submit__button" type="button" value="forgotPwbtn" onclick="sendEmail()">인증번호 발송</button>
						</div>
					</div>
					
					<br>
					<div class="forgotPw_submit_email_valid">
						<div class="email_valid_title">인증번호 입력</div>
						<input type="text" id="code_text" placeholder="인증번호 입력하세요" name="member_email_valid" required>
					</div>
			
					<div>
						<div>
				 		<button class="forgotPw__submit__button" type="submit" value="forgotPwbtn"  onclick="idSearch()">인증번호 확인</button>		  				 		
						</div>
					</div>
				</div>

			</form>

		</div>

	</div>
	</section>
	<jsp:include page="../footer/mainFooter.jsp" />
	
	

	
</body>
</html>