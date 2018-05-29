<html>
    <head>
        <title>bookmark/admin</title>
    </head>
    <body>
        <h2>列表：<c:out value="${title}" /><c:if test="${tag != null}"><c:out value="：${tag}" /></c:if></h2>
        <br />
        <hr/>
        <a href="<c:url value="/user/admin?page=0" />">all url<a/>&nbsp
        <a href="<c:url value="/user/admin?page=1" />">all user<a/>
        <hr/>
        <c:choose>
            <c:when test="${fn:length(urlList) > 0}">
            <c:out value="${nums}"/>urls
                <c:forEach items="${urlList}" var="url">
                <br />
                    <c:out value="${url.title}"/>||    
                    <a href="<c:url value="/user/admin?deleteUrlId=${ url.id }" />">
                                        删除
                    </a>||
                    <c:if test="${fn:length(url.tags) > 0}">
                        <c:forEach items="${url.tags}" var="tag">
                        <a href="<c:url value="/user/admin?tag=${tag}" />">
                        <c:out value="${tag}" /></a>&nbsp
                        </c:forEach>
                        </c:if>
                </c:forEach>
            </c:when>
            <c:when test="${fn:length(userList) > 0}">
            <c:out value="${nums}"/>users
            <c:forEach items="${userList}" var="user"><br />
                    <c:out value="${user.id}"/>||<c:out value="${user.username}"/>||
                    <c:out value="${user.email}"/>
            </c:forEach>
            </c:when>
            <c:otherwise>
                <i>空</i>
            </c:otherwise>
        </c:choose>
    </body>
</html>