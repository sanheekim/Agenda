

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

	h2, h3 {
		text-align: center;
	}
	
	form {
		width: 600px;
		height: 600px;
		border: 1px solid black;	
		margin-left: auto;
		margin-right: auto;
	}
	
	#myid, #mypw1, #mypw2, #myname, #roadFullAddr, #myph, #email{
		width: 200px;
		
	}
	
	#roadFullAddr {
		width: 400px;}

</style>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	function idCheck(){
		var doc = document.getElementsByName("member_id")[0];
		if (doc.value.trim() == "" || doc.value == null) {
			alert("아이디를 입력해 주세요!");
		} else {
			open("../RegistController?command=idcheck&member_id="+doc.value,
					"",
					"width=200, height=200");
		}
	}
	
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("./addrPop.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		
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

	<h3>A.gen.da</h3>
	
	<form action="/Agenda/RegistController" method="post">
	
	<h2>아괜다 계정 정보를 입력해주세요.</h2>
	
		<input type="hidden" name="command" value="registres"/>
		<table>
		<!-- 아이디, 비밀번호, 이름, 주소, 전화번호, 이메일 -->
			<tr>
				<td>아이디<br>
				<!-- 이런건 보통 id를 줌 -->
					<input type="text" name="member_id" id="myid" placeholder="아이디를 입력하세요." required="required" title="n" />
					<input type="button" value="중복체크" onclick="idCheck();"/>    
				</td>
			</tr>
			<tr>
				<td>비밀번호<br>
				<!-- name 속성 지우지마!! -->
					<input type="password" name="member_pw" id="mypw1" class="pw" placeholder="비밀번호를 입력하세요." required="required" />
					<span>8~15자리의 영문,숫자,특수문자의 입력이 가능합니다.</span><br>				
					<input type="password" name="member_pw" id="mypw2" class="pw" placeholder="비밀번호를 확인하세요." required="required" />
    				<font id="Notice" size="2"></font>
    				
				</td>
			</tr>
			<tr>
				<td>이름<br>
					<input type="text" name="member_name" id="myname" placeholder="이름을 입력하세요." required="required" />
				</td>
			</tr>
			<tr>
				<td>주소<br>
					<button type="button" class="btn btn-warning" onclick="goPopup()">주소검색</button>
					<input type="text" name="member_addr" id="roadFullAddr" class="form-control" placeholder="주소를 검색하세요." required="required" disabled/>
				</td>
			
			</tr>
			<tr>
				<td>전화번호<br>
					<input type="text" name="member_phone" id="myph" placeholder="전화번호를 '-'없이 입력하세요." required="required" />
				</td>
			</tr>
			<!--  
			<tr>
				<td>이메일<br>
					<input type="email" name="myemail" placeholder="이메일을 입력하세요." required="required" />
				</td>
			</tr>
			-->
			<tr>
				<td>이메일<br><input type="email" name="member_email" id="email"  placeholder="이메일을 입력하세요."/></td>
				<td><br><input id="receiver" type="button" value="인증번호발송" onclick="sendEmail()"></td>
				<td><input type="text" name="member_email_valid" placeholder="인증코드"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="회원가입" />
					<input type="button" value="취소" />
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
