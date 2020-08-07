<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/header/loginMain.css"
	type="text/css" media="screen" />
<title>Insert title here</title>
</head>
<body>
	<header>
		<div class="wrapper">
			<div id="header-bar" onclick="location.href='${pageContext.request.contextPath}/main/main.jsp'">
				<img src="${pageContext.request.contextPath}/header/resources/img/medicine.png">
				<span>AH.GENDA</span>
			</div>
			<nav>
			<c:choose>
				<c:when test="${logindto.member_role eq 'ADMIN' }">
					<ul class="header-menu">
					<li><a href="${pageContext.request.contextPath}/admin/adlayout.jsp"><span class="mypage"><button id="mypage">Mypage</button></span></a></li>
					<li><a href="${pageContext.request.contextPath}/LoginController?command=logout"><span class="logout"><button id="logout">Log out</button></span></a></li>
					<li><a href="#"><span class="list" onclick="openNav()">&#9776;</span></a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="header-menu">
					<li><a href="${pageContext.request.contextPath}/MyinfoController?command=myinfo&member_id=${logindto.member_id }"><span class="mypage"><button id="mypage">Mypage</button></span></a></li>
					<li><a href="${pageContext.request.contextPath}/LoginController?command=logout"><span class="logout"><button id="logout">Log out</button></span></a></li>
					<li><a href="#"><span class="list" onclick="openNav()">&#9776;</span></a></li>
					</ul>
				</c:otherwise>
			</c:choose>
			<div id="mySidenav" class="sidenav">
				<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
				<p>SERVICE</p>
				<a href="${pageContext.request.contextPath}/MainController?command=mediSearch" 
					onmouseover="this.innerHTML='&nbsp;Medication check'"
					onmouseout="this.innerHTML='&nbsp;의약품조회'">의약품조회
				</a> 
				<a href="${pageContext.request.contextPath}/MainController?command=findPharm"
					onmouseover="this.innerHTML='&nbsp;Finding Pharmacy'"
					onmouseout="this.innerHTML='&nbsp;약국찾기'"><span>약국찾기</span>
				</a> 
				<a
					href="${pageContext.request.contextPath}/MediLockerRegistController?command=mediLocker" 
					onmouseover="this.innerHTML='&nbsp;Prescription'"
					onmouseout="this.innerHTML='&nbsp;처방전'"><span>처방전</span>
				</a> <br>
				<br> <br>
				<p>NOTICE</p>
				<a href="${pageContext.request.contextPath}/MainController?command=notice" 
					onmouseover="this.innerHTML='&nbsp;Notice'"
					onmouseout="this.innerHTML='&nbsp;공지사항'">공지사항</a>
				<p>SUPPORT</p>
				<a href="${pageContext.request.contextPath}/MainController?command=qna"
					onmouseover="this.innerHTML='&nbsp;Questions and Answers'"
					onmouseout="this.innerHTML='&nbsp;Q&A'">Q&A
				</a>
			</div>
			</nav>
		</div>
		<script>
			function openNav() {
				document.getElementById("mySidenav").style.width = "400px";
			}

			function closeNav() {
				document.getElementById("mySidenav").style.width = "0";
			}

		</script>
	</header>
</body>
</html>