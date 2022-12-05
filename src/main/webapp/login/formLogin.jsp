<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<%@include file="../Top.jsp"%>
</head>

<body>
	<br>
	<div class = "container">
		<h1 align="center">로그인</h1>
		<hr>
		<form name="loginForm" action="/MyBoard/login" method ="POST"> 
			<table class="table table-bordered" align="center">
				<tr><th>ID</th><td>
					<input class="form-control"  type="text" name="user_id" size="20">
				</td></tr>
				<tr><th>PASSWORD</th><td>
					<input class="form-control"  type="password" name="user_pwd" size="20">
				</td></tr>
				<tr><td colspan="2" align="center">
					<input class="btn btn-success" type="submit" value="로그인">
					<input class="btn btn-danger" type="reset" value="다시">
				</td></tr>
			</table>
		</form>
	</div>
</body>
</html>