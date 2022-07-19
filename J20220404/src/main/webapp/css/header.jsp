<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>4조의 첫 웹 개발</title>
<link href="css/header.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
		<div id="title">
			<a href="main.do" id="mainclick">우리 동네 건강 지키미</a>
		</div>
	
	<c:choose>
        <c:when test="${admin_id ne null}">
        	<button id="logout" onclick="location.href='logoutForm.do'">로그아웃</button>
			<button id="managerpage" onclick="location.href='adminMainForm.do'">마이페이지</button>
        </c:when>
    	<c:when test="${user_id ne null && admin_id eq null}">
	        <button id="logout" onclick="location.href='logoutForm.do'">로그아웃</button>
			<button id="mypage" onclick="location.href='myPage.do'">마이페이지</button>
    	</c:when>
    	<c:otherwise>
	        <button id="login" onclick="location.href='loginForm.do'">로그인</button>
	        <button id="managerlogin" onclick="location.href='adminLoginForm.do'">관리자로그인</button>
    	</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${empty user_id}">
		<div class="dropdown">
			<button class="dropbtn" ><img src="images/menu_bar.png"></button>
			<div class="dropdown-content">
				<a href="joinForm.do">회원가입</a>
				<a href="" onclick="alert('로그인 후 이용해주세요')">센터소개</a>
				<a href="" onclick="alert('로그인 후 이용해주세요')">커뮤니티</a>
			</div>
		</div>
		</c:when>
		
		<c:otherwise>
		<div class="dropdown">
			  <button class="dropbtn" ><img src="images/menu_bar.png"></button>
			  <div class="dropdown-content">
				  <a href="joinForm.do">회원가입</a>
				  <a href="locationForm.do">센터소개</a>
				  <a href="reviewList.do">커뮤니티</a>
			  </div>
		</div>
		</c:otherwise>	
	</c:choose>
	</header>
</body>
</html>