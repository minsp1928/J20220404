<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="css/maincss.jsp" %>
<% 
	String context = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/psCheckPro.css">
</head>
<body>
<jsp:include page="css/header.jsp"></jsp:include>
<section>
<table class="idChPro_table" border="1">
	<h3 class="idChPro"> 비밀번호 찾기 결과 : </h2>
	<tr><td><input type="text" name="user_ps" value="${member.user_ps}"></td></tr>
	<tr><td><input type="button" id="log_button" value="로그인하기" onclick="location.href='loginForm.do'">
	        <input type="button" id="psCh_button" value="아이디찾기" onclick="location.href='idCheckForm.do'"></td></tr>
</table>
</section>
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>