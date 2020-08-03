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
	href="https://uicdn.toast.com/editor/latest/tui-editor/dist/tui-editor.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/qna/qna_detail.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>Insert title here</title>
</head>
<script>


/* 댓글 입력 */
$(document).ready(function(){
	$("#btnReply").click(function(){
		console.log("click");
		replyInsert(); 
	});
	
	$("#btnDelete").click(function(){
		console.log("click");
		replyInsert(); 
	});
	
});

function replyInsert(){
	
	var comm_content=$("#comm_content").val();
	var qna_no="${detail.qna_no}";
	console.log(comm_content + " " + qna_no);
	
	var url = "commController.do";
	console.log(url);
	
	$.ajax({				
		type: "post",
		url: url +"?command=write",
		data : {
			qna_no : qna_no,
			comm_content : comm_content
			},
		success: function(){
			alert("댓글이 등록되었습니다.");
		}                
	});
}

/* 댓글 삭제 */

function replyDelete(){
	var qna_no="${detail.qna_no}";
	console.log(qna_no);
	
	var url = "commController.do";
	console.log(url);
	
	$.ajax({				
		type: "post",
		url: url +"?command=delete",
		success: function(){
			alert("댓글이 삭제되었습니다.");
		}                
	});
}

</script>
<body>
  	 
  	<jsp:include page="../header/header.jsp" />
	
	<section>
		<h1>Q&A 글보기</h1>
		<hr>
		<!-- 글 상세내역 부분 -->
			<input type="hidden" name="qna_no" value="${detail.qna_no }" id="qna_no">
			<table id="board">
				<tr>
					<td colspan="6"><input type="hidden" name="qna_title"
						value="${detail.qna_title }">${detail.qna_title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="hidden" name="member_id"
						value="${detail.member_id }">${detail.member_id}</td>
					<th>작성시간</th>
					<td>작성시간</td>
					<th>조회</th>
					<td>조회수</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="viewer" name="qna_content" value="${detail.qna_content }"></div>
					</td>
				</tr>
				<tr>
					<td colspan="6"><input type="button" value="수정하기"
						onclick="location.href='${pageContext.request.contextPath}/qnaController.do?command=update&qna_no=${detail.qna_no }'">
						<input type="button" value="목록으로"
						onclick="location.href='${pageContext.request.contextPath}/qna/index.jsp'">
						<input type="button" value="글삭제"
						onclick="location.href='${pageContext.request.contextPath}/qnaController.do?command=delete&qna_no=${detail.qna_no }'">
					</td>
				</tr>
			</table>
			<!-- 댓글 목록 -->
			<div id="listReply">
			<table>
				<c:forEach var="row" items="${commlist}">
				<tr>	
					<td>
						${row.member_id}
						<br>
						${row.comm_content}
						<br>
						${row.comm_regdate}
						<br>
						<hr>
						<input type="button" value="수정" id="btnUpdate">
						<input type="button" value="삭제" id="btnDelete">
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
			<table>
				<tr>
					<th>작성자</th>
					<td colspan="3"><textarea name="comm_content" id="comm_content"></textarea></td>
					<td>작성시간</td>
					<td><input type="submit" value="작성" id="btnReply"></td>
				</tr>
			</table>
	</section>
	
    <jsp:include page="../footer/mainFooter.jsp" />
</body>
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.js"></script>
<script>
	const viewer = new toastui.Editor({
		el : document.querySelector('#viewer'),
		height : '300px',
		initialValue : `${detail.qna_content }`
	});
</script>
</html>