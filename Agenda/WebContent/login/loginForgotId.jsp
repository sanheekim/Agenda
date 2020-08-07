<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style type="text/css">

</style>
<script>
function idSearch(){

	 var frm = document.idfindscreen;
	  if (frm.findname.value.length < 1) {
	   alert("이름을 입력해주세요");
	   return;
	  }
	  
	  if (frm.findemail.value.length < 1) {
	   alert("이메일을 입력해주세요");
	   return;
	  }

	/*   frm.method = "post";
	  frm.action = "${pageContext.request.contextPath}/login/loginSearchId.jsp"; //넘어간화면
	  frm.submit();  //post방식으로 넘긴다. */

}

</script>
</head>
<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/login/loginForgotId.css">
<body>
	<jsp:include page="../header/header.jsp" />
<section class= first >
	<div id="fotgotId">
	
	
		<div class="fotgotId__top">

			<h2 class="forgotId__title">아이디 찾기</h2>
			
			
		</div>




		<div class=forgotId__bottom>

			<form name="idfindscreen" action="../LoginController" method="post" >
			<input type="hidden" name="command" value="forgotId" />

				<div class="forgotId__submit">
				
					
					
						<div class="forgotId__sub__title">가입시 입력하신 이름과 이메일을 통해 찾을 수 있습니다</div>
						
						<div class="forgotId__submit__name">
						<div class="name__title"> 회원정보에 등록한 이름</div>
						<input type="text" placeholder="이름을 입력하세요" name="member_name"
							required />
						</div>

					<div class="forgotId__submit__email">
					
						<div class="email__title">회원정보에 등록한 이메일</div>
						<input type="email"  placeholder="이메일을 입력하세요" name="member_email" required>
						
					</div>

					<div>
					<div>
						<button class="forgotId__submit__button" type="submit" value="forgotIdbtn"  onclick="idSearch()">아이디찾기</button>
						
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