<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>${sessionScope.user.name }
		<a href="logout">로그아웃</a>
	</h3>

	<form>
		제목<input name="title">
		게시판구분 <select name="boardType">
					<c:forEach items="${boardType }"  var="t">
						<option value="${t.value }">${t.key}
					</c:forEach>
				</select>
	 	정렬<select name="orderby">
	 			<option value="">선택
				<option value="title">제목
				<option value="writer">작성자
				<option value="regDate">작성일자
		   </select>	
		<button>검색</button>
	</form>
	
	<form action="deleteBoardList">
		<c:forEach items="${boardList }" var="board">
			<div><input name="seqList" type="checkbox" value="${board.seq}"/>${board.seq}	
			<a href="getBoard?seq=${board.seq}"> ${board.title} </a> 
			${board.regDate} ${board.uploadFilename} </div>
		</c:forEach>
		<button>삭제</button>
	</form>
</body>
</html>