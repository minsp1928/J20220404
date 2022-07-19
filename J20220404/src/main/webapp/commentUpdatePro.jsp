<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="css/maincss.jsp"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title>Insert title here</title>
<head></head>
<body>
   <jsp:include page="css/header.jsp"></jsp:include>
   <section>
      <c:if test="${result >0 }">
         <script type="text/javascript">
            location.href="content.do?pageNum=${pageNum}&num=${comnum}"
         
         </script>
      </c:if>
      <c:if test="${result == 0 }">
         <script type="text/javascript">
            alert("수정실패");
            location.href = "commentUpdateForm.do?num=${num}&pageNum=${pageNum}";
         </script>
      </c:if>

   </section>
   <jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>