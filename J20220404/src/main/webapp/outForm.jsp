<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/outForm.css">
</head>
<body>
<jsp:include page="css/header.jsp"></jsp:include>
<form action="outPro.do">
<section>
<h3 class="outForm">탈퇴하려면 암호를 입력하시오</h3>
	<table class="outForm_table" border="1">
			<tr><td>비밀번호</td>   
			<td><input type="password" name="user_ps"></td></tr>
			<tr>
				<td>탈퇴 이유</td>
				<td class="out_Reason">
					<select name="unregister_reason">
						<option value="1" selected="selected">거주지 변경</option>                        <!-- 콤보 박스 -->
						<option value="2">타사이트 이용</option>
						<option value="3">기타</option>
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" id="sub_button" value="확인">
				    <input type="hidden" name="user_id" value="${user_id }"></td>
			</tr>
	</table>
	</section>
</form>
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>