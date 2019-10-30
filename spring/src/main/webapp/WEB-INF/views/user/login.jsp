<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3><spring:message code="message.user.login.title"/></h3>
<form action="login" method="post">
	<spring:message code="message.user.login.id" />
		<input name="id" value="${requestScope.user.id }"/>
	<spring:message code="message.user.login.password" />
		<input name="password" value="${requestScope.user.password }"/>
	<button><spring:message code="message.user.login.loginBtn"/></button>
</form>
</body>
</html>