<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	<script type="text/javascript">
		alert("로그아웃 되었습니다.");
		location.href="logoutPro.do";
	</script>
	
	<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>