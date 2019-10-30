<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 
	<form action="insertBoard" method="post" enctype="multipart/form-data">	<!-- / 넣으면 x  ./해야됨 -->	<!-- 스프링 필터?(예전에 charset? 안걸면 한글깨짐 -->
		제목<input name="title">
		작성자<input name="writer">
		내용<input name="content">
		<select name="boardType">
			<c:forEach items="${boardType }" var="t">
				<option value="${t.value }">${t.key}
			</c:forEach>
		</select>
		업로드<input type="file" name="uploadFile">
		<button>등록</button>
	</form>
	 --%>
	
	<!-- path는 반드시 VO에 있어야된다 -->
	<form:form commandName="board" action="insertBoard" method="post" enctype="multipart/form-data">	<!-- / 넣으면 x  ./해야됨 -->	<!-- 스프링 필터?(예전에 charset? 안걸면 한글깨짐 -->
		<!-- <input name="mode" value="update"/> -->
		<form:input path="seq" />
		제목<form:input path="title"/>
		작성자<form:input path="writer"/>
		내용<form:input path="content"/>
		<form:select path="boardType">
				<form:options items="${boardType}"/> 
		</form:select>
		업로드<input type="file" name="uploadFile"/>
		<form:button>등록</form:button>
	</form:form>

</body>
</html>