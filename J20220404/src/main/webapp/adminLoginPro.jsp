<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {background-color: #90baff;}
	tr {font-size: 24; }
</style>
</head>
<body>
<jsp:include page="css/header.jsp"></jsp:include>

<c:if test="${result == 1 }">
	<script type="text/javascript">
		alert("로그인 성공\n환영합니다^^");
		location.href = "main.do";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("비밀번호를 다시 확인해 주세요.");
		location.href = "adminLoginForm.do";
	</script>
</c:if>
<c:if test="${result == -1 }">
	<script type="text/javascript">
		alert("존재하지 않는 아이디입니다.");
		location.href = "adminLoginForm.do";
	</script>
</c:if>
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>