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
<title>가계부 정보 수정창</title>
<style>
	.cls1 {
	   font-size:40px;
	   text-align:center;}
</style>
<%@include file="../Top.jsp"%>
</head>

<body>
	<br>
	<h1 class="cls1">가계부 정보 수정창</h1>
	<form  method="post" action="${contextPath}/homebook/modHomebook.do?serialno=${bookInfo.serialno}">
		<table align="center" >
			<tr>
		    	<td width="200"><p align="right">등록번호</td>
		    	<td width="400"><input type="text" name="serialno" value="${bookInfo.serialno}" disabled></td>
		    </tr>
		    <tr>
		    	<td width="200"><p align="right">등록일자</td>
		    	<td width="400"><input type="text" name="day" value="${bookInfo.day}" ></td>
		    </tr>
		 
		 	<tr>
		    	<td width="200"><p align="right">수지구분</td>
		    	<td width="400"><input type="text" name="section" value="${bookInfo.section}" ></td>
		    </tr>
		    <tr>
		        <td width="200"><p align="right">계정과목</td>
		        <td width="400"><input type="text"  name="accounttitle" value="${bookInfo.accounttitle}"></td>
		    </tr>
		    <tr>
		        <td width="200"><p align="right">적요</td>
		        <td width="400"><p><input type="text"  name="remark"  value="${bookInfo.remark}"></td>
		    </tr>
		    <tr>
		        <td width="200"><p align="right">수입</td>
		        <td width="400"><p><input type="text"  name="revenue"  value="${bookInfo.revenue}"></td>
		    </tr>
		    <tr>
		        <td width="200"><p align="right">지출</td>
		        <td width="400"><p><input type="text"  name="expense"  value="${bookInfo.expense}"></td>
		    </tr><tr>
		        <td width="200"><p align="right">회원아이디</td>
		        <td width="400"><p><input type="text"  name="mem_id"  value="${bookInfo.mem_id}" disabled></td>
		    </tr>
		 
		    <tr align="center" >
		    <td colspan="2" width="400"><input type="submit" value="수정하기" >
		       <input type="reset" value="다시입력" > </td>
		   </tr>
		</table>
	</form>

</body>
</html>