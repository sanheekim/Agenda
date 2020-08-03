<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style type="text/css">
#forgotId{
margin-top:20px;
height:100vh;
}

section {
	width: 1000px;
	height: 100%;
	margin: 80px auto;
}

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
<body>
	<jsp:include page="../header/header.jsp" />
<section>
	<div id="fotgotId">
		<div class="fotgotId__top">

			<h2 class="forgotId__title">아이디 찾기</h2>
		</div>




		<div class=forgotId__bottom>

			<form name="idfindscreen" action="../LoginController" method="post" >
			<input type="hidden" name="command" value="forgotId" />

				<div class="forgotId__submit">
				
					<div class="forgotId__submit__id">
					
						<div>아이디는 가입시 입력하신 이름과 이메일을 통해 찾을 수 있습니다</div>
						<input type="text" placeholder="이름을 입력하세요" name="member_name"
							required />
					</div>

					<div class="forgotId__submit__email">
					
						<div>회원정보에 등록한 이메일</div>
						<input type="email"  placeholder="이메일을 입력하세요" name="member_email" required>
						
					</div>


					<div class="forgotId__submit__button">
						<button type="submit" value="forgotIdbtn"  onclick="idSearch()">아이디찾기</button>
						
					</div>

				</div>

			</form>

			<div></div>

		</div>


	</div>
	</section>
	<jsp:include page="../footer/mainFooter.jsp" />
</body>
</html>