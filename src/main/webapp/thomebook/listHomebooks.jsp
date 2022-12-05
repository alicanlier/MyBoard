<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html> 
<html>
<head>
<c:choose>
   <c:when test='${msg=="addHomebook"}'>
      <script>
         window.onload=function(){
            alert("가계부를 등록했습니다.");}
      </script>
   </c:when>
   <c:when test='${msg=="modified"}'>
      <script>
        window.onload=function(){
          alert("가계부 정보를 수정했습니다.");}
      </script>
   </c:when>
   <c:when test= '${msg=="deleted" }'>
      <script>
         window.onload=function(){
            alert("가계부 정보를 삭제했습니다.");} 
      </script>
</c:when>
</c:choose>

<meta  charset="UTF-8">
<title>가계부 정보 출력장</title>
<style>
     .cls1 {
       font-size:40px;
       text-align:center;}
     .cls2 {
       font-size:20px;
       text-align:center;}
</style>
<%@include file="../Top.jsp"%> 
</head>

<body>
	<br>
	<p class="cls1">가계부정보</p>
	<table align="center" border="1" >
		<tr align="center" bgcolor="lightgreen">
			<td width="7%" ><b>등록번호</b></td>
			<td width="7%" ><b>등록일자</b></td>
			<td width="7%" ><b>수지구분</b></td>
			<td width="7%"><b>계정과목</b></td>
			<td width="7%" ><b>적요</b></td>
			<td width="7%" ><b>수입금액</b></td>
			<td width="7%" ><b>지출금액</b></td>
			<td width="7%" ><b>회원아이디</b></td>
			<td width="7%" ><b>수정</b></td>
			<td width="7%" ><b>삭제</b></td>
		</tr>

		<c:choose>
		    <c:when test="${empty homebookList or empty login_id}" >
				<tr align="center">
					<td colspan=10>
						<p><b>등록된 가계부 내역이 없거나 열람 관한이 없습니다...</b></p>
				      	<p><b>Only member area. Please login to use.</b></p>
				      	<p><a href="../login/formLogin.jsp"><button type="button">Move to login page..</button></a></p>					
					</td>  
				</tr>
			</c:when>  
			<c:when test="${not empty homebookList && login_id eq 'admin'}" >
				<c:forEach  var="data" items="${homebookList }" >
					<tr align="center">
						<td>${data.serialno }</td>
						<td>${data.day}</td>
						<td>${data.section }</td>
						<td>${data.accounttitle}</td>     
						<td>${data.remark}</td>     
						<td>${data.revenue}</td>
						<td>${data.expense}</td>
						<td>${data.mem_id}</td>
						<td><a href="${contextPath}/homebook/modHomebookForm.do?serialno=${data.serialno }">수정</a></td>
						<td><a href="${contextPath}/homebook/delHomebook.do?serialno=${data.serialno }">삭제</a></td>
					</tr>
				</c:forEach>
			</c:when>
	
			<c:when test="${!empty homebookList && login_id ne 'admin'}" >
				<c:forEach  var="data" items="${homebookList }">
				     <c:if test="${login_id eq data.mem_id}">
		        		<tr align="center">
							<td>${data.serialno }</td>
							<td>${data.day}</td>
							<td>${data.section }</td>
							<td>${data.accounttitle}</td>     
							<td>${data.remark}</td>     
							<td>${data.revenue}</td>
							<td>${data.expense}</td>
							<td>${data.mem_id}</td>
							<td><a href="${contextPath}/homebook/modHomebookForm.do?serialno=${data.serialno }">수정</a></td>
							<td><a href="${contextPath}/homebook/delHomebook.do?serialno=${data.serialno }">삭제</a></td>
						</tr>
					</c:if>
				</c:forEach>
				<tr align="center"><td colspan="10">You can see only your own data. Admin permission is required to see the whole list.</td></tr>
			</c:when>		
		</c:choose>
	</table>
	<a href="${contextPath}/homebook/homebookForm.do"><p class="cls2">가계부작성하기</p></a>
	
</body>
</html>