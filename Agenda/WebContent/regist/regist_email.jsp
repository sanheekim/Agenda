<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
	
		function CheckCode(){
			var v1 = form2.code_check.value;
			var v2 = form2.code.value;
			
			if(v1 != v2){
				document.getElementById("checkCode").style.color = "red";
				document.getElementById("checkCode").innerHTML = "잘못된 인증 번호";
				
				makeNull();
			}else {
				document.getElementById("checkCode").style.color = "blue";
				document.getElementById("checkCode").innerHTML = "인증되었습니다";
				
				makeReal();
			}
		}
		
		function makeReal(){
			var hi = document.getElementById("hi");
			hi.type="submit";
		}
		function makeNull(){
			var hi = document.getElementById("hi");
			hi.type="hidden";
		}
		
		
		function getParameterValues(){
			var queryString = "?command=real&email=" + $("#email").val();
		return queryString;
		}
		
		
		function getId(){
			$.ajax({
				url : "send" + getParameterValues(),
				dataType : "json",
				success : function(data){
					//alert("Id는"+data.id+"입니다.");
					alert("인증성공!\n 회원가입 페이지로 이동합니다.");
					window.location="registfrom.jsp";
					}, error:function(){
						alert("연결실패");
					}
				});
			}
		
	</script>

</head>
<body>
<%
	String email = (String)request.getAttribute("email");
%>
	<form id="form2" action="javascript:getId()">
		<input type="hidden" id="email" value="<%=email %>"/>
			<table>
				<tr>
					<td><span>인증번호</span></td>
					<td><input type="text" name="code" id="code"
								onkeyup="checkCode()" placeholder="인증번호를 입력하세요.">
								<div id="checkCode"></div>
					</td>
					<td><input type="hidden" readonly="readonly" name="code_check"
								id="code_check" value="<%=request.getAttribute("code") %>">
					</td>
				</tr>
			</table>
		<input id="hi" type="button" value="인증하기">
	</form>
	
</body>
</html>