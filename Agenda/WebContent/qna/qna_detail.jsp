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

$(document).ready(function(){
	
	$("#btnReply").click(function (){
		
		console.log("insert click");
		
		var member_id = $(this).attr("name");
		console.log(member_id)
		var comm_content=$("#comm_content").val();
		var qna_no="${detail.qna_no}";
		console.log(comm_content + " " + qna_no);
	
		
		var url = "commController.do";
		console.log(url);
		
	   $.ajax({				
			type: "post",
			url: url +"?command=commwrite",
			data : {
				member_id : member_id,
				qna_no : qna_no,
				comm_content : comm_content
				},
			success: function(){
				alert("댓글이 등록되었습니다.");
				location.href= "qnaController.do?command=detail&qna_no="+${detail.qna_no};
			}                
		}); 
	});
	
	$(".btnDelete").click(function (){
		console.log("click");
		
		var comm_no= $(this).attr("name");
		console.log(comm_no);
		
		var qna_no="${detail.qna_no}";
		console.log(qna_no);
		
		var url = "commController.do";
		console.log(url);
				
		$.ajax({				
			type: "post",
			url: url+"?command=commdelete", 
			data : {
				qna_no : qna_no,
				comm_no : comm_no
				},
			success: function(){
				alert("댓글이 삭제되었습니다.");
				location.href= "qnaController.do?command=detail&qna_no="+${detail.qna_no};
			}                
		});
	});
	
	$(".btnUpdate").click(function(){
		console.log("update click");
		
		$(this).parent().children('.comm_content').attr("disabled", false);
		$(this).attr("value", "확인");
		
		$(this).attr("value", "확인").click(function(){
			
			console.log("확인");
			
			var qna_no="${detail.qna_no}";
			console.log(qna_no);
			
			var comm_no= $(this).attr("name");
			console.log(comm_no);
			
			var comm_content=$(this).parent().children('.comm_content').val();
			console.log(comm_content);
			
			var url = "commController.do"
			
			$.ajax({				
				type: "post",
				url: url+"?command=commupdate",
				data : {
					qna_no : qna_no,
					comm_no : comm_no,
					comm_content : comm_content
					},
				success: function(){
					alert("댓글이 수정되었습니다");
					location.href= "qnaController.do?command=detail&qna_no="+${detail.qna_no};
				}                
			});
			
		})
		
	});
	
});


</script>
<body>

	<c:choose>
		<c:when test="${empty logindto }">
			<jsp:include page="../header/header.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="../header/loginMain.jsp" />
		</c:otherwise>
	</c:choose>


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
					<td>${detail.qna_regdate}</td>
					<th>조회</th>
					<td>조회수</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="viewer" name="qna_content" value="${detail.qna_content }"></div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
					<c:if test="${sessionScope.member_id == detail.member_id}">
						<input type="button" value="수정하기"
						onclick="location.href='${pageContext.request.contextPath}/qnaController.do?command=update&qna_no=${detail.qna_no }'">
					</c:if>
						<input type="button" value="목록으로"
						onclick="location.href='${pageContext.request.contextPath}/qna/index.jsp'">
					<c:if test="${sessionScope.member_id == detail.member_id}">
						<input type="button" value="글삭제"
						onclick="location.href='${pageContext.request.contextPath}/qnaController.do?command=delete&qna_no=${detail.qna_no }'">
					</c:if>
					</td>
				</tr>
			</table>
			<!-- 댓글 목록 -->
			<div id="listReply">
			<table>
				<c:forEach var="row" items="${commlist}">
				<tr>	
					<td>
						번호 : ${row.comm_no }
						<br>
						작성자 : ${row.member_id}
						<br>
						<textarea rows="3" cols="40" disabled="disabled" class="comm_content">${row.comm_content }</textarea>
						<br>
						날짜 : ${row.comm_regdate}
						<br>
						<hr>
						<c:out value="${row.member_id }"></c:out><br>
						<c:out value="${sessionScope.member_id }"></c:out>
						<c:if test="${sessionScope.member_id == row.member_id}">
							<input type="button" value="수정" class="btnUpdate" name="${row.comm_no }">
							<input type="button" value="삭제" class="btnDelete" name="${row.comm_no }">
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
			<!-- 댓글 작성 -->
			<table>
				<c:choose>
				
					<c:when test="${logindto ne null }">
						<tr>
							<th><c:out value="${logindto.member_id }"></c:out></th>
							<td colspan="3"><textarea name="comm_content" id="comm_content"></textarea></td>
							<td><input type="button" value="작성" id="btnReply" name="${logindto.member_id }"></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
						<td><span>댓글 작성을 위해 로그인 해주세요</span></td>
						</tr>
					</c:otherwise>
				</c:choose>
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