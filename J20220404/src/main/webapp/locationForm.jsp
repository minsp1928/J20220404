<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ include file="css/maincss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title>Insert title here</title>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=ctgqbpmeoz&submodules=geocoder"></script>
<head>
<link href="css/location.css" rel="stylesheet" type="text/css">
<%
	String context = request.getContextPath();
%>
<script type="text/javascript" src = "js/jquery.js"></script>
<script type="text/javascript">
	function likecheck(num) {
	    var user_id = $('#user_id').val();
	    var sport_type = $('#sport_type').val();
	    var exnum = num;
	    var check_list = {"user_id":user_id, "sport_type":sport_type, "exnum":exnum};
	    $.ajax({
	      url:"<%=context%>/imgcheck.do",
	      data: check_list,
	      dataType:'text',
	      success:function(data){
	      	if(data == "like") {
	      		 document.getElementById("imgcheck").src="./images/star.png";
	             const result = document.getElementById('likecnt');
	             let number = result.innerText;
	             number = parseInt(number)+1;
	             result.innerText = number;
	             location.reload();
	      	}
	      	if(data =="dislike"){
	      		 document.getElementById("imgcheck").src="./images/empty_star.png";
	             const result = document.getElementById('likecnt');
	             let number = result.innerText;
	             number = parseInt(number)-1;
	             result.innerText = number;
	             location.reload();
	      	}
	      }
	    });
	  };
	
</script>
</head>
<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	<section>
		<div class="type">
			<div class="type_white" onclick="location.href='locationForm.do?sport_type=1'">
				<a href='#'>위치</a>
			</div>
			<div class="type_blue" onclick="location.href='sportsTypeForm.do?sport_type=1'">
				<a href='#'>종목</a>
			</div>
		</div>

		<div class="buttons">
			<a href='locationForm.do?sportType=1' class="myButton">헬스</a> <a
				href='locationForm.do?sportType=2' class="myButton">수영</a> <a
				href='locationForm.do?sportType=3' class="myButton">요가/필라</a> <a
				href='locationForm.do?sportType=4' class="myButton">골프</a> <a
				href='locationForm.do?sportType=5' class="myButton">기타</a>
		</div>
		<div class="infomapmarker"><img alt="마커" src="images/pingyellow4.png" id="infomarker">지도의 마커를 눌러 센터를 확인하세요</div>
		<!-- ajax를 써서 이거 바꾸자. 굳이? -->
		<div class="container_loc">
			<c:if test="${totCnt > 0 }">
				<%-- <c:forEach var="sports" items="${list }"> --%>
				<div class="center">
					<%-- <c:choose>
						<c:when test="${list.pictureurl != null }">
							<img src="<%=context %>/images/${list.pictureurl }"
								class="sportsImage">
						</c:when>
						<c:otherwise>
							<img class="nonSportsImage" src="./images/null.gif">
						</c:otherwise>
					</c:choose> --%>
					<div class="context">
						<%-- <div class="checkcnt">
			            	<input type="hidden" id="user_id" value="${user_id }">
			                <input type="hidden" id="sport_type" value="${list.sport_type }">
			                <button class="likebtn" onclick="likecheck(this.value)" value="${list.exnum }">
			                <c:choose>
			                	<c:when test="${list.check > 0 }">
			               			<img id="imgcheck" src="./images/star.png" >
			               		</c:when>
			               		<c:otherwise>
			              			<img id="imgcheck" src="./images/empty_star.png">
			              		</c:otherwise>
			              	</c:choose>
			               	</button>
			               	<span id="likecnt">${list.checkcnt }</span><span>명이 좋아합니다.</span>
			            </div> --%>
						<p>
						<div id="exname"></div>
							<%-- ${list.exname }<br> 
							위치 : ${list.loc }<br> 
							전화번호: ${list.tel }<br> 
							영업시간 : ${list.time }<br>
							소개 <br> 
							${list.excontext } <br>
							<a href="${list.homeurl }">사이트로 연결</a> --%>
					</div>
				</div>
				<%-- </c:forEach> --%>
			</c:if>
			<c:if test="${totCnt == 0}">
  		  		센터가 없넹
  			</c:if>
			<div id="map"></div>
			<c:forEach var="list3" items="${list2 }" varStatus="status" >
				<input type="hidden" id="list${status.index}" value="${list3.loc }">
				<input type="hidden" id="Lat${status.index}" value=${Lat[status.index] }>
				<input type="hidden" id="Lng${status.index}" value=${Lng[status.index] }>
			</c:forEach>
			<input type="hidden" id="listsize" value="${listsize }">
			<script type="text/javascript">
				var list = [];
				var Lat = [];
				var Lng = [];
				var listsize = $('#listsize').val();
				for(var num = 0; num<listsize; num++){
					list.push($('#list'+num).val());
					Lat.push($('#Lat'+num).val());
					Lng.push($('#Lng'+num).val());
				}
				let markers = new Array();
				let infoWindows = new Array();
				$(function() {
						initMap();
				});//ready end
				
				function initMap() {
					// 지도 생성
					var map = new naver.maps.Map('map', {
						center : new naver.maps.LatLng(Lat[0], Lng[0]), // 지도를 열 좌표
						zoom : 16
					});
					for (var i = 0; i < listsize; i++) {
						var markerOptions = {
								position : new naver.maps.LatLng(Lat[i],Lng[i] ), //마커찍을 좌표
								map : map,
								icon : {
									url : 'images/pingyellow4.png', //아이콘 경로
								} // icon end
						};// markerOptions end
						var marker = new naver.maps.Marker(markerOptions);// 마커 생성
						markers.push(marker);
					}// for end
					function getClickHandler(seq) {
						return function(e) {
							var marker = markers[seq];
							naver.maps.Event.addListener(marker, 'click', function () {
								var user_id = $('#user_id').val();
								if(user_id == null){
									user_id = $('admin_id').val();
								}
								var sport_type = $('#sport_type').val();
								var bag_list = {"user_id":user_id, "sport_type":sport_type, 
												  "list":list[seq], "lng":Lng[seq], "lat":Lat[seq]};
								$.ajax({
									url:"<%=context%>/ajaxLocationForm.do",
									data : bag_list,
									dataType : 'text',
									success : function(data) {
										if (data != null) {
											const sport_list = document.getElementById('list_content');
											/* writeSportList = 
												this.exname+"<br>"+
												"위치 : "+this.loc+"<br>"+
												"전화번호: "+this.tel+"<br>"+
												"영업시간 : "+this.time+"<br>"+
												"소개 <br> "+this.excontext+"<br>"+
												"<a href='"+this.gethomeurl+"'>사이트로 연결</a>";
											 */
											// location.reload();
											/* $('#list_content').text(writeSportList); */
											$('#exname').html(data);
										} // if end
										
									} // success end
								}); // ajax end 
							});//map event end
						}
					}
					for(var i = 0; i<listsize; i++){
						console.log(markers[i], getClickHandler(i));
						naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
					}
				}
			</script>
		</div>

	</section>
	<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>