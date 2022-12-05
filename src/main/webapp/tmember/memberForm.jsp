<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%request.setCharacterEncoding("UTF-8");%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입창</title>
<%@include file="../Top.jsp"%>
</head>

<body>

	<br>
	<h1 style="text-align:center">회원 가입창</h1>
	
	<c:choose>
		<c:when test="${!empty login_id && login_id ne 'admin'}">	
			<div align="center">
		      	<p><b>You are already a member. Logout for a new registry.</b></p>
		      	<p><a href="../wait_login.jsp"><button type="button">로그아웃</button></a></p>
	      	</div>	      	
		</c:when>
		<c:otherwise>
		
			<c:if test="${existmsg eq 'yes'}">
		    	<div align="center">
			      	<p><b>Entered ID is already in use. Try a different ID.</b></p>
				</div>
			</c:if>		
		
			<table  align="center">	
				<form method="post" action="${contextPath}/member/addMember.do">
					<tr>
						<td width="200"><p align="right">아이디</td>
						<td width="400"><input type="text" name="id"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">비밀번호</td>
						<td width="400"><input type="password"  name="pwd"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">이름</td>
						<td width="400"><p><input type="text"  name="name"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">이메일</td>
						<td width="400"><p><input type="text"  name="email"></td>
					</tr>
					<tr>
						<td width="200"><p>&nbsp;</p></td>
						<td width="400">
							<input type="submit" value="가입하기">
							<input type="reset" value="다시입력">
						</td>
					</tr>
				</form>
			</table>
		</c:otherwise>
	</c:choose>
	
</body>
</html>