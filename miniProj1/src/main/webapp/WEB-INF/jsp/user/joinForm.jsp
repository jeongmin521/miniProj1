<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <style>
        label {
            display: inline-block;
            width: 120px;
        }
        input {
            margin-bottom: 10px; 
        }
    </style>
</head>
<body>
    <h1>
        회원가입 양식
    </h1>
    	<input type="hidden" name="action" value="join">
        <label>아이디 : </label> <input type="text" id="userid" name="userid" required="required"> <br/>
        <label>비밀번호 : </label>   <input type="password" id="userpassword" name="userpassword" required="required"><br/>
        <label>비밀번호확인 : </label>   <input type="password" id="userpassword2" name="userpassword2" required="required"><br/>
        <label>이름 : </label>   <input type="text" id="username" name="username" required="required"><br/>
        <label>나이: </label>    <input type="number" id="userage" name="userage" required="required"><br/>
        <label>이메일: </label>  <input type="email" id="useremail" name="useremail" required="required"><br/>
    <div>
        <input type="submit" value="등록" >
        <a href="user.do?action=list">취소</a>
    </div>

</body>
</html>