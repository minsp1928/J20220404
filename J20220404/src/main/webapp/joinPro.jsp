<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="css/header.jsp"></jsp:include>
	<c:if test="${result > 0}">
		<script type="text/javascript">
			alert("회원가입을 축하합니다.");
			location.href="index.do";
		</script>
	</c:if>
	<c:if test="${result == 0}">
	<script type="text/javascript">
			alert("가입실패");
			history.back(); 
		</script>
	</c:if>
	
	
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>