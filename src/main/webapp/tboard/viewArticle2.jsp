<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<head>
   <meta charset="UTF-8">
   <title>글보기</title>
   <style>
     #tr_btn_modify{
       display:none;
     }
   
   </style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script type="text/javascript" >
     function backToList(obj){
       obj.action="${contextPath}/board/listArticles.do";
       obj.submit();
     }
 
    function fn_enable(obj){
       document.getElementById("i_title").disabled=false;
       document.getElementById("i_content").disabled=false;
   // document.getElementById("i_imageFileName").disabled=false;
       document.getElementById("tr_btn_modify").style.display="block";
       document.getElementById("tr_btn").style.display="none";
    }
    
    function fn_modify_article(obj){
       obj.action="${contextPath}/board/modArticle.do";
       obj.submit();
    }
    
    
     function fn_remove_article(url,articleNO){
       var form = document.createElement("form");
       form.setAttribute("method", "post");
       form.setAttribute("action", url);
        var articleNOInput = document.createElement("input");
        articleNOInput.setAttribute("type","hidden");
        articleNOInput.setAttribute("name","articleNO");
        articleNOInput.setAttribute("value", articleNO);
       
        form.appendChild(articleNOInput);
        document.body.appendChild(form);
        form.submit();
    
    }
    
    function fn_reply_form(url, parentNO){
       var form = document.createElement("form");
       form.setAttribute("method", "post");
       form.setAttribute("action", url);
        var parentNOInput = document.createElement("input");
        parentNOInput.setAttribute("type","hidden");
        parentNOInput.setAttribute("name","parentNO");
        parentNOInput.setAttribute("value", parentNO);
       
        form.appendChild(parentNOInput);
        document.body.appendChild(form);
       form.submit();
    }
    
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#preview').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }  
 </script>
 
 
 
  <link rel='stylesheet' href='../style.css'>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600;900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Source+Sans+Pro:wght@600;900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nanum+Gothic&family=Source+Sans+Pro:wght@600;900&display=swap" rel="stylesheet">

 
 
 
 
</head>
<body>


<nav class="navbar" height="200px" >
 
<div class="navbar_logo">

   <i class="fa-solid fa-calculator" style="color:white"> <a class="white" href="../" style="font-size: 24px;"> 비전가계부</a></i>

 </div>

 
<ul class="navbar_menu" >
<li><a class= "white" href="../">이용방법안내</a></li>
<li><a class= "white" href="../homebook/homebookForm.do"> 가계부작성</a></li>
<li><a class= "white" href="../homebook/listHomebooks.do"> 회원자료</a></li>
<li><a class= "white" href="listArticles.do">커뮤니티게시판</a>
<li><a class= "white" href="../member/modMemberForm.do">내정보</a>
<li><a class= "white" href="../member/listMembers.do">전체회원정보</a>
</ul>


<ul class="navbar_icons">
<%
   String id = (String) session.getAttribute("login.id");
   if (id == null) {
      id = "GUEST";
   }
%>
<a class= "white"><%=id%>님 &nbsp </a>
<a class = icons href="../login/formLogin.jsp"><i class="fa-solid fa-user-check" style="color:white"></i></a>
<a class = icons href="../member/memberForm.do"><i class="fa-solid fa-user-plus" style="color:white"></i></a>
<a class = icons href="../login/logout.jsp"><i class="fa-solid fa-right-from-bracket"></i></a>

</ul>

<a ref="#" class="navbar_toogleBtn">
<i class="fa-solid fa-bars"></i>
</a> 
</nav>


<center>

<div class = "maindiv_viewArticles">


  <form name="frmArticle" method="post"  action="${contextPath}"  enctype="multipart/form-data">
  <table  border=0  align="center">
   <tr  >  <td colspan="10" align="center" ><h1> 상세내용 및 수정</h1></td>  </tr>
  <tr>
   <td width=150 align="center" bgcolor=#FF9933>
      글번호
   </td>
   <td >
    <input type="text"  value="${article.articleNO }"  size = "57" disabled />
    <input type="hidden" name="articleNO" value="${article.articleNO}"  />
   </td>
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      작성자 아이디
   </td>
   <td >
    <input type=text value="${article.id }" name="writer" size = "57" disabled />
   </td>
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      제목 
   </td>
   <td>
    <input type=text value="${article.title }"  name="title"  id="i_title" size = "57" disabled />
   </td>   
  </tr>
  <tr>
    <td width="150" align="center" bgcolor="#FF9933">
      내용
   </td>
   <td>
    <textarea rows="20" cols="60"  name="content"  id="i_content" size = "57" disabled />${article.content }</textarea>
   </td>  
  </tr>
 
<c:if test="${not empty article.imageFileName && article.imageFileName!='null' }">  
<tr>
    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">
      이미지
   </td>
   <td>
     <input  type= "hidden"   name="originalFileName" value="${article.imageFileName }" />
    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" id="preview"  width=450px height=200 /><br>
       
   </td>   
  </tr>  
  <tr>
    <td>
       <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   />
    </td>
  </tr>
 </c:if>
  <tr>
      <td width="150" align="center" bgcolor="#FF9933">
         등록일자
      </td>
      <td>
       <input type=text value="<fmt:formatDate value="${article.writeDate}" />" size = "57" disabled />   
      </td>   
  </tr>
  <tr   id="tr_btn_modify"  >
      <td colspan="2"   align="center" >
          <input type=button value="수정반영하기"   onClick="fn_modify_article(frmArticle)"  >
           <input type=button value="취소"  onClick="backToList(frmArticle)">
      </td>   
  </tr>
    
  <tr  id="tr_btn"    >
  <td colspan="2" align="center">
  
  <c:if test="${article.id == article.ids}">  
       <input type=button value="수정하기" onClick="fn_enable(this.form)">
       <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
   </c:if>
       <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
       
         <% if (id != "GUEST") {      %>  
        <input type=button value="답글쓰기"  onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleNO})">
        <%} %>
   </td>
  </tr>
 </table>
 </form>
 </div>
 </center>
</body>
</html>