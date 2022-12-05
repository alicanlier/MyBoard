<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  request.setCharacterEncoding("UTF-8");%>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h2 {
    position: absolute;
    top: 25%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center; 
    /*vertical-align: middle;*/
}
</style>
</head>
<body>

<c:choose> 
	<c:when test="${pass}">
		<h2>안녕하세요 ${mem_id} 님!!!<br><br>Redirecting to home...</h2>
		<c:remove var="pass" scope="session" />
		<script>setTimeout(() => { onload=window.location='/MyBoard/';}, 1000);</script>
	</c:when>
	<c:when test="${pass eq null}">
		<h2>Logging out!!!<br><br>Redirecting to home...</h2>
		<%session.invalidate();%>
		<script>setTimeout(() => { onload=window.location='/MyBoard/';}, 1000);</script>
	</c:when>			
	<c:otherwise>
		<c:choose>
			<c:when test="${emptyLogin}">
				<h2>ID and password are required.<br><br>다시 로그인하세요!"</h2>
				<script>setTimeout(() => { onload=window.location='login/formLogin.jsp';}, 1500);</script>		
			</c:when>			
			<c:otherwise>
				<h2>ID and password don't match.<br><br>다시 로그인하세요!"</h2>
				<script>setTimeout(() => { onload=window.location='login/formLogin.jsp';}, 1500);</script>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
	


<!--  -->
</body>
</html>