<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<%
String context = request.getContextPath();
%>
<html>
<title>Insert title here</title>
<link href="css/sports.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

  function likecheck(num) {
    var user_id = $('#user_id').val();
    var sport_type = $('#sport_type').val();
    var exnum = num;
    var check_list = {"user_id":user_id, "sport_type":sport_type, "exnum":exnum};
    console.log(check_list + "좋아요");
    $.ajax({
      url:"<%=context%>/imgcheck.do",
      data: check_list,
      dataType:'text',
      success:function(data){
      	if(data == "like") {
      		 document.getElementById("imgcheck").src="./images/star.png";
             const result = document.getElementById('likecnt');
             let number = result.innerText;
             number = parseInt(number)+1;
             result.innerText = number;
             location.reload();
      	}
      	if(data =="dislike"){
      		 document.getElementById("imgcheck").src="./images/empty_star.png";
             const result = document.getElementById('likecnt');
             let number = result.innerText;
             number = parseInt(number)-1;
             result.innerText = number;
             location.reload();
      	}
      }
    });
  };
</script>
<head></head>
<body>
<jsp:include page="css/header.jsp"></jsp:include>

<section>
  <div class="type">
    <div class="type_blue" onclick="location.href='locationForm.do?sport_type=1'"><a href='#'>위치</a></div>
    <div class="type_white" onclick="location.href='sportsTypeForm.do?sport_type=1'"><a href='#'>종목</a></div>
  </div>

  <div class="buttons">
    <a href='sportsTypeForm.do?sport_type=1' class="myButton">헬스</a>
    <a href='sportsTypeForm.do?sport_type=2' class="myButton">수영</a>
    <a href='sportsTypeForm.do?sport_type=3' class="myButton">요가/필라</a>
    <a href='sportsTypeForm.do?sport_type=4' class="myButton">골프</a>
    <a href='sportsTypeForm.do?sport_type=5' class="myButton">기타</a>
  </div>

  <div class="container_sport">
    <c:if test="${totCnt > 0 }">
      <c:forEach var="sports" items="${list }">

        <div class="center">
          <c:choose>
            <c:when test="${sports.pictureurl != null }">
              <img src="${context }/images/${sports.pictureurl }" class="sportsImage">
            </c:when>
            <c:otherwise>
              <img class="nonSportsImage" src="./images/null.gif">
            </c:otherwise>
          </c:choose>
		<div class="context">
        	<div class="checkcnt">
            	<input type="hidden" id="user_id" value="${user_id }">
                <input type="hidden" id="sport_type" value="${sports.sport_type }">
                <button class="likebtn" onclick="likecheck(this.value)" value="${sports.exnum }">
                <c:choose>
                	<c:when test="${sports.check > 0 }">
               			<img id="imgcheck" src="./images/star.png" >
               		</c:when>
               		<c:otherwise>
              			<img id="imgcheck" src="./images/empty_star.png">
              		</c:otherwise>
              	</c:choose>
               	</button>
               	<span id="likecnt">${sports.checkcnt }</span><span>명이 좋아합니다.</span>
             </div>
          	<span id="exname">${sports.exname }</span><br>
           	 위치 : ${sports.loc }<br>
         	 전화번호: ${sports.tel }<br>
          	 영업시간 : ${sports.time }<br>
          	 <a id="site" href="${sports.homeurl}">사이트로 연결</a><p>
          	 소개
          	 <br> ${sports.excontext }<br>
          </div>
        </div>	
      </c:forEach>
    </c:if>
  </div>
  <c:if test="${totCnt == 0}">
   센터가 없넹
  </c:if>
</section>
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>