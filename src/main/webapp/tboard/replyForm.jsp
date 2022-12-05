<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%request.setCharacterEncoding("UTF-8");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function backToList(obj){
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit();
	}
	
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            $('#preview').attr('src', e.target.result);}
	        reader.readAsDataURL(input.files[0]);}
	}  
</script> 
<title>답글쓰기 페이지</title>
<%@include file="../Top.jsp"%>
</head>

<body>
	
	<br>
	<h1 style="text-align:center">답글쓰기</h1>
	<form name="frmReply" method="post"  action="${contextPath}/board/addReply.do" enctype="multipart/form-data">
	    <table align="center">
		    <tr>
				<td align="left"> 글쓴이: </td>
				<td colspan=2><input type="text" size="5" value="${login_id}" disabled /> </td>
			</tr>
			<tr>
				<td align="left">글제목: </td>
				<td colspan=2><input type="text" size="67" maxlength="99" name="title" /></td>
			</tr>
			<tr>
				<td align="left" valign="top"><br>글내용:&nbsp; </td>
				<td colspan=2><textarea name="content" rows="10" cols="70" maxlength="4000"> </textarea> </td>
			</tr>
			<tr>
				<td align="left">이미지파일 첨부:&nbsp;</td>
				<td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
	            <td align="right"><img  id="preview" src="#" width=200 height=200/></td>
			</tr>
			<tr>
				<td align="left"> </td>
				<td colspan=2>
					<input type=submit value="답글반영하기" />
					<input type=button value="취소"onClick="backToList(this.form)" />
					
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>