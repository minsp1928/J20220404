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
<link rel="stylesheet" type="text/css" href="css/myPage.css">
</head>
<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	<section>
	<h2 class="myPage">마이 페이지</h2>
		<form action="main.do?user_id=${user_id}" method="post">
			<input type="hidden" name="user_id" value="${member.user_id}">
			<table class="myPageTbl" border="1">
					
				<tr>
					<td>아이디</td>
					<td>${member.user_id}</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>${member.user_ps}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${member.uname}</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>${member.id_num}</td>
				</tr>
				<tr>
					<td>성별</td>
					<td class="gender">
					<span>남자</span><input type="radio" name="gender" <c:if test="${member.gender==1}">checked="checked"</c:if>
						            value="1" onclick="return (false)"><br> <!-- 사용자가 남성이 많은 경우 -->
					<span>여자</span><input type="radio" name="gender" <c:if test="${member.gender==2}">checked="checked"</c:if>
						             value="2" onclick="return (false)"><br>
			        </td>
				</tr>
				<tr>
					<td>회원사진</td>
					<td><img src="<%=context %>/fileSave/${member.user_picture}"
						alt="회원사진" onclick="return (false)"><br> <%-- <input type="file" name="user_picture" value="${member.uname}"> --%>
					</td>
				</tr>
				<tr>
					<td>연락처</td>
					<td>${member.tel}</td>
				</tr>
				<tr>
					<td>관심 운동 종목</td>
					<td class="workout">
					<span>필라/요가</span><input type="radio" name="sport_type" <c:if test="${inter.sport_type==1}">checked="checked"</c:if> value="1" onclick="return (false)"><br>
					<span>헬스</span><input type="radio" name="sport_type" <c:if test="${inter.sport_type==2}">checked="checked"</c:if> value="2" onclick="return (false)"><br> 
					<span>수영</span><input type="radio" name="sport_type" <c:if test="${inter.sport_type==3}">checked="checked"</c:if> value="3" onclick="return (false)"><br> 
					<span>골프</span><input type="radio" name="sport_type" <c:if test="${inter.sport_type==4}">checked="checked"</c:if> value="4" onclick="return (false)"><br> 
					<span>기타</span><input type="radio" name="sport_type" <c:if test="${inter.sport_type==5}">checked="checked"</c:if> value="5" onclick="return (false)"><br>
					</td>
				</tr>
				<tr>
				<td></td>
					<td colspan="2"><input type="submit" id="sub_botton" name="submit" value="확인"> 
					             	<input type="button" id="up_botton" name="update" value="수정하기" onclick="location.href='myUpdateForm.do'"> 
					             	<input type="button" id="out_botton" name="out" value="탈퇴하기" onclick="location.href='outForm.do'"></td>
				</tr>
			</table>

		</form>
	</section>
	<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>