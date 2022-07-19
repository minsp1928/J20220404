<%@page import="dao.Com"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="css/maincss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<%
String context = request.getContextPath();
%>
<title>게시글상세페이지</title>
<link rel="stylesheet" type="text/css" href="css/content.css">

<head>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	 function winop() {//글삭제 
	    window.open("deleteForm.do?num=${com.num}&pageNum=${pageNum }", "",
	          "width =700 height =400");
	 }
	 
	 function fn_commentUpADe(stsIndex){//댓글수정
	    //alert('fn_commentUpADe sstsIndex->' + stsIndex);
	    var sendData = $('form#commentUpADe'+stsIndex).serialize();
	    //alert('fn_commentUpADe sendData update->' + sendData);
	     window.open("commentUpdateForm.do?"+sendData ,"", "width = 800 height= 200");
	 }
	
	$(function() {
		$('#commWritebtn').click(function() { //댓글등록
			var com_num =  $('#comnum_coment').val();
			//alert("com_num->"+com_num);
			var re_content_val = $('#commentarea').val();
			if(re_content_val==""){
				alert("내용을 입력하세요");
				comment.re_content.focus();
				return;
			}
			var sendData = $('form#comment').serialize();
			/* alert("sendData->"+sendData); */
			if (confirm('댓글을 등록하시겠습니까?')) {
				// location.href = "commentWrite.do?comnum="+com_num+"&pageNum=${pageNum}";
				location.href = "commentWrite.do?"+sendData;
			} else {
				location.href = "content.do?num=${com.num}&pageNum=${pageNum}";
			}
		});
	});

	function commDeletebtn1(recnnum,comnum,pageNum) {//댓글삭제
		/* var recnnum = $('#recnnum').val(); */
		/* var pageNum = $('pageNum').val(); */
		/* var comnum = $('#comnum').val(); */
		if (confirm('댓글을 삭제하시겠습니까?')) {
			//alert("pageNum->"+pageNum);
			location.href = "commentDelete.do?pageNum="+pageNum+"&recnnum="+recnnum+"&comnum="+comnum; 
		} else {
			alert('삭제 취소');
		}
	}

</script>
</head>
<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	<section>
		
		<div id="comCategorie">${com_type_str}</div>
		<p>
		<div id="comSubject">&nbsp${com.subject}</div>
		<!--글제목-->
		<div id="comIdReg_date">&nbsp&nbsp&nbsp${com.user_id}&nbsp&nbsp${com.reg_date}</div>
		<!--아이디, 작성일-->

		<hr>
		<!--글사진-->
		<c:if test="${poto!=null }">
			<span class="p">&nbsp&nbsp&nbsp <img src="<%=context%>/fileSave/${poto}">
		</c:if>
		<c:if test="${poto==null }"></c:if>
		</span>
		<!-- 글내용 해시태그  -->
		<div id="content">&nbsp&nbsp&nbsp${content}</div>
		<input type="hidden" name="content" value=${content }>
		<p>
		<p>
		<div id="hashtag">
			&nbsp&nbsp
			<c:forEach var="hash" items="${hashList}">
				<!--list는 foreach문으로-->
						 #${hash.hsre_content }
			</c:forEach>
		</div>
		<hr>
		&nbsp&nbsp&nbsp댓글&nbsp${comCnt }
		<!--댓글수-->
		<hr>


		<div>
			<!--댓글의 아이디, 댓글-->
			<c:forEach var="recn" items="${recnList}" varStatus="status">
				<p></p>
					<!--list는 foreach문으로-->
				<div class="commentAll">
					<div>
					<p id="recnUser_id">&nbsp&nbsp&nbsp${recn.user_id}</p>
					<p id="recnRe_content">&nbsp&nbsp&nbsp${recn.re_content }</p>
				</div>
				
				<c:if test="${recn.user_id==user_id }">
					<div class="commUA">
						<!--댓글 수정 버튼-->
						  <form action="" id="commentUpADe${status.index }" name="commentUpADe${status.index }">
		                     <input type="hidden" name="recnnum" value="${recn.num }" id="recnnum${status.index }"> 
		                     <input type="hidden" name="comnum" value="${com.num }" id="comnum${status.index }" >
		                     <input type="hidden" name="pageNum" value="${pageNum }" id="pageNum${status.index }"> 
		                     <input type="button" id="commupdate" value="수정" name="commupdate" onclick="fn_commentUpADe(${status.index })">
		                  </form>
						<p></p>
						<!--댓글 삭제 버튼-->
							<form action="" id="commDelete">
								<input type="hidden" name="recnnum" value="${recn.num }">
								<input type="hidden" name="comnum" value="${com.num }"> 
								<input type="hidden" name="pageNum" id="pageNum" value="${pageNum }"> 
								<input type="hidden" name="re_step" value="${recn.re_step }">
								<input type="hidden" name="re_content" value="${recn.re_content }"> 
								<input type="button" onclick="commDeletebtn1(${recn.num},${com.num },${pageNum })" value="삭제" id="commDeletebtn" name="commDeletebtn">
							</form>
					</div>
				</c:if>
				</div>
				
				<c:if test="${recn.user_id!=user_id }">
					<c:if test="${admin_id != null}">
						<form action="" id="commDelte">
							<input type="button" name="commDeletebtn" value="삭제"
								id="commDeletebtn">
						</form>
					</c:if>

				</c:if>
				<hr>
			</c:forEach>
		</div>
		
		<!--댓글입력창-->
		<form action="" name="comment" id="comment">
			<div class="commentWrite">
				<textarea rows="5" cols="80" id="commentarea" required="required" name="re_content" placeholder="&nbsp&nbsp댓글을 입력해주세요"></textarea>
				<input type="button" name="commWritebtn" value="등록" id="commWritebtn" required="required">
				<input type="hidden" name="recnnum" value="${recn.num }">
				<input type="hidden" name="comnum" id="comnum_coment" value="${com.num }"> 
				<input type="hidden" name="pageNum" value="${pageNum }"> 
			</div>
		</form>
		<p></p>
		<p></p>

		<div>
			<!--글 수정update로 넘어가는 버튼 -->

			<c:if test="${user_id==com.user_id }">
				<input id="updateButton" type="button" value="글 수정"
					onclick="location.href='updateForm.do?num=${com.num}&pageNum=${pageNum }'"></input>
			<!--글 삭제delete로 넘어가는 버튼 -->	
				<input id="deleteButton" name="delete" type="button" value="글 삭제"
					onclick="winop()"></input>
			</c:if>
			<c:if test="${user_id!=com.user_id }">
				<c:if test="${admin_id != null}">
					<input id="deleteButton" name="delete" type="button" value="글 삭제"
						onclick="winop()"></input>
				</c:if>
			</c:if>
		</div>

		<div>
			<!--리스트목록으로 돌아가는 버튼-->
			<input id="listBackButton" type="button" value="목록"
				onclick="location.href='reviewList.do?num=${com.num}&pageNum=${pageNum }'"></input>
		</div>
	</section>
	<jsp:include page="css/footer.jsp"></jsp:include>

</body>
</html>