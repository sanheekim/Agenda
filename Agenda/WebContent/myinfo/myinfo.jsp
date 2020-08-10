<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<style type="text/css">
@charset "UTF-8";
@font-face{font-family:'Nanum Gothic'; src:url('../font/NanumBarunGothic.ttf')}
@font-face{font-family:'Nanum Gothic Light'; src:url('../font/NanumBarunGothicLight.ttf')}
@font-face{font-family:'Nanum Gothic Bold'; src:url('../font/NanumBarunGothicBold.ttf')}
@font-face{font-family:'Nanum Gothic Ultra Light'; src:url('../font/NanumBarunGothicUltraLight.ttf')}
	
	body {
 		margin: 0 0;
 		padding: 0 0;
	}

	#myinfo {
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 100vw;
		height: 100vh;
	}
	
	#my_top {
		display: flex;
		justify-content: center;
		width: 100vw;
		height: 150px;
	}
	
	#my_title {
		display: flex;
		justify-content: center;
		align-items: center;
		font-family:'Nanum Gothic Bold';
		font-size: 44px;
		line-height: 72px;
		color: #000000;		
	}
	
	#my_bottom {
		width: 100vw;
		top: 100vh;
		display: flex;
		align-items: center;
		justify-content: space-around;
		font-family:'Nanum Gothic';
	}
	
	.my_box {
		width: 240px;
		height: 400px;
		top: 373px;
		bottom: 40px;
		background: #FAFAFA;
		cursor: pointer;
		text-align: center;
		font-size: 16pt;
		line-height: 200px;
	}
	
	#my_box span {
		display: teble-cell;
	}
	
	.icon {
		width: 100px;
		height: 100px;
		display: block;
		position: relative;
		margin: 0px auto;
	}
	
	.btn {
		position: absolute;
		background-color: #CED8F6;
		top: 200px;
	}
	
	#my_footer {
		position: relative;
		width: 100vw;
		height: 300px;
		bottom: 0;
		background: #E5E5E5;
	}
	
</style>

</head>
<body>

<div id="myinfo">

	<c:choose>
    	<c:when test="${empty logindto }">
    		<jsp:include page="../header/header.jsp" />
    	</c:when>
    	<c:otherwise>
    		<jsp:include page="../header/loginMain.jsp" />
    	</c:otherwise>
	</c:choose>

	<div id="my_top">
		<div id="my_title">마이 페이지</div>
	</div>
	
	<div id="my_bottom">
			<div class="my_box" onclick="location.href='${pageContext.request.contextPath}/MyinfoController?command=myinfoUpdateform&member_id=${dto.member_id}'">
			<span>내 정보 수정</span>
			<img class="icon" src="img/info.png" />
			</div>
			<div class="my_box" onclick="location.href='${pageContext.request.contextPath}/MyinfoController?command=myinfoDelete&member_id=${logindto.member_id }'">
			<span>계정 탈퇴</span>
			<img class="icon" src="img/bye.png" />
			</div>
			<div class="my_box" onclick="location.href='${pageContext.request.contextPath}/dnController?command=dnlist&member_id=${logindto.member_id }'">
			<span>후원내역조회</span>
			<img class="icon" src="img/qr.png" />
			</div>
	</div>	

	<footer id="my_footer">
		<jsp:include page="../footer/mainFooter.jsp"></jsp:include>
	</footer>
</div>

</body>
</html>