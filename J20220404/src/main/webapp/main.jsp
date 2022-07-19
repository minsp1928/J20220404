<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="css/maincss.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<title>Insert title here</title>
<head></head>
<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	<section>
    	
		
		<c:choose>
		
			<c:when test="${empty user_id && empty admin_id}">
				<div class="button-area">
		        	<div class="button" onclick="alert('로그인 후 이용해주세요') ">후기</div>
		        	<div class="button" onclick="alert('로그인 후 이용해주세요')"> 질문 </div>
		        	<div class="button" onclick="alert('로그인 후 이용해주세요')"> 식단 </div>
		        	<div class="button" onclick="alert('로그인 후 이용해주세요')"> 잡담 </div>
		        </div>
	        </c:when>
	        
			<c:otherwise>
				<div class="button-area">
		        	<div class="button" onclick="location.href='reviewList.do'"> 후기 </div>
		        	<div class="button" onclick="location.href='qaList.do'"> 질문 </div>
		        	<div class="button" onclick="location.href='foodList.do'"> 식단 </div>
		        	<div class="button" onclick="location.href='etcList.do'"> 잡담 </div>
		        </div>
	        </c:otherwise>
	        
	    </c:choose>
	    
	    <c:choose>
	    	<c:when test="${empty user_id && empty admin_id}">
		    	<div class="search-box">
					<form action="main.do">
						<input type="text" class="search-txt" name="search" placeholder="검색어를 입력해주세요"
							pattern=".{2,1000}" required title="두 글자 이상 입력하세요"> 
						<input type="submit" class="search-btn" onclick="alert('로그인 후 이용해주세요')">
					</form>
				</div>
	    	</c:when>
	    	<c:otherwise>
	    		<div class="search-box">
					<form action="search.do">
						<input type="text" class="search-txt" name="search" placeholder="검색어를 입력해주세요"
							pattern=".{2,1000}" required title="두 글자 이상 입력하세요"> 
						<input type="submit" class="search-btn" >
					</form>
				</div>
	    	</c:otherwise>
	    </c:choose>
 		
 		<c:choose>
 			<c:when test="${empty user_id && empty admin_id}">
	 			<div class="baner">
					<button id="space1" onclick="alert('로그인 후 이용해주세요')">					
						<img id="health1space" src="images/health2.jpg" >
					</button>
					<button id="space2" onclick="alert('로그인 후 이용해주세요')">					
						<img id="swimming2space" src="images/swimming2.jpg" >
					</button>
					<button id="space3" onclick="alert('로그인 후 이용해주세요')">					
						<img id="yoga3space" src="images/yoga4.jpg" >
					</button>			
					<button id="space4" onclick="alert('로그인 후 이용해주세요')">
						<img id="golf4space" src="images/golf.jpg" >
					</button>		
				</div>
			</c:when>
			<c:otherwise>
 			<div class="baner">
				<button id="space1" onclick="location.href='sportsTypeForm.do?sport_type=1'">
					<img id="health1space" src="images/health2.jpg" >
				</button>
				<button id="space2" onclick="location.href='sportsTypeForm.do?sport_type=2'">
					<img id="swimming2space" src="images/swimming2.jpg"  >
				</button>
				<button id="space3" onclick="location.href='sportsTypeForm.do?sport_type=3'">
					<img id="yoga3space" src="images/yoga4.jpg"  >
				</button>			
				<button id="space4" onclick="location.href='sportsTypeForm.do?sport_type=4'">
					<img id="golf4space" src="images/golf.jpg">
				</button>		
			</div>			
			</c:otherwise>
		</c:choose>	
		<div class="floating">
			<div onclick="location.href='#title'">Top</div>
			<div onclick="location.href='#space1'">헬스</div>
			<div onclick="location.href='#space2'">수영</div>
			<div onclick="location.href='#space3'">요가& 필라테스</div>
			<div onclick="location.href='#space4'">골프</div>
		</div>

	</section>

    <jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>