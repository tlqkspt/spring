<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="insertBoard" method="post" enctype="multipart/form-data">	<!-- / 넣으면 x  ./해야됨 -->	<!-- 스프링 필터?(예전에 charset? 안걸면 한글깨짐 -->
		제목<input name="title">
		작성자<input name="writer">
		내용<input name="content">
		업로드<input type="file" name="uploadFile">
		<button>등록</button>
	</form>

</body>
</html>