<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<%
String context = request.getContextPath();
%>
<html>
<title>Insert title here</title>
<link href="css/sportsWrite.css" rel="stylesheet" type="text/css">
<head>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function checkSports(num) {
	    var sport_type = num;
	    var sendData = 'sport_type='+sport_type;
		    $.ajax({
		      url:"<%=context%>/ajaxSportsWrite.do",
		      data: sendData,
		      dataType:'text',
		      success:function(data){
		      	$(document).ready(function () {
					$("#exnum2").val(data);
				})
		      }
		    });
	  };
	  
	$(document).ready(function () {
		$('#context_area').on('keyup', function () {
			$('#cnt').html("("+$(this).val().length+" / 150)");
			
			if($(this).val().length > 150) {
				$(this).val($(this).val().substring(0, 150));
				$('#cnt').html("(150/150)");
			}
		});
	}); 
	 var sport_type = $('#sport_type').val();
	$(function () {
		$("#default").val(sport_type).prop("selected", true);
	})
</script>
</head>

<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	<section>	
	 <input type="hidden" id= "sport_type" value="${sport_type }">
	<div class="wrap">
		<div class="centerInsert">
			<div class="centerImg"><img alt="kimi" src="./images/icon.png"></div>
			센터추가하기<p>
		</div>
		<form action="sportsWritePro.do?sport_type=${sport_type}" method="post" enctype="multipart/form-data">
			<table class="writeTbl">
				<tr>
				 	<th>센터타입</th>
				 	<td><select name="sport_type" id="default" onchange="checkSports(this.value)">
					 		<option value="" selected disabled hidden>선택해주세요</option>
					 		<option value="1">1.헬스</option>
							<option value="2">2.수영</option>
					 		<option value="3">3.요가/필라</option>
					 		<option value="4">4.골프</option>
					 		<option value="5">5.기타</option>
					    </select></td>
				</tr>
				<tr><th>센터번호</th>
					<td><input type="text" name="exnum" id="exnum2" value="${exnum}" readonly="readonly"></td>			
				</tr>
				<tr><th>*센터명</th>
				 	<td><input type="text" name="exname" required="required"></td>
				</tr> 
				<tr><th>영업시간</th>
				 	<td><input type="text" name="time"></td>
				</tr>
				<tr><th>*위치</th>
				 	<td><input type="text" name="loc" required="required"></td>
				</tr>
				<tr><th>홈페이지</th>
				 	<td><input type="text" name="homeurl"></td>
				</tr>
				<tr><th>전화번호</th>
				 	<td><input type="text" name="tel"></td>
				</tr>
				<tr>
					<th>*소개글</th>
				 	<td><textarea id="context_area" rows="15" cols="10" class="sports_text" placeholder="150자 이내로 입력하세요" name="excontext" required="required"></textarea><br>
				 		<div id="cnt"></div></td>
				</tr>
				<tr>
				 	<th>센터이미지</th>
				 	<td><input type="file" name="pictureurl"></td>
				</tr>
		 	</table>
			<div class="submit">
				<input type="submit" value="확인">
				<input type="reset" value="취소">
				<input type="button" value="목록보기" onclick="location.href='adminSports.do?'">
			</div>
		</form>
	</div>
	</section>
	<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>