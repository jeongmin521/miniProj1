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
    
	  <label>이름: </label> <br/>
      <label>아이디 :</label><br/>
      <label>비밀번호 : </label><br/>
      <label>나이 : </label><br/>
      <label>이메일 : </label><br/>
   
    <div>
        <a href="user.do?action=list">목록</a>
        <a href="user.do?action=updateForm">수정</a>
    </div>
</body>
</html>