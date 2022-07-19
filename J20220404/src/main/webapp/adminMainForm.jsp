<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminMainForm</title>
<link rel="stylesheet" href="css/adminMainForm.css" type="text/css">
</head>
<body>

	<jsp:include page="css/header.jsp"></jsp:include>
	<div class="adminmainform">
	<aside>
		<ul>
			<li><a class="menu">센터관리</a></li>
			<li><a href="adminSports.do">센터조회</a></li>
		</ul>
		<ul>
			<li><a class="menu">커뮤니티관리</a></li>
			<li><a href="reviewList.do?pageNum=${pageNum }&com_type=101">후기</a></li>
			<li><a href="qaList.do?pageNum=${pageNum }&com_type=101">질문</a></li>
			<li><a href="foodList.do?pageNum=${pageNum }&com_type=103">식단</a></li>
			<li><a href="etcList.do?pageNum=${pageNum }&com_type=104">잡담</a></li>
			
		</ul>
		<ul>
			<li><a class="menu">통계관리</a></li>
			<li><a href="adminInterSports.do">관심스포츠</a></li>
			<li><a href="adminCheckSports.do">찜한스포츠</a></li>
			<li><a href="adminOutuser.do">회원탈퇴사유</a></li>
		</ul>
	</aside>
	<section>
		<img id="admin" alt="adminMainImage" src="images/adminmainimage.png">
		<button class="logButton" onclick="window.open
			('adminLogForm.do', 'Admin Log View', 'width=750, height=400, top=250, left=400, location=yes, status=no, scrollbars=yes, resizable=yes');"> 	
		LOGIN 기록보기</button>
		
	</section>
	</div>
	<jsp:include page="css/footer.jsp"></jsp:include>

</body>
</html>