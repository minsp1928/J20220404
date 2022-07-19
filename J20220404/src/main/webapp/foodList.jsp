<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="css/maincss.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="dao.Com" %>
<!DOCTYPE html>
<html>
<%
	String context = request.getContextPath();
%>
<title>식단 게시판</title>
<head>
<link rel="stylesheet" type="text/css" href="css/reListStyle.css">

</head>
<body>

<jsp:include page="css/header.jsp"></jsp:include>
<div class="wholerList">
<section>
	<div class="bars">
		<div style="width: 78.078px;"></div>
		<table id="bar">
			<tr>  
				<td class="t2"><a href="reviewList.do?com_type=101"> 후기 </a></td>
				<td class="t2"><a href="qaList.do?com_type=102"> 질문</a></td>
				<td class="t1"><a href="foodList.do?com_type=103">식단</a> </td>
				<td class="t2"><a href="etcList.do?com_type=104">잡담</a></td>
				</tr>
				
		</table>
		<div class="w">
			<a class="write" href="WriteForm.do"><font size="white"></font> 글쓰기</a>
			<%-- <input type="hidden" name="com_type" value="${com_type }"> --%>
			<!--글쓰기 버튼 카테고리 밑 , 글내용탭 오른쪽 고정 -> write로 이동-->
		</div>		
	</div>
	
	
	<c:if test="${totCnt > 0 }">
	
	<c:forEach var="reviewList" items="${reviewList }" >
		<div class="main1">
			<div class="main2">
					<div id="name">
					<h6>식단</h6>
					</div>
					<div id="subject"><a href="content.do?num=${reviewList.num }&pageNum=${pageNum}">${reviewList.subject } </a></div>
				<div class="main3">
					<div>조회수: ${reviewList.count } </div>
					<div>작성자: ${reviewList.user_id}</div>
				</div>
			</div>
			<c:if test="${reviewList.poto != null }">
				<div id="poto">
					<span class="p"> <img alt="" src="<%=context%>/fileSave/${reviewList.poto}${filename}"></span>
				</div>
			</c:if>
			<c:if test="${reviewList.poto == null }">
				<div class="content">
					<span>${reviewList.content} </span>
				</div>
			</c:if>
			<c:forEach var="rehashList" items="${rehashList }">
				<c:if test="${fn:contains(reviewList.num, 'inNumstr') }"><span class="tag tag1">
					<c:out value="# ${rehashList.hsre_content }" /></span>
				</c:if>
			</c:forEach>
			<hr style="border:solid 1px #c4dcff;">	
			<%--  <br> 본문 : ${reviewList.content}<br> --%>
			
			<div id="re_coment">
				<img alt="댓글" src="images/re_coment.png"> 
				<div class="re_content" >${reviewList.re_max_content }
			 		<c:choose>
						<c:when test="${empty reviewList.re_max_content }"> </c:when>
						<c:otherwise>작성자 : ${reviewList.re_user_id } </c:otherwise>	
					</c:choose>
				</div>
			</div>
		</div>	
    <c:set var="startNum" value="${startNum - 1 }" />   
	</c:forEach>
   	<br> 
	</c:if>
	
	
   <div style="text-align: center;">
      <c:if test="${startPage > blockSize }">
         <a href='foodList.do?pageNum=${startPage-blockSize }'>[이전]</a>
      </c:if>
      <c:forEach var="i" begin="${startPage }" end="${endPage }">
         <a href='foodList.do?pageNum=${i}'>[${i}]</a>
      </c:forEach>
      <c:if test="${endPage<pageCnt }">
         <a href='foodList.do?pageNum=${startPage-blockSize }'>[다음]</a>
      </c:if>
   </div>



</section>
</div>	
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>