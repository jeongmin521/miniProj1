<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 상세보기</title>
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
        회원 상세보기
    </h1>
    
	  <label>이름: ${user.username}</label> <br/>
      <label>아이디 : ${user.userid}</label><br/>
      <label>비밀번호 : ${user.userpassword}</label><br/>
      <label>나이 : ${user.userage}</label><br/>
      <label>이메일 : ${user.useremail}</label><br/>
      <label>전화번호 : ${user.userphone}</label><br/>
      <label>주소 : ${user.useraddress}</label><br/>
   
    <div>
        <a href="user.do?action=list">목록</a>
        <a href="user.do?action=updateForm&userid=${user.userid}">수정</a>
        <a href="user.do?action=delete&userid=${user.userid}">삭제</a>
    </div>
</body>
</html>