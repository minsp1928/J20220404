<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="css/maincss.jsp" %>
<!DOCTYPE html>
<html>
<%
	String context = request.getContextPath(); // context를 가져오기 위해 넣어줌. 여러 path를 사용하는 경우, 이걸 걸어주면 기본 path를 잡는다.
%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/joinForm.css">
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
	/* Controller */  
	function getUser_idCheck(){
		var vUser_id = $('#user_id').val();
		/* alert("vUser_id->"+vUser_id); */
		$.ajax({
			url:"<%=context%>/ajaxIdCheck.do",
			data : {user_id : vUser_id},
			dataType:'text',
			success:function(data){
				//alert(".ajax getUser_idCheck memberCnt Message->"+data);
				alert(data);
				/* $('#user_idCheckMsg').html(data); 아래 글 띄워줌 */      /* span id Tag */
			}
		}); 
	}
	
	function chk() {
		if (frm.user_ps.value !=frm.user_ps2.value) {
			alert("암호가 다릅니다");
			frm.user_ps.focus();
			location.href="joinForm.jsp"
			return false;
		}
		if (frm.id_num.value =="") {
			alert("생년월일을 입력 안 했네요 ㅠ");
			frm.id_num.focus();
			location.href="joinForm.jsp"
			return false;
		}
		return true;
	}
	
</script>
</head>
<body>
<jsp:include page="css/header.jsp"></jsp:include>
	<section>
	<h2 class="join">회원가입</h2>
	<form action="joinPro.do" name="frm" onsubmit="return chk()" method="post" enctype="multipart/form-data">
		<table class="joinTbl" border="1">
			
			<tr>
				<td>아이디</td>
				<td><input type="text" class="in_tag" name="user_id"  id="user_id" placeholder="(20자 까지 가능)" required="required" autofocus="autofocus">
				    <input type="button" id="id_botton" value="아이디체크" onclick="getUser_idCheck()"> 
				    <span id="user_idCheckMsg"></span>
				    </td> 
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" class="in2_tag" name="user_ps" placeholder="(10자 까지 가능)" required="required"></td>
			</tr>
			<tr>
				<td>비밀번호확인</td>
				<td><input type="password" class="in2_tag" name="user_ps2" placeholder="위와 동일한 비밀번호 입력" required="required"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" class="in2_tag" name="uname" required="required"></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input type="text" class="in2_tag" name="id_num" placeholder="00000000" required="required" ></td>
			</tr>
			<tr><td>성별</td>
				<td class="gender">
					<span>남자</span><input type="radio" class="in_tag" name="gender" checked="checked" value="1"><br>    <!-- 사용자가 남성이 많은 경우 -->
			    	<span>여자</span><input type="radio" class="in_tag" name="gender"                   value="2">
			    </td>
			</tr>
			<tr>
				<td>연락처</td>
				<td><input type="tel" class="in2_tag" name="tel" required="required"
				pattern="\d{2,3}-\d{3,4}-\d{4}" placeholder="xxx-xxxx-xxxx" 
				title="2,3자리-3,4자리-4자리"></td> <!-- title은 도움말/ placeholder랑 같이 써야 화면에 나온다. -->
			</tr>
			<tr>
				<td>회원사진</td>
				<td><input type="file" id="input_button" name="user_picture" required="required"></td>
			</tr>
			<tr><td>관심 운동 종목</td>
				<td class="workout">
					<span>필라/요가</span><input type="radio" class="in_tag" name="sport_type" value="1"><br>   <!-- checkbox은 multiple 가능 -->
					<span>헬스</span><input type="radio" class="in_tag" name="sport_type" value="2"><br>
					<span>수영</span><input type="radio" class="in_tag" name="sport_type" value="3"><br>
					<span>골프</span><input type="radio" class="in_tag" name="sport_type" value="4"><br>
					<span>기타</span><input type="radio" class="in_tag" name="sport_type" value="5"><br>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" id="sub_botton" name="submit" value="완료">
				    <input type="reset" id="re_botton" name="reset" value="다시작성"></td>
			</tr>
		</table>
	</form>
	</section>
<jsp:include page="css/footer.jsp"></jsp:include>
</body>
</html>