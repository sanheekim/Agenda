<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<style type="text/css">
#paging {
	font-size: 10pt;
}
</style>

</head>
<body>
<div id="paging">


	<!-- 1~10까지 있는 페이지의 페이징 -->
	<c:url var="action" value="NoticeController.do"/>
	
	<c:if test="${param.prev }">
		<a href="${action}?page=${param.beginPage-1}&command=list">prev</a>
	</c:if>
	
	<c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
		<c:choose>
			<c:when test="${param.page==index }">
				<a id="onePage">${index }</a>
			</c:when>
			<c:otherwise>
				<a href="${action}?page=${index}&command=list">${index}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${param.next }">
		<a href="${action }?page=${param.endPage+1}&command=list">next</a>
	</c:if>

</div>
</body>
</html>