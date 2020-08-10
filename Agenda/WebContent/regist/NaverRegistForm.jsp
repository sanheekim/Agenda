<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

	body {
		background-color: whitesmoke;		
	}
	
	#header-bar {
		display: flex;
		cursor: pointer;
		justify-content: center;
		
	}
	
	#header-bar img{
		width: 30px;
		height: 30px;
		padding : 10px;
		margin-left: 10px;
	}
	
	#header-bar span{
		display: block;
		font-size: 2em;
		font-family:'Nanum Gothic';
		letter-spacing: 3px;
	}
	
	h2 {
		padding-left: 0;
	}
	
	form {
		width: 500px;
		height: 600px;
		border: 1px solid #dbdbdb;
		margin-left: auto;
		margin-right: auto;
		padding-left: 100px;
	}
	
	td {
		font-size: 15px;
	}
	
	span {
		font-size: 10px;
	}
	
	#myid, #mypw1, #mypw2, #myname, #roadFullAddr, #myph, #email{
		width: 400px;
		height:30px;
		font-size:15px;
		border-top: 1px;
        border-left: 1px;
        border-right: 1px;
		
	}
	
	#roadFullAddr, #code_text {
		width: 400px;
		height: 30px;
		font-size: 15px;
		border-top: 1px;
        border-left: 1px;
        border-right: 1px;
	}
	
	input::placeholder {
		color: #dbdbdb;
	}
	
	#button {
		text-align: center;
		width:200px;
		height:30px;
		background-color: #dbdbdb;
		border: none;
		font-size: 15px;
		cursor: pointer;
	}
	
	#button:hover {
		background-color: #b5b5b5;
	}
	
	
	#receiver, #idButton, #addrButton {
		width: 100px;
		height: 30px;
		background-color: white;
		border: 1px solid black;
		border-radius: 2em;
		cursor: pointer;
	}
	
	#receiver:hover {
		background: #dbdbdb;
	}
	
	#idButton:hover {
		background: #dbdbdb;
	}
	
	#addrButton:hover {
		background: #dbdbdb;
	}
	
	

</style>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	function idCheck() {
		var doc = document.getElementsByName("member_id")[0];
		if (doc.value.trim() == "" || doc.value == null) {
			alert("아이디를 입력해 주세요!");
		} else {
			open("${pageContext.request.contextPath}/NaverRegistController?command=idcheck&member_id="+doc.value,
					"",
					"width=200, height=200");
		}
	}
	
</script>

</head>

<body>

	<br>
	<div id="header-bar" onclick="location.href='${pageContext.request.contextPath}/main/main.jsp'">
		<img src="${pageContext.request.contextPath}/header/resources/img/medicine.png">
		<span>AGENDA</span>
	</div>
	<br>
	
	<form action="${pageContext.request.contextPath}/NaverRegistController" method="post">
	
	<br><br><h2>아괜다 계정 정보를 입력해주세요.</h2><br><br>
	
		<input type="hidden" name="command" value="Naverregistres"/>
		<table>
		<!-- 아이디, 비밀번호, 이..름, 주소, 전화번호,, 이메일 -->
			<tr>
				<td>아이디<br>
				<!-- 이런건 보통 id를 줌 -->
					<input type="text" name="member_id" id="myid" placeholder="아이디를 입력하세요." required="required" value="<%=session.getAttribute("member_id") %>" title="n" readonly="readonly" style="background: whitesmoke;"/>
					<br>
					<input type="button" id="idButton" value="중복체크" onclick="idCheck();"/>  
					<br><span>영문,숫자,특수문자의 입력이 가능합니다.</span><br>
				</td>
			</tr>
			<tr>
				<td><br>닉네임<br>
					<input type="text" name="member_name" id="myname" placeholder="닉네임을 입력해주세요." required="required" value="<%=session.getAttribute("member_name") %>" readonly="readonly" style="background: whitesmoke;"/>
				</td>
			</tr>
			<tr>
				<td><br>이메일<br>
					<input type="email" name="member_email" id="email"  placeholder="이메일을 입력하세요." value="<%=session.getAttribute("member_email") %>" readonly="readonly" style="background: whitesmoke;"/>
				</td>
			</tr>
			<tr>
				<td>
					<input id="receiver" type="button" value="인증번호발송" onclick="sendEmail()">
					<br>
					<input type="text" name="member_email_valid" id="code_text" placeholder="받으신 인증번호를 입력하세요." style="background: whitesmoke;">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<br><br>
					<input type="submit" id="button" value="회원가입" />
					<input type="button" id="button" value="취소" onclick="location.href='${pageContext.request.contextPath}/main/main.jsp'"/>
				</td>
			</tr>
		</table>
	</form>
	<script>
	function sendEmail() {
		var email = document.getElementById("email").value
		$.ajax({
			url: "/Agenda/RegistController?command=emailValid&email="+email,
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

</body>
</html>
