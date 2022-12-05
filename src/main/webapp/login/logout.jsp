<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃처리</title>
</head>
<body>
<a href="/MyBoard/Main.jsp" style="text-align:center"><h2>HOME</h2></a>
<script>
	alert('로그아웃 됩니다.');
</script>
<%

	//session.removeAttribute("login.id");
	//session.removeAttribute("login.pwd");
	//session.removeAttribute("isLogon");
	//session.removeAttribute("memInfo");
	session.invalidate();
	response.sendRedirect("/MyBoard/");
%>

</body>
</html>