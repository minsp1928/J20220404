<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="css/maincss.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/* document.body.addEventListener('click', function() {
	console.log('click') */
	document.addEventListener("DOMContentLoaded",function() {
		var user_id = '${user_id}';
		var className = document.getElementsByClassName("subTableText");
		var className2 = document.getElementsByClassName("ContentTableText");
		var myFunction = function() {
			console.log("user_id=" + user_id);
			if(user_id == "") alert('로그인 후 이용해주세요');
			event.preventDefault();
		};
		 
		for (var i = 0; i < className.length; i++) {
			if(user_id == "")
				className[i].addEventListener('click', myFunction, false);
		}
		for (var i = 0; i < className2.length; i++) {
			if(user_id == "")
				className2[i].addEventListener('click', myFunction, false);
		}
	});
	

</script>
</head>
<body>
<jsp:include page="css/header.jsp"></jsp:include>
	<section class="search-page">	
		<div class="search-box" style="top: 5%;">
			<form action="search.do">
				<input type="text" class="search-txt" name="search" placeholder="검색어를 입력해주세요"
					pattern=".{2,1000}" required title="두 글자 이상 입력하세요"> 
				<input type="submit" class="search-btn" >
			</form>
		</div>
	
		<div class="searchsubject-header">제목으로 검색</div>
		<div class="search-subject">
			<table class="tableborder">
				<tr class="tableheader">
					<th id="tableSearch">검색 위치</th><th id="tableSubject">제목</th>
				</tr>
				<c:if test="${subCnt > 0 }">
					<c:forEach var="comSub" items="${comSub}">
						<tr class="subTableText">
							<td>${comSub.com_type_name}</td>
							<td><a href="content.do?num=${comSub.num}" style="cursor:pointer"> ${comSub.subject}</a></td>
						</tr>					
					</c:forEach>
					<c:forEach var="spoSub" items="${spoSub}">
						<tr class="subTableText">
							<td>${spoSub.sports_type_name}</td>
							<td> <a href="sportsTypeForm.do?sport_type=${spoSub.sport_type}" style="cursor:pointer"> ${spoSub.exname} </a></td>
						</tr>					
					</c:forEach>
				</c:if>
				<c:if test="${subCnt == 0 }">
					<tr>
						<td>검색 결과가 존재하지 않습니다</td>
					</tr>
				</c:if>	
			</table>
		</div>
		
		<div class="searchcontent-header">
		내용으로 검색
		</div>
		<div class="search-content">
		<table class="tableborder">
		<tr class="tableheader">
			<th id="tableSearch">검색 위치</th><th id="tableSubject">내용</th>
		</tr>
		<c:if test="${contentCnt > 0 }">
			<c:forEach var="comContent" items="${comContent}">
				<tr class="ContentTableText">
					<td>${comContent.com_type_name}</td>
					<td> <a href="content.do?num=${comContent.num}" style="cursor:pointer" target="_self"> ${comContent.content} </a></td>
				</tr>					
			</c:forEach>
			<c:forEach var="spoContent" items="${spoContent}">
				<tr class="ContentTableText">
					<td>${spoContent.sports_type_name}</td>
					<td> <a href="sportsTypeForm.do?sport_type=${spoContent.sport_type}" style="cursor:pointer" target="_self"> ${spoContent.excontext} </a></td>
				</tr>					
			</c:forEach>
		</c:if>
		<c:if test="${contentCnt == 0 }">
			<tr>
				<td>검색 결과가 존재하지 않습니다</td>
			</tr>
		</c:if>	
		</table>
		</div>
	
		
	</section>
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>