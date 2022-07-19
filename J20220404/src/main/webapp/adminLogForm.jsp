<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그기록</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<h2>관리자ID 로그인기록</h2>
	
	<table border="1">
	  <tr height="50">
	  	<td width="200" align="center">접속일자</td>
	  	<td width="200" align="center">아이디</td>
	  	<td width="300" align="center">접속IP</td>
	  </tr>
	<c:forEach items="${adminLogView}" var="adminLogView" varStatus="i">
	  <tr height="30">
	   	<td width="200" align="center"><c:out value="${adminLogView.admin_date}"/></td>
	  	<td width="200" align="center">admin</td> 
	  	<td width="300" align="center"><c:out value="${adminLogView.admin_ip}"/></td>
	  </tr>
	</c:forEach>
	<c:if test="${fn:length(adminLogView) eq 0}">
	  <tr height="30">
	  	<td colspan="3" align="center">조회 내용이 없습니다.</td>
	  </tr>
	</c:if>
	</table>

</body>
</html>
</body>
</html>