<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style type="text/css">
</style>
<script>
	function idSearch() {

		var frm = document.idfindscreen;
		if (frm.TempPw.value.length < 1) {
			alert("인증번호를 입력해주세요");
			return;
		}

		if (frm.Change_Pw.value.length < 1) {
			alert("변경할 비밀번호를 입력해주세요");
			return;
		}

		if (frm.Change_Pw_Re.value.length < 1) {
			alert("변경할 비밀번호를 다시 입력해주세요");
			return;
		}

		/*   frm.method = "post";
		  frm.action = "${pageContext.request.contextPath}/login/loginSearchId.jsp"; //넘어간화면
		  frm.submit();  //post방식으로 넘긴다. */

	}
</script>
</head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/login/loginTemp.css">
<body>
	<jsp:include page="../header/header.jsp" />
	<section class="first">
		<div id="TempPw">


			<div class="TempPw__top">

				<h2 class="TempPw__title">비밀번호를 재설정 해주세요</h2>


			</div>




			<div class=TempPw__bottom>

				<form name="idfindscreen" action="../LoginController" method="post">
					<input type="hidden" name="command" value="TempPw" />

					<div class="TempPw__submit">



						<div class="TempPw__sub__title">전송받은 인증번호를 입력해주세요</div>

						<div class="TempPw__submit__TempNumber">

							<div class="TempNumber">전송받은 인증번호를 입력해주세요</div>
							<input type="text" placeholder="인증번호를 입력해주세요" name="TempPw"
								required>

						</div>

						<div class="TempPw__submit__pw">
							<div class="Change__Pw">변경하실 비밀번호를 입력해주세요</div>
							<input type="text" placeholder="변경하실 비밀번호를 입력해주세요"
								name="Change_Pw" required />
						</div>

						<div class="TempPw__submit__pw__re">

							<div class="Change__Pw__Re">변경하실 비밀번호를 다시 입력해주세요</div>
							<input type="password" placeholder="다시 입력해주세요"
								name="Change_Pw_Re" required>

						</div>




						<div>
							<div>
								<button class="TempPw__submit__button" type="submit"
									value="TempPwbtn">비밀번호 변경하기</button>
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