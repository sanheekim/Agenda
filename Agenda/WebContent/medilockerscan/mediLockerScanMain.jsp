<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/medilockerscan/mediLockerScanMain.css" >
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/medilockerscan/mediLockerScanMain.js"></script>
</head>
<body>
	<header>
		<jsp:include page="../header/loginMain.jsp"></jsp:include>
	</header>
	<div id="LockerMainDiv">
		<div id="LockerThemeDiv">
			<h2>처방전 보관함</h2>
			<button onclick="upload();">SCAN</button>
		</div>
		<div id="LockerSubDiv">
			<div id="LockerListDiv">
				<div>2020.08.03 처방전1</div>
				<div>2020.08.03 처방전2</div>
				<div>2020.08.03 처방전3</div>
				<div>2020.08.03 처방전4</div>
			</div>
			<div>상세정보리스트</div>
		</div>
	</div>
	<footer>
		<jsp:include page="../footer/mainFooter.jsp"></jsp:include>
	</footer>
	  <c:forEach items="${list }" var="scan">
 		<li> ${scan }</li>
 	  </c:forEach>

	
</body>
</html>