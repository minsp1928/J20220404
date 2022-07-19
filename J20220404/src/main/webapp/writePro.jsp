<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<title>Insert title here</title>
<head></head>
<body>
   <jsp:include page="css/header.jsp"></jsp:include>
   <section>
      <c:if test="${result>0 }">
         <script type="text/javascript">
            alert("작성완료");
            location.href="reviewList.do?pageNum=${pageNum}&filename=${filename}";
         </script>
      </c:if>
      
      <c:if test="${result == 0 }">
         <script type="text/javascript">
            alert("게시물 작성 오류");
            location.href="WriteForm.do?num=${num}&pageNum=${pageNum}&com_type=${com_type }";
         </script>
      </c:if>   
   
   </section>
   <jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>