<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 버튼하나 생성 버튼을 클릭하면 함수 호출 -->

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
/* html문서가 생성될 떄 notification 기능에 대한 허용여부 */
        
	 
	
	   

 
   window.onload = function(){
	  
	  
		   
      if(window.Notification){
         Notification.requestPermission();
      }
    
    printTime();
   		//calculate();
    
      
   }
   function printTime() {
	   var list = new Array(); 
	   <c:forEach items="${list}" var="item">
	   list.push("${item.sche_time}");
	   </c:forEach>
	   console.log(list);
	  
	
	   var sche_time = '<%= session.getAttribute("sche_time")%>';
	   var clock = document.getElementById("clock");            // 출력할 장소 선택
	   var now = new Date();                                                  // 현재시간
	   var nowTime = /*now.getFullYear() + "년" + (now.getMonth()+1) + "월" + now.getDate() + "일" +*/ now.getHours() + ":" + now.getMinutes()  ;  
	   
	  
	   		if(list.includes(nowTime)){
		  
	   			clock.innerHTML = nowTime + "q";    // 현재시간을 출력
	   	 		var set = setTimeout(function(){
	       		notify();
	   			}, 10000);
	   			return;
	   			
	   		}
	   
		
	   else{
		   		clock.innerHTML = sche_time;
		   		
		   		
		  
	   		}
	   
       setTimeout("printTime()",1000);         // setTimeout(“실행할함수”,시간) 시간은1초의 경우 1000

}

   
   /* notify()함수를 호출하여 브라우저에 알림을 출력
   여기서는 의도적으로 1초 후에 알림을 출력하도록 함
   */
   /*function calculate(){
	   

	   
       	var set = setInterval(function(){
       		notify()
       	}, 5000);
	   
       	
    
   }*/

   function notify(){
       if(Notification.permission !== 'granted'){
          alert('notification is disabled');
       }
       else {
          var notification = new Notification('요리조리', {
             icon : './img/warning.jpg',
             body : '방송이 시작되었습니다.',
          });
          
          /* 푸쉬알림 클릭 시 어디로 이동할건지 : 우리페이지로.. */
          notification.onclick = function(){
             window.open('http://naver.com');
          };
       } 
      
    }
   
 
 
</script>
현재 <span id="clock"></span> 입니다<br />
<span id="a"></span>

	
	
</body>
</html>

