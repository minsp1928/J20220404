<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="css/maincss.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
   $(function() {
      $('#delete').click(function() {
         var sendData = $('form').serialize();
         //alert('sendData -- > ' + sendData);
         location.href = "deletePro.do?" + sendData;
      });
      $('#cancel').click(function() {
         window.close();
      });
   });
</script>
</head>
<body>
   <jsp:include page="css/header.jsp"></jsp:include>
   <section>
      <form action="">
         <h2>삭제하시겠습니까?</h2>
         <input type="hidden" name="num" value="${num }"> 
         <input type="hidden" name="pageNum" value="${pageNum }"> 
         <input type="hidden" name="user_id" value="${user_id }">
         <input type="button" name="delete" value="삭제" id="delete"> 
         <input type="button" name="cancel" value="취소" id="cancel">
      </form>
   </section>
   <jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>