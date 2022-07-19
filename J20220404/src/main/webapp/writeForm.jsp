<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="css/maincss.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" type="text/css" href="css/writeStyle.css">
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
   function chk() {
      if($("#select option:selected").val() =="none"){
         alert("게시판 유형을 선택해주세요");
         form.com_type.focus();
         return false;
      }
      return true;
   }
</script>
</head>
<body>
<jsp:include page="css/header.jsp"></jsp:include>
<section>
   <form action="writePro.do?pageNum=${pageNum }" method="post" enctype="multipart/form-data" name="form" onsubmit="return chk()">
      <input type="hidden" name="num" value="${num }">
      <input type="hidden" name="ref" value="${ref }"> <!-- 0 -->
      <input type="hidden" name="re_step" value="${re_step }"> <!-- 0 -->
      <input type="hidden" name="re_level" value="${re_level }"><!-- 0 -->
      <input type="hidden" name="user_id" value="${user_id }">
      
      <div class=writeform>
         <select name="com_type" id="select" >
            <option value="none">=선택=</option>
            <option value="101" >후기</option>
            <option value="102">질문</option>
            <option value="103">식단</option>
            <option value="104">잡담</option>
         </select>
         
         <input type="text" name="subject" placeholder="제목을 입력하세요" required="required">
      <hr>
      </div>
      
         <div class=file><input type="file" name="poto">   </div>
         
         <div class=writeform>
            <textarea rows="10" cols="30" id="summernote" name="content" placeholder="내용을 입력하세요" required="required"></textarea>
         </div>
   
   
      <div class="finish">
         <input class="submit" type="submit" value="완료">
         <input class="reset" type="reset" value="다시작성">
      </div>
   </form>
   
</section>
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>