<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/psCheckForm.css">
</head>
<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	<form action="psCheckPro.do" name="psCheck" method="post">
		<section>
			<h3 class="psCheck">비밀번호 찾기</h3>
			<table class="psCheck_table" border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="user_id" required="required"></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="id_num" placeholder="00000000"
						required="required"></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="tel" name="tel" required="required"
						pattern="\d{2,3}-\d{3,4}-\d{4}" placeholder="xxx-xxxx-xxxx"
						title="2,3자리-3,4자리-4자리"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" id="sub_button" name="submit" value="확인">
					    <input type="reset" id="res_button" name="reset" value="취소"></td>
				</tr>
			</table>
		</section>
	</form>
	<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>