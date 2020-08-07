<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<style type="text/css">



</style>
</head>
<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/login/loginSearchId.css">	
<body>



<%
 String id = (String)request.getAttribute("member_id");
 System.out.println("id:" + id);
%>
<jsp:include page="../header/header.jsp" />

<section class="first">


	<div class="SearchId">
		<div class="SearchtId__top">

			<h2 class="SearchtId__title">아이디 찾기 성공!</h2>
			
			
		</div>
		<div class="SearchId__bottom">
		
		<div class="SearchtId__sub__title">찾으시는 아이디는</div>
		<div class="SearchId__Result">
		
		<span class="douquot">"</span>
		<span class="SearchId__Result"> <%=id %></span>
		<span class="douquot">"</span>
		
		<div>입니다</div>
	</div>
		</div>
		
		<div>
			<button class="loginbtn" value="loginbtn"  onclick="location.href='${pageContext.request.contextPath}/login/loginForm.jsp'">로그인</button>
						
		</div>

</div>
</section>
 <jsp:include page="../footer/mainFooter.jsp" />
</body>
</html>