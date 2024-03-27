<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시물 상세보기</title>
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
        게시물 상세보기
    </h1>
    
	  <label>게시물 번호: ${board.bno}</label> <br/>
      <label>제목 : ${board.btitle}</label><br/>
      <label>내용 : ${board.bcontent}</label><br/>
      <label>작성자 : ${board.buserid}</label><br/>
      <label>작성일 : ${board.bdate}</label><br/>
   
    <div>
        <a href="board.do?action=list">목록</a>
        <a href="board.do?action=updateForm">수정</a>
    </div>
</body>
</html>