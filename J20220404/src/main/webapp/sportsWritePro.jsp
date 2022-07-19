<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<title>Insert title here</title>
<link href="css/sports.css" rel="stylesheet" type="text/css">
 
<head></head>
<body>
<jsp:include page="css/header.jsp"></jsp:include>
<section>
	<c:if test="${result > 0 }">
		<script type="text/javascript">
			alert("센터가 추가되었습니다");
		    location.href='adminSports.do?sport_type=${sport_type}';
		</script>
	</c:if>
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert("센터 추가에 실패하였습니다");
			location.href='sportsWriteForm.do?sport_type=${sport_type}';
		</script>
	</c:if>
</section>
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>