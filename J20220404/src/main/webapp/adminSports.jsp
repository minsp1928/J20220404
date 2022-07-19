<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<title>Insert title here</title>
<link href="css/adminSports.css" rel="stylesheet" type="text/css">
<head></head>
<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	<section>
	<div class="wrap">
		<div class="centerInsert">
			<div class="centerImg"><img alt="kimi" src="./images/icon.png"></div>
			센터관리<p>
		</div>	

		<div class="buttons">
			<a href='adminSports.do?' class="myButton">전체</a>
			<a href='adminSports.do?sport_type=1' class="myButton">헬스</a>
			<a href='adminSports.do?sport_type=2' class="myButton">수영</a>
			<a href='adminSports.do?sport_type=3' class="myButton">요가/필라</a>
			<a href='adminSports.do?sport_type=4' class="myButton">골프</a>
			<a href='adminSports.do?sport_type=5' class="myButton">기타</a>
			<p>	
		</div>
		<table class="adminTable">
			<tr class="row">
				<th>No.</th><th>센터명</th><th>위치</th><th>전화번호</th><th>영업시간 </th><th colspan="2">관리</th> 
			</tr>
			<c:if test="${totCnt > 0 }">
				<c:forEach var="sports" items="${list }">
					<tr>
						<td>${startNum }</td>
						<td>${sports.exname }</td>
						<td>${sports.loc }</td>
						<td>${sports.tel }</td>
						<td>${sports.time }</td>
						<td><input class="Tbtn" type="button" value="수정" onclick="location.href='sportsUpdateForm.do?sport_type=${sports.sport_type}&exnum=${sports.exnum}'"></td>
						<td><input class="Tbtn" type="button" value="삭제" onclick="location.href='sportsDeletePro.do?sport_type=${sports.sport_type}&exnum=${sports.exnum}'"></td>
					</tr>
					<c:set var="startNum" value="${startNum +1 }"></c:set>
				</c:forEach>
			</c:if>	
		</table>
			<div class="insert">
				<p>
				<input  class="insertBtn" type="button" value="센터추가" 	onclick="location.href='sportsWriteForm.do?sport_type=${sport_type}'">
			</div>
		</div>

		<c:if test="${totCnt == 0}">
			센터가 없습니다.
		</c:if>
	</section>
	<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>