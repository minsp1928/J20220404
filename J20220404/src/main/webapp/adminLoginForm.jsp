<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <style type="text/css">
	body {text-align: center;}
	table {background-color: #BFA0ED;
		   margin: auto;}
	tr {font-size: 24; }
</style> -->
<link rel="stylesheet" type="text/css" href="css/loginForm.css">
</head>
<body>
<body>
<jsp:include page="css/header.jsp"></jsp:include>
<form action="adminLoginPro.do" method="post">
<section>
<h3 class="login">관리자로그인</h3>
<table border="1" class="login_table">
	<tr><td>아이디</td>
	<td><input type="text" name="admin_id" class="admin_id" required="required"></td></tr>
	<tr><td>암호</td>
	<td><input type="password" name="admin_ps" required="required"></td></tr>
</table>
<input type="submit" id="sub_button" value="확인">
<input type="button" id="btn" value="회원 로그인" onclick="location.href='loginForm.do'">
</form><p>
</section>

<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>