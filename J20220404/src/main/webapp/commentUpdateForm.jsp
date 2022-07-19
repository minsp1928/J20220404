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
      $('#finish').click(function() {
         var sendData = $('form').serialize();
         /* alert('sendData -- > ' + sendData); */
         alert("수정완료");
         window.close();
         opener.parent.location="commentUpdatePro.do?"+sendData;
      });
      
   });
</script>
</head>
<body>
   <section>
      <form action="">
         <h2>댓글 수정</h2>
         <input type="hidden" name="recnnum" value="${recnnum }"> 
         <input type="hidden" name="comnum" value="${comnum }"> 
         <input type="hidden" name="pageNum" value="${pageNum }"> 
         <input type="hidden" name="ref" value="${com.ref}">
         <input type="hidden" name="re_level" value="${com.re_level }">
         <input type="hidden" name="re_step" value="${com.re_step }">
         <textarea rows="2" cols="40" name="re_content" placeholder="댓글을 입력하세요"></textarea><p>
         <input type="button" name="finish" value="수정완료" id="finish"> 
         <input type="reset" name="recet" value="다시작성" id="recet">
      </form>
   </section>
   <jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>