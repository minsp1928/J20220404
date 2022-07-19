<%@page import="dao.Com"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="css/maincss.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정alert</title>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("수정되었습니다");
		location.href = "content.do?num=${num}&pageNum=${pageNum}&user_id=${user_id}&filename=${filename}&content=${content}";
	</script>

</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("수정이 되지않았습니다");
		location.href = "updateForm.do?num=${num}&pageNum=${pageNum}&user_id=${user_id}&filename=${filename}&content=${content}";
	</script>
</c:if>
</head>
<body>

</body>
</html>