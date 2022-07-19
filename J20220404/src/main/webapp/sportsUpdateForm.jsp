<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<title>Insert title here</title>
<link href="css/sportsUpdate.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		$('#context_area').on('keyup', function () {
			$('#cnt').html("("+$(this).val().length+" / 150)");
			
			if($(this).val().length > 150) {
				$(this).val($(this).val().substring(0, 150));
				$('#cnt').html("(150/150)");
			}
		});
	}); 
</script>
<head></head>
<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	<section>
	<div class="wrap">
		<div class="centerInsert">
			<div class="centerImg"><img alt="kimi" src="./images/icon.png"></div>
			센터수정하기<p>
		</div>	
		<form action="sportsUpdatePro.do?" method="post" enctype="multipart/form-data">
			<input type="hidden" name="sport_type" value="${sports.sport_type }">
			<input type="hidden" name="exnum" value="${sports.exnum }">
			<table class="updateTbl">
				<tr>
				 	<th>센터타입</th>
				 	<c:choose>
				 		<c:when test="${sports.sport_type == 1}"><td>헬스</td></c:when>
				 		<c:when test="${sports.sport_type == 2}"><td>수영</td></c:when>
				 		<c:when test="${sports.sport_type == 3}"><td>요가/필라</td></c:when>
				 		<c:when test="${sports.sport_type == 4}"><td>골프</td></c:when>
				 		<c:when test="${sports.sport_type == 5}"><td>기타</td></c:when>
				 	</c:choose>
				</tr>
				 <tr><th>*센터명</th>
				 	 <td><input type="text" name="exname" required="required" value="${sports.exname }">
				 </tr> 
				  <tr><th>영업시간</th>
				 	 <td><input type="text" name="time" value="${sports.time  }"></td>
				 </tr>
				 <tr><th>*위치</th>
				 	 <td><input type="text" name="loc" required="required" value="${sports.loc }"></td>
				 </tr>
				 <tr><th>홈페이지</th>
				 	 <td><input type="text" name="homeurl" value="${sports.homeurl }"></td>
				 </tr>
				 <tr><th>전화번호</th>
				 	 <td><input type="text" name="tel" value="${sports.tel }"></td>
				 </tr>
				 <tr>
				 	<th>*소개글</th>
				 	<td><textarea id="context_area" rows="15" cols="10" class="sports_text" name="excontext" required="required">${sports.excontext }</textarea><br>
				 		<div id="cnt"></div>
				 	</td>
				 </tr>
				 <tr>
				 	<th>센터이미지</th>
				 	<td><input type="file" name="pictureurl" value="${sports.pictureurl }">${sports.pictureurl }</td>
				 </tr>
		 	</table>
		 	<div class="submit">
		 		<input type="submit" value="확인">
				<input type="reset" value="취소">
				<input type="button" value="목록보기" onclick="location.href='adminSports.do?'">
		 	</div>
		</form>
	</div>
	</section>
	<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>