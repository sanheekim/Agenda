<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script type="text/javascript">
	
		function checkCode() {
			var v1 = form2.code_check.value;
			var v2 = form2.code.value;
			if(v1!=v2) {
				document.getElementById('checkCode').style.color = "red";
				document.getElementById('checkCode').innerHTML = "잘못된 인증번호";
				makeNull();
			} else{
				document.getElementById('checkCode').style.color = "blue";
				document.getElementById('checkCode').innerHTML = "인증되었습니다";
				makeReal();
			}
		}
		function makeReal() {
			var hi = document.getElementById("hi");
			hi.type = "submit";
		}
		function makeNull() {
			var hi = document.getElementById("hi");
			hi.type = "hidden";
		}
	
	</script>

</head>
<body>
<form id="form2" action="javascript:getPassword()">
	<table>
			<tr>
				<td>
					<span>인증번호</span>
				</td>
				<td>
					<input type="text" name="code" id="code" onkeyup="checkCode()" placeholder="인증번호를 입력하세요." />
					<div id="checkCode"></div>
				</td>
				<td>
					<input type="hidden" readonly="readonly" name="code_check" id="code_check" value="<%=request.getAttribute("code")%>" />
				</td>
			</tr>
	</table>
	<input id="hi" type="hidden" value='인증하기' />
</form>
</body>
</html>