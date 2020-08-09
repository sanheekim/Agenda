

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

	<%!
	%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

	h1 {
		text-align: center;
	}
	
	h2 {
		padding-left: 0;
	}
	
	form {
		width: 500px;
		height: 950px;
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
			open("${pageContext.request.contextPath}/RegistController?command=idcheck&member_id="+doc.value,
					"",
					"width=200, height=200");
		}
	}
	
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("./regist/addrPop.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		//location.href="${pageContext.request.contextPath}/addrPop.jsp"
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}

	function jusoCallBack(roadFullAddr){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.	
			console.log(roadFullAddr);
			document.getElementById("roadFullAddr").value = roadFullAddr;		
	}
	
	
	
	$(function(){
	    $('#mypw1').keyup(function(){
	      $('#chkNotice').html('');
	    });

	    $('#mypw2').keyup(function(){

	        if($('#mypw1').val() != $('#mypw2').val()){
	          $('#Notice').html('비밀번호 일치하지 않음<br><br>');
	          $('#Notice').attr('color', '#f82a2aa3');
	        } else{
	          $('#Notice').html('비밀번호 일치함<br><br>');
	          $('#Notice').attr('color', '#199894b3');
	        }
	    });  
	});
	
</script>

</head>

<body>

	<h1>A.gen.da</h1>
	
	<form action="${pageContext.request.contextPath}/NaverRegistController" method="post">
	
	<br><br><h2>아괜다 계정 정보를 입력해주세요.</h2><br><br>
	
		<input type="hidden" name="command" value="Naverregistres"/>
		<table>
		<!-- 아이디, 비밀번호, 이..름, 주소, 전화번호,, 이메일 -->
			<tr>
				<td>아이디<br>
				<!-- 이런건 보통 id를 줌 -->
					<input type="text" name="member_id" id="myid" placeholder="아이디를 입력하세요." required="required" value="<%=session.getAttribute("member_id") %>" title="n" />
					<br>
					<input type="button" id="idButton" value="중복체크" onclick="idCheck();"/>  
					<br><span>영문,숫자,특수문자의 입력이 가능합니다.</span><br>
				</td>
			</tr>
			<tr>
				<td><br>비밀번호<br>
				<!-- name 속성 지우지마!! -->
					<input type="password" name="member_pw" id="mypw1" class="pw" placeholder="비밀번호." required="required" />
					<br><span>영문,숫자,특수문자의 입력이 가능합니다.</span><br>				
					<br><input type="password" name="member_pw" id="mypw2" class="pw" placeholder="비밀번호 재입력" required="required" />
					<br><span>비밀번호를 다시 입력해주세요.</span><br>
    				<font id="Notice" size="2"></font>
    				<input type="hidden" name="member_salt">
    				
				</td>
			</tr>
			<tr>
				<td><br>닉네임<br>
					<input type="text" name="member_name" id="myname" placeholder="닉네임을 입력해주세요." required="required" value="<%=session.getAttribute("member_name") %>" />
				</td>
			</tr>
			<tr>
				<td><br>주소<br>
					<input type="button" id="addrButton" class="btn btn-warning" value="주소검색" onclick="goPopup()">
					<br>
					<input type="text" name="member_addr" id="roadFullAddr" class="form-control" placeholder="주소를 검색하세요." required="required" disabled/>
				</td>
			</tr>
			<tr>
				<td><br>전화번호<br>
					<input type="text" name="member_phone" id="myph" placeholder="전화번호를 '-'없이 입력하세요." required="required" />
				</td>
			</tr>
			<tr>
				<td><br>이메일<br>
					<input type="email" name="member_email" id="email"  placeholder="이메일을 입력하세요." value="<%=session.getAttribute("member_email") %>"/>
				</td>
			</tr>
			<tr>
				<td>
					<input id="receiver" type="button" value="인증번호발송" onclick="sendEmail()">
					<br>
					<input type="text" name="member_email_valid" id="code_text" placeholder="인증코드">
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
			url: "/Agenda/NaverRegistController?command=emailValid&email="+email,
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
