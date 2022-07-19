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
<c:if test="${result > 0 && result2 > 0}">
	<script type="text/javascript">
		alert("탈퇴 완료  ");
		location.href="index.do";
	</script>
</c:if>
<c:if test="${result == 0 && result2 == 0}">
	<script type="text/javascript">
		alert("탈퇴되지 않았습니다. 다시 진행해 주세요.");
		location.href="outForm.do";
	</script>
</c:if>
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>