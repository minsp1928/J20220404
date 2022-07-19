<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="css/maincss.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	String context = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/myUpdateForm.css">
</head>
<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	<section>
		<h2 class="myUp">마이 페이지 수정하기</h2>
		<form action="myUpdatePro.do" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="user_id" value="${member.user_id}">
			<table class="myUpTbl" border="1">

				<tr>
					<td>아이디</td>
					<td>${member.user_id}</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" class="in2_tag" name="user_ps"
						value="${member.user_ps}"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" class="in2_tag" name="uname" value="${member.uname}"></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="number" class="in2_tag" name="id_num"
						value="${member.id_num}"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td class="gender">
					<span>남자</span><input type="radio" name="gender" <c:if test="${member.gender==1}">checked="checked"</c:if> value="1"><br> <!-- 사용자가 남성이 많은 경우 --> 
					<span>여자</span><input type="radio" name="gender" <c:if test="${member.gender==2}">checked="checked"</c:if> value="2"></td>
				</tr>
				<tr>
					<td>회원사진</td>
					<td><img src="<%=context %>/fileSave/${member.user_picture}" alt="회원사진"><br> 
						<input type="file" class="in2_tag" id="input_button" name="user_picture2" value="${member.user_picture}"> 
						<input type="hidden" name="user_picture" value="${member.user_picture}"></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="text" class="in2_tag" name="tel" value="${member.tel}"></td>
				</tr>
				<tr>
					<td>관심 운동 종목</td>
					<td class="sel_sport"><span>필라/요가</span><input type="radio"
						name="sport_type"
						<c:if test="${inter.sport_type==1}">checked="checked"</c:if>
						value="1"><br> <span>헬스</span><input type="radio"
						name="sport_type"
						<c:if test="${inter.sport_type==2}">checked="checked"</c:if>
						value="2"><br> <span>수영</span><input type="radio"
						name="sport_type"
						<c:if test="${inter.sport_type==3}">checked="checked"</c:if>
						value="3"><br> <span>골프</span><input type="radio"
						name="sport_type"
						<c:if test="${inter.sport_type==4}">checked="checked"</c:if>
						value="4"><br> <span>기타</span><input type="radio"
						name="sport_type"
						<c:if test="${inter.sport_type==5}">checked="checked"</c:if>
						value="5"><br></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input type="submit" id="sub_botton" value="수정하기"> 
				                 	<input type="reset" id="re_botton" value="다시 작성"></td>
			</table>

		</form>
	</section>
	<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>