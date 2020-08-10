<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mediSearch</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/medisearch/mediSearch.css">
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	
$(function(){
	$(".subDivItem").hide();
	$("#mediSearchBtn").click(
		function(){
			var item = $("#mediSearch").val();
			var url = "../mediSearch";
			$.ajax({
				type : "GET",
				url : url,
				data : {command : "predInfo", item : item},
				dataType : "text",
				success : function(data){
					
					var temp = data;
					temp = temp.replace(/\n/gi,"");
					temp = temp.replace(/\r/gi,"");
					console.log(temp);
					var obj = JSON.parse(temp);
					console.log(typeof(obj));
					if(obj.item_name == item){
						$(".subDivItem").show();
						$("#item_name").text(obj.item_name);
	                    $("#effect").text(obj.effect);
	                    $("#capacity").text(obj.capacity);
	                    $("#warning").text(obj.warning);
					}else{
						alert("조회된 정보 없음");
						$(".subDivItem").show();
						$("#noData").text("조회된 정보가 없습니다");
						
					}
					
				},
				error : function(){
					alert("정보 조회 실패");
				}
			});
		}		
	)
	
})
		

</script>
</head>
<body>

	<header>
		<c:choose>
    		<c:when test="${empty logindto }">
    			<jsp:include page="../header/header.jsp" />
    		</c:when>
    		<c:otherwise>
    			<jsp:include page="../header/loginMain.jsp" />
    		</c:otherwise>
		</c:choose>
	</header>
	<div id="mainDiv">
		<div class="subDiv">
			<div id="mainTheme">의약품 조회</div>
		</div>
		<div class="subDiv">
			<input id="mediSearch">
			<button id="mediSearchBtn">조회</button>
			
		</div>
		<div class="subDiv" id="subDetailInfo" >
			<div>상세정보 </div>
			
			<div class="subDivItems">
				<div id="noData"></div>
				<div class="subDivItem" id="subDivItem_1">
					<div>이름</div>
					<div>효능효과</div>
					<div>용법용량</div>
					<div>주의사항</div>
				</div>
				<div class="subDivItem">
					<div id="item_name"></div>
					<div id="effect"></div>
					<div id="capacity"></div>
					<div id="warning"></div>
				</div>
			</div>
		</div>
		<div class="subDiv Info">
			<div>낱알정보</div>
		</div>
		<div class="subDiv Info">
			<div>부작용 정보</div>
		</div>
	</div>

	<footer>
		<jsp:include page="../footer/mainFooter.jsp"></jsp:include>
	</footer>
</body>
</html>