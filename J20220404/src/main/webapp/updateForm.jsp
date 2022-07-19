<%@page import="dao.Com"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="css/maincss.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<%
   String context = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
<link href="css/update.css" rel="stylesheet" type="text/css">	

</head>
<body>
<jsp:include page="css/header.jsp"></jsp:include>
<section>
<form action="updatePro.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="num" value="${com.num }"> <!--반드시사용할 pk는 수정하지못하게 hidden-->
		<input type="hidden" name="pageNum" value="${pageNum }"><!--board에 없는 값이라서 그냥 pageNum, pk는 아니지만 사용을 하기때문에!!-->
		<input type="hidden" name="user_id" value="${user_id }">

		
		<h2>게시판 수정</h2>
		<div class=writeform>
		       <!--  <select  name="com_type" id="com_typeSelect">
		        	<option value="none">=선택=</option>
		            <option value="101">후기</option>
		            <option value="102">질문</option>
		            <option value="103">식단</option>
		            <option value="104">잡담</option>  ->수정할때 카테고리 선택X
		        </select> -->
	         	<input type="text" name="subject" placeholder="제목을 입력하세요" required="required" value="${com.subject }">
	      		<hr>
	      		<!--글사진-->
					<span class="p">&nbsp&nbsp&nbsp <img src="<%=context%>/fileSave/${com.poto}">
					<input type="hidden" name="dbpoto" value="${com.poto}" > 
		
	      	    <div class=file><input type="file" name="poto"></div>
	         	<textarea rows="10" cols="30" name="content" placeholder="내용을 입력하세요" required="required">${com.content }</textarea>
	    </div>
     	<div class="finish">
     		 <input class="submit" type="submit" value="수정완료">
     		 <input class="reset" type="reset" value="다시작성">
 		</div>
			
</form>
</section>
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>