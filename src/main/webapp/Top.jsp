<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<style>
	.top {background-color: lightgray;}
	.green {color: GREEN;}
	.red {color: red;}
</style>
<%String id = (String) session.getAttribute("login_id");
if (id == null) {id = "GUEST";}%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
</head>

<body>

	<table width="100%" border="0">
		<tr height="70" class="top">
			<td><a href="/MyBoard/Main.jsp" style="text-decoration: none"> 
			<img src="/MyBoard/images/LOGO.png"  height="70" alt="로고출력자리"/>

			</a></td>
			<td colspan="5">
				<div align="center">
					<a href="/MyBoard/Main.jsp" style="text-align:center" title="home" style="text-decoration: none">
					<h1 class="red">My Account Book</h1></a>
					<h4 class="green">Oracle/FrontControllPattern/Model2방식</h4>
				</div>
			</td>
			<td align="center" width="200">
			<h3 class="y"><%=id%>님</h3> 
		<%if (id.equals("GUEST")) {%>
			<button class="btn btn-primary" onclick="location.href='/MyBoard/login/formLogin.jsp'">로그인</button>
			<%
				} else {
			%>
			<!-- <button class="btn btn-primary" onclick="location.href='login'">로그아웃</button> -->
			<button class="btn btn-primary" onclick="location.href='/MyBoard/wait_login.jsp'">로그아웃</button>
			<%
				}
			%></td>
		</tr>

		<tr height="50">
			<td align="center" width="14%" bgcolor="black"><a href="/MyBoard/member/memberForm.do" style="text-decoration: none">
				<font color="white" size="4">회원가입</font></a></td>
			<td align="center" width="14%" bgcolor="gray"><a href="/MyBoard/member/modMemberForm.do" style="text-decoration: none">
				<font color="white" size="4">본인정보</font></a></td>
			<td align="center" width="14%" bgcolor="gray"><a href="/MyBoard/homebook/homebookForm.do" style="text-decoration: none">
				<font color="white" size="4">가계부입력</font></a></td>
			<td align="center" width="14%" bgcolor="gray"><a href="/MyBoard/homebook/listHomebooks.do" style="text-decoration: none">
				<font color="white" size="4">회원자료</font></a></td>
			<td align="center" width="14%" bgcolor="gray"><a href="/MyBoard/member/listMembers.do" style="text-decoration: none">
				<font color="white" size="4">모든회원</font></a></td>
			<td align="center" width="16%" bgcolor="gray"><a href="/MyBoard/board/listArticles.do" style="text-decoration: none">
				<font color="white" size="4">게시판</font></a></td>
			<td align="center" width="14%" bgcolor="black"><a href="instructions" style="text-decoration: none; pointer-events: none">
				<font color="white" size="4">본사이트이용법</font></a></td>
		</tr>
	</table>
</body>
</html>