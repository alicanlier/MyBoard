<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html> 
<html>
<head>
<meta  charset="UTF-8">
<title>회원 정보 출력창</title>
<style>
     .cls1 {
       font-size:40px;
       text-align:center;
     }
    
     .cls2 {
       font-size:20px;
       text-align:center;
     }
  </style>
<%@include file="../Top.jsp"%>  
</head>

<body>

	<c:choose>
		<c:when test='${msg=="addMember" }'>
			<script>
	        	window.onload=function(){
	            alert("회원을 등록했습니다.");}
			</script>
		</c:when>
		<c:when test='${msg=="modified" }'>
			<script>
	        	window.onload=function(){
	        	alert("회원 정보를 수정했습니다.");}
			</script>
		</c:when>
		<c:when test= '${msg=="deleted"}'>
			<script>
	        	window.onload=function(){
	            alert("회원 정보를 삭제했습니다.");} 
			</script>
		</c:when>
	</c:choose>
	
	<br>
	<p class="cls1">회원정보</p>
	<table align="center" border="1" width="60%">
    	<tr align="center" bgcolor="lightgreen">
			<td width="7%" ><b>아이디</b></td>
			<td width="7%" ><b>비밀번호</b></td>
			<td width="7%" ><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%" ><b>가입일</b></td>
			<td width="7%" ><b>수정</b></td>
			<td width="7%" ><b>삭제</b></td>
		</tr>
		<c:choose>
		    <c:when test="${empty membersList or empty login_id}">
		    	<tr align="center">
			        <td colspan=7>
			          	<p><b>등록된 회원이 없거나 열람 관한이 없습니다.</b></p>
				      	<p><b>Only member area. Please login to use.</b></p>
				      	<p><a href="../login/formLogin.jsp"><button type="button">Move to login page..</button></a></p>
			       	</td>  
		      	</tr>
			</c:when>  
			<c:when test="${!empty membersList && login_id eq 'admin'}">
				<c:forEach  var="mem" items="${membersList }">
	        		<tr align="center">
						<td>${mem.id }</td>
						<td>${mem.pwd }</td>
						<td>${mem.name}</td>     
						<td>${mem.email }</td>     
						<td>${mem.joinDate}</td>
						<td><a href="${contextPath}/member/modMemberForm.do?id=${mem.id }">수정</a></td>
						<td><a href="${contextPath}/member/delMember.do?id=${mem.id }">삭제</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${!empty membersList && login_id ne 'admin'}">
				<c:forEach  var="mem" items="${membersList }">
				     <c:if test="${login_id eq mem.id}">
		        		<tr align="center">
							<td>${mem.id }</td>
							<td>${mem.pwd }</td>
							<td>${mem.name}</td>     
							<td>${mem.email }</td>     
							<td>${mem.joinDate}</td>
							<td><a href="${contextPath}/member/modMemberForm.do?id=${mem.id }">수정</a></td>
							<td><a href="${contextPath}/member/delMember.do?id=${mem.id }">회원탈퇴</a></td>
						</tr>
					</c:if>
				</c:forEach>
				<tr align="center"><td colspan="7">You can see only your own data. Admin permission is required to see the whole member list.</td></tr>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test= '${msg=="linked"}'>
				<script>
		        	window.onload=function(){
		            alert("member is linked to homebook.\nconfirm to delete all data.");} 
				</script>
				<tr><td colspan="7" style="text-align:center;"><a href="${contextPath}/member/delMemberLink.do?id=${mem.id }">Delete member & data</a></td></tr>
			</c:when>
		</c:choose>
	</table>  
	<a href="${contextPath}/member/memberForm.do"><p class="cls2">회원 가입하기</p></a>
	
</body>
</html>