<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		
		//console.log("insert click");
		
		var member_id = $(this).attr("name");
		//console.log(member_id)
		var comm_content=$("#comm_content").val();
		var qna_no="${detail.qna_no}";
		//console.log(comm_content + " " + qna_no);
		
		if(comm_content == ""){
			alert("내용을 입력하세요");
			return;
		}
		
		var url = "commController.do";
		//console.log(url);
		
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
		//console.log("click");
		
		var comm_no= $(this).attr("name");
		//console.log("댓글번호" +  comm_no);
		
		var qna_no="${detail.qna_no}";
		//console.log(qna_no);
		
		var url = "commController.do";
		//console.log(url);
		
		if(confirm("삭제하시겠습니까?")){
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
		}
	});
	
	$(".btnUpdate").click(function(){
		//console.log("update click");
		alert('수정하시겠습니까?');
		var comm_no = $(this).attr("name");
		//console.log("댓글번호" +  comm_no);
		
		$(this).parent().parent().children().children().attr("disabled",false);
		$(this).attr("value", "확인");
		
		$(this).attr("value", "확인").click(function(){
			
			//console.log("확인");
			
			var qna_no="${detail.qna_no}";
			//console.log(qna_no);
			
			var comm_no= $(this).attr("name");
			//console.log(comm_no);
			
			var comm_content=	$(this).parent().parent().children().children().val();
			//console.log(comm_content);
			
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
		<h1><a href="${pageContext.request.contextPath}/qna/index.jsp">Q & A</a></h1>
		<!-- 글 상세내역 부분 -->
			<input type="hidden" name="qna_no" value="${detail.qna_no }" id="qna_no">
			<input type="hidden" name="member_id" value="${detail.member_id }">
			<table id="board">
				<tr>
					<td>
					<input type="hidden" name="qna_title" value="${detail.qna_title }">${detail.qna_title } 
					</td>
				</tr>
				<tr>
					<td>작성자 | ${detail.member_id}</td>
					<td>작성시간 | <fmt:formatDate value="${detail.qna_regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>조회수 | ${detail.qna_hit}회</td>
				</tr>
				<tr>
					<td>
						<c:if test="${logindto.member_id == detail.member_id}">
							<input type="button" value="수정"
							onclick="location.href='${pageContext.request.contextPath}/qnaController.do?command=update&qna_no=${detail.qna_no }'">
						</c:if>
						<c:if test="${logindto.member_id == detail.member_id || logindto.member_role == 'ADMIN' }">
							<input type="button" value="삭제"
							onclick="location.href='${pageContext.request.contextPath}/qnaController.do?command=delete&qna_no=${detail.qna_no }'">
						</c:if>
					</td>
					<td colspan="2">
						<input type="button" value="목록으로"
						onclick="location.href='${pageContext.request.contextPath}/qna/index.jsp'">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="viewer" name="qna_content" value="${detail.qna_content }"></div>
					</td>
				</tr>
			</table>
			
			<!-- 댓글 목록 -->
			<div id="listReply">
			<table>
				<c:forEach var="row" items="${commlist}">
				<tr title="1">	
					<td>
						${row.member_id}
					</td>
					<td title="3">
						<textarea title="4" rows="2" cols="70" disabled="disabled" class="comm_content">${row.comm_content }</textarea>
					</td>
					<td title="2">					
						<fmt:formatDate value="${row.comm_regdate}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
						<c:if test="${logindto.member_id == row.member_id}">
							<input type="button" value="수정" class="btnUpdate" name="${row.comm_no }">
						</c:if>
						<c:if test="${logindto.member_id == row.member_id || logindto.member_role == 'ADMIN'}">
							<input type="button" value="삭제" class="btnDelete" name="${row.comm_no }">
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
			
			<!-- 댓글 작성 -->
			<table id="replyBoard">
				<c:choose>
				
					<c:when test="${logindto ne null }">
						<tr>
							<th><c:out value="${logindto.member_id }"></c:out></th>
							<td><textarea rows="2" cols="70" name="comm_content" id="comm_content"></textarea></td>
							<td><input type="button" value="작성" id="btnReply" name="${logindto.member_id }"></td>
						</tr>
					</c:when>
					<c:otherwise>
							<th></th>
							<td><textarea rows="2" cols="70" disabled="disabled" name="comm_content" id="comm_content" placeholder="댓글 작성을 위해 로그인해주세요"></textarea></td>
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
		initialValue : `${detail.qna_content }`
	});
</script>
</html>