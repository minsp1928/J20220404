<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://www.google.com/jsapi"></script>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	google.load("visualization", "1", {
		"packages" : [ "corechart" ]
	}); //구글에서 제공하는 api를 불러오기
	google.setOnLoadCallback(drawChart); //차트패키지를 불러온 후 함수를 호출하라는 것
	function drawChart() {
		var rowData = '${rowsMap}';
		var data = new google.visualization.DataTable(rowData);
		// 차트 데이터 객체
		var chart = new google.visualization.PieChart(document
				.getElementById("chart_div")); // -> 파이차트형식으로 만들어라
		chart.draw(data, {
			title : "찜한 스포츠",
			width : window.innerWidth,
			height : 600
		});
	}
	//alert("${rowsMap}");
</script>
<link href="css/adminMainForm.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="css/header.jsp"></jsp:include>
	
	<section>
		<div id="chart_div"></div>
		<button class="logButtonRefresh" onclick="drawChart()">data refresh</button>
	</section>
	
	<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>