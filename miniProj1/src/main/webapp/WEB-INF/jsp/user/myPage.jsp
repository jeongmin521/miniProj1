<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <style>
        label {
            display: inline-block;
            width: 200px;
        }
        input {
            margin-bottom: 10px; 
        }
    </style>
</head>
<body>
    <h1>
       마이페이지
    </h1>
   
      <label>아이디 : </label> <br/>
      <label>이름 :</label><br/>
      <label>나이: </label><br/>
      <label>이메일:</label><br/>

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script>
function jsDelete() {
	alert("정말 탈퇴하시겠습니까?");
}

function jsUpdateForm() {
	alert("정말로 수정하시겠습니까?");
		
}

</script>
<!-- 두개의 폼을 하나로 합치는 방법 , js를 사용하여 처리  -->
	<form id="viewForm" method="post" action="user.do">
		<input type="hidden" id="action" name="action" value="">
		<input type="hidden" id="userid" name="userid" value="${user.userid}">
		<input type="button" value="삭제" onclick="jsDelete()">
		<input type="button" value="수정" onclick="jsUpdateForm()">
	</form>     
 
</body>
</html>

