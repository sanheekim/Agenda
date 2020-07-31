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
<script>
	// 원하는 페이지로 이동시 검색조건, 키워드 값을 유지하기 위해 
	function list(page){
		location.href="${pageContext.request.contextPath}/qnaController.do?command=list&curPage="+page+"&searchOption=${map.searchOption}"+"&keyword=${map.keyword}";
	}
</script>
</head>

<body>
	<header>
	</header>
	<section>
		<h1>Q & A</h1>
		<hr>
		<form action="${pageContext.request.contextPath}/qnaController.do" method="post">
			<input type="hidden" name="command" value="list">
			${map.count}개의 게시물이 있습니다.
			<table>
			
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
					<th>조회</th>
				</tr>
				
				
				<c:forEach var="row" items="${map.list}">
					<tr>
						<td>${row.qna_no}</td>
						<!-- 게시글 상세보기 페이지로 이동시 게시글 목록페이지에 있는 검색조건, 키워드, 현재페이지 값을 유지하기 위해 -->
						<td><a
							href="${pageContext.request.contextPath}/qnaController.do?command=detail&qna_no=${row.qna_no}">${row.qna_title}
						</a></td>
						<td><!-- 작성자 --></td>
						<td>${row.qna_regdate}</td>
						<td><!-- 조회수 --></td>
					</tr>
				</c:forEach>
				
				<!-- 검색 -->
				<tr>
					<td colspan="5">
					<select name="searchOption">
						<option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}"/> >제목+이름+내용</option>
						<option value="member_id" <c:out value="${map.searchOption == 'user_name'?'selected':''}"/> >이름</option>
						<option value="qna_content" <c:out value="${map.searchOption == 'content'?'selected':''}"/> >내용</option>
						<option value="qna_title" <c:out value="${map.searchOption == 'title'?'selected':''}"/> >제목</option>
					</select>
							<input name="keyword" value="${map.keyword}">
							<input type="submit" value="조회">
							<input type="button" value="글쓰기"
								onclick="location.href='${pageContext.request.contextPath}/qnaController.do?command=write'">
					</td>
				</tr>
				<!-- 검색 끝 -->
				
				<!-- 페이징 -->
				<tr>
					<td colspan="5">
						<!-- 처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력-->
						<c:if test="${map.boardPager.curBlock > 1}">
							<a href="javascript:list('1')">[처음]</a>
						</c:if>
						
						<!-- 이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
						<c:if test="${map.boardPager.curBlock > 1}">
							<a href="javascript:list('${map.boardPager.prevPage}')">[이전]</a>
						</c:if>
						
						<!-- **하나의 블럭 시작페이지부터 끝페이지까지 반복문 실행 -->
						<c:forEach var="num" begin="${map.boardPager.blockBegin}" end="${map.boardPager.blockEnd}">
							<!-- 현재페이지이면 하이퍼링크 제거 -->
							<c:choose>
								<c:when test="${num == map.boardPager.curPage}">
									<span style="color: red">${num}</span>&nbsp;
								</c:when>
								<c:otherwise>
									<a href="javascript:list('${num}')">${num}</a>&nbsp;
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<!-- 다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
						<c:if test="${map.boardPager.curBlock <= map.boardPager.totBlock}">
							<a href="javascript:list('${map.boardPager.nextPage}')">[다음]</a>
						</c:if>
						
						<!-- 끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
						<c:if test="${map.boardPager.curPage <= map.boardPager.totPage}">
							<a href="javascript:list('${map.boardPager.totPage}')">[끝]</a>
						</c:if>
					</td>
				</tr>
				<!-- 페이징끝 -->
				
			</table>
		</form>
	</section>
	<footer>
	</footer>
</body>
</html>