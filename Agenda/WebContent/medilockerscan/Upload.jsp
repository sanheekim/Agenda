<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="../GoogleVisionController" method="post" enctype="multipart/form-data">
	<table>
          <tr>
              <td>파일 선택</td>
              <td><input type="file" value="파일 선택" name="file"/></td>
          </tr>
          <tr>
          	  <td><input type="submit" value="파일 업로드"/></td>
          </tr>
         
    

     </table>
     </form>
</body>
</html>