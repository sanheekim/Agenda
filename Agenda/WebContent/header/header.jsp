<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/header/header.css" type="text/css" media="screen" />
<title>Insert title here</title>
</head>
<body>
	<header>
		<div class="wrapper">
			<h2></h2>
			<h1>
				<img src="${pageContext.request.contextPath}/header/resources/img/medicine.png"
					style="position: absolute; width: 32px; height: 30px; left: -40px; top: -8px;">AH.GENDA
			</h1>
			<nav>
				<ul class="menu">
					<li><a href="#"><span class="login"><button id="로그인">Log in</button></span></a></li>
					<li><a href="#"><span class="reg"><button id="회원가입">Register</button></span></a></li>
					<li><a href="#"><span class="list" onclick="openNav()">&#9776;</span></a></li>
				</ul>
			</nav>
		</div>
		<div id="mySidenav" class="sidenav">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<p>SERVICE</p>
			<a href="#" onmouseover="this.innerHTML='&nbsp;ABC'"
				onmouseout="this.innerHTML='&nbsp;의약품조회'"">의약품조회</a> 
			<a href="#"
				onmouseover="this.innerHTML='&nbsp;DEF'"
				onmouseout="this.innerHTML='&nbsp;약국찾기'""><span>약국찾기</span></a> 
			<a href="#" onmouseover="this.innerHTML='&nbsp;GHI'"
				onmouseout="this.innerHTML='&nbsp;처방전'""><span>처방전</span></a> <br>
			<br>
			<br>
			<p>NOTICE</p>
			<a href="#" onmouseover="this.innerHTML='&nbsp;123'"
				onmouseout="this.innerHTML='&nbsp;공지사항'"">공지사항</a>
			<p>SUPPORT</p>
			<a href="#" onmouseover="this.innerHTML='&nbsp;456'"
				onmouseout="this.innerHTML='&nbsp;Q&A'"">Q&A</a>
		</div>

		<script>
			function openNav() {
			  document.getElementById("mySidenav").style.width = "400px";
			}
			
			function closeNav() {
			  document.getElementById("mySidenav").style.width = "0";
			}
			
			 $('body').prepend('<div id="overlay">');
		</script>

	</header>
</body>
</html>