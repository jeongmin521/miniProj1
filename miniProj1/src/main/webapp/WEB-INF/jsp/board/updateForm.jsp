<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>수정화면</title>
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
        게시물 수정 수정양식 
    </h1>
    <form action="boards" method="post">
    	<input type="hidden" name="action" value="update">
        <label>게시물 번호: </label> <input type="text" id="bno" name="bno" value="${board.bno}" readonly="readonly"> <br/>
        <label>제목 : </label><input type="text" id="btitle" name="btitle" value="${board.btitle}"><br/>
        <label>내용: </label> <input type="text" id="bcontent" name="bcontent" value="${board.bcontent}"><br/>
    <div>
        <input type="submit" value="수정">
        <a href="boar.do?action=view">취소</a>
    </div>
    
    </form>
    
</body>
</html>
