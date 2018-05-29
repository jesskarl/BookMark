<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--@elvariable id="registerForm" type="cn.woshizhiwu.form.RegisterForm"--%>
<%--@elvariable id="info"--%>
<%--@elvariable id="validationErrors" type="java.util.Set<javax.validation.ConstraintViolation>"--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
        <h5><c:if test="${info != null}"><c:out value="${info}" /></c:if>
                <c:if test="${validationErrors != null}"><div class="errors">
            <ul>
                <c:forEach items="${validationErrors}" var="error">
                    <li><c:out value="${error.message}" /></li>
                </c:forEach>
            </ul>
        </div></c:if></h5>
        <form:form method="post" modelAttribute="registerForm">
            <form:label path="email">邮箱:</form:label><br />
            <form:input path="email" type="string" /><br />
            <form:errors path="email" cssClass="errors" />
            <br />
            <form:label path="username">用户名:</form:label><br />
            <form:input path="username" type="string" /><br />
            <form:errors path="username" cssClass="errors" />
            <br />
            <form:label path="password">密码:</form:label><br />
            <form:input path="password" type="password" /><br />
            <form:errors path="password" cssClass="errors" />
            <br />
            <form:label path="password2">确认密码:</form:label><br />
            <form:input path="password2" type="password" /><br />
            <form:errors path="password2" cssClass="errors" />
            <br />
            <input type="submit" value="注册" />
        </form:form>
        <br/>
        <a href="<c:url value="/login" />">已有帐号，去登录</a>
        <br/>
        <a href="<c:url value="/index" />">随便看看</a>
</body>
</html>