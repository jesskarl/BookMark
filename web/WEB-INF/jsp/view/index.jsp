
<!DOCTYPE html>
<html>
    <head>
        <title>BookMark</title>
    </head>
    <body>
    
    <h1></h1>
    <c:if test="${!isLogin}"><c:out value="${user.getUsername()}"/>
        <a href="<c:url value="/register" />">注册</a>    <a href="<c:url value="/login" />">登陆</a>
    </c:if>
    <c:if test="${isLogin}"><c:out value="${user.getUsername()}"/>
        <a href="<c:url value="/user/home" />">用户主页</a>    
        </c:if>
        简介
    </body>
</html>
