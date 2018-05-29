<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
        <H5><c:if test="${info != null}"><c:out value="${info}"/></c:if></H5>
        <form:form method="post" modelAttribute="loginForm">
            <form:label path="email">邮箱:</form:label><br />
            <form:input path="email" type="string" /><br />
            <br />
            <form:label path="password">密码:</form:label><br />
            <form:input path="password" type="password" /><br />
            <br />
            <input type="submit" value="登录" />
        </form:form>
        <br/>
        <a href="<c:url value="/register" />">注册</a>

</body>
</html>