<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>
<h3>게시글 상세보기</h3>
	번호: ${board.seq } <br>
	제목: ${board.title } <br>
	작성자: ${board.writer } <br>
	내용: ${board.content } <br>
	파일명: ${board.uploadFilename}<br>
	작성일: ${board.regDate }<br>
	<a href="./download/${board.uploadFilename }">${board.uploadFilename }</a><br>
	<img src="./resources/image/${board.uploadFilename }"/>
</body>
</html>