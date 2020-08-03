<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/medilockerscan/mediLockerScanMain.css" >
</head>
<body>
	<header>
		<jsp:include page="../header/loginMain.jsp"></jsp:include>
	</header>
	<div id="LockerMainDiv">
		<h2>처방전 보관함</h2>
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
</body>
</html>