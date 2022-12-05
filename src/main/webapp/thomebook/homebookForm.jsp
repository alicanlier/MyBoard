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
<title>가계부 등록창</title>
<%@include file="../Top.jsp"%>
</head>

<body>
	<br>
	<h1  style="text-align:center">가계부등록</h1>
	
	<c:choose>
		<c:when test="${empty login_id}">
	    	<div align="center">
		      	<p><b>Only member area. Please login to use.</b></p>
		      	<p><a href="../login/formLogin.jsp"><button type="button">Move to login page..</button></a></p>
	      	</div>
		</c:when>
		<c:when test="${!empty login_id}">
			<table  align="center">
				<form method="post" action="${contextPath}/homebook/addHomebook.do">
					<tr>
						<td width="200"><p align="right">수지구분</td>
						<td width="400"><input type="text" name="section"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">계정과목</td>
						<td width="400"><input type="text"  name="accounttitle"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">적요</td>
						<td width="400"><p><input type="text"  name="remark"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">수입</td>
						<td width="400"><p><input type="text"  name="revenue"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">지출</td>
						<td width="400"><p><input type="text"  name="expense"></td>
					</tr>
					<tr>
						<td width="200"><p align="right">회원아이디</td>
						<td width="400"><p><input type="text"  name="mem_id" value="${login_id}"></td>
					</tr>
					<tr>
						<td width="200"><p>&nbsp;</p></td>
						<td width="400">
							<input type="submit" value="기록하기">
							<input type="reset" value="다시입력">
						</td>
					</tr>
				</form>
			</table>
		</c:when>
	</c:choose>

</body>
</html>