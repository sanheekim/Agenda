<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/qna/qna_list.css">
<title>Insert title here</title>
</head>

<body>
	<header>
	</header>
	<section>
		<h1>Q & A</h1>
		<hr>
		<form action="${pageContext.request.contextPath}/qnaController.do" method="post">
			<table>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
					<th>조회</th>
				</tr>
				<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td colspan="5">----작성된 글이 존재하지않습니다---</td>
						</tr>
					</c:when>
					<c:otherwise>
						<!-- 설정한 변수 dto에 list 객체의 값을 가져온다 -->
						<c:forEach items="${list }" var="dto">
							<tr>
								<td>${dto.qna_no }</td>
								<td><a
									href="${pageContext.request.contextPath}/qnaController.do?command=detail&qna_no=${dto.qna_no }">${dto.qna_title }</a></td>
								<td>${dto.member_id }</td>
								<td>${dto.qna_regdate }</td>
								<td></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<tr>
					<td colspan="5">
						<fieldset>
							<select name="key">
								<option ${(param.key == "qna_title")? "selected":"" }
									value="qna_title">제목</option>
								<option ${(param.key == "member_id")? "selected":"" }
									value="member_id">작성자</option>
							</select> <input type="text" name="word" value="${param.word }"> <input
								type="submit" value="검색"> <input type="button"
								value="글쓰기"
								onclick="location.href='${pageContext.request.contextPath}/qnaController.do?command=write'">
						</fieldset>
					</td>
				</tr>
			<tr>
			<td>
				<jsp:include page="boardPager.jsp">
					<jsp:param value="${paging.page}" name="page" />
					<jsp:param value="${paging.beginPage}" name="beginPage" />
					<jsp:param value="${paging.endPage}" name="endPage" />
					<jsp:param value="${paging.prev}" name="prev" />
					<jsp:param value="${paging.next}" name="next" />
				</jsp:include>
			</td>
			</tr>
			</table>
		</form>
	</section>
	<footer>
	</footer>
</body>
</html>