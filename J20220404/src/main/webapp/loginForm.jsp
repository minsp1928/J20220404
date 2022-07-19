<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/loginForm.css">
</head>
<body>
<body>
<jsp:include page="css/header.jsp"></jsp:include>
<form action="loginPro.do" method="post">
<section>
<h3 class="login">로그인</h3>
<table class="login_table" border="1">
	<tr><td>아이디</td><td><input type="text" name="user_id" class="text"  
		required="required"></td></tr>
	<tr><td>암호</td><td><input type="password" name="user_ps" class="text"
		required="required"></td></tr>
</table>

<input type="submit" id="sub_button" name="submit" value="로그인">
<p>
<input type="button" id="join_button" value="회원가입" onclick="location.href='joinForm.do'"> 
<input type="button" id="idCheck_button" value="아이디 찾기" onclick="location.href='idCheckForm.do'">
<input type="button" id="psCheck_button" value="비밀번호 찾기" onclick="location.href='psCheckForm.do'">
</form><p>
</section>	
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>