<%--@elvariable id="urlList" type="java.util.List<cn.woshizhiwu.site.entity.Url>"--%>

<!DOCTYPE html>
<html>
    <head>
        <title>bookmark</title>
    </head>
    <body>
        <h2>列表：<c:out value="${title}" /><c:if test="${tag != null}"><c:out value="：${tag}" /></c:if></h2>
        [<a href="<c:url value="/user/addurl" />">添加链接</a>]<br />
        <br />
        <hr/>
        <a href="<c:url value="/user/home?category=0" />">所有<a/>&nbsp
        <a href="<c:url value="/user/home?category=1" />">收藏<a/>&nbsp
        <a href="<c:url value="/user/home?category=2" />">公开<a/>&nbsp
        <a href="<c:url value="/user/home?category=3" />">归档<a/>
        <hr/>
        <c:choose>
            <c:when test="${fn:length(urlList) > 0}">
                <c:forEach items="${urlList}" var="url">
                    <a href="<c:url value="/user/url/${url.id}" />">
                        <c:out value="${url.url}" /></a><br />
                    <c:out value="${url.title}"/>||
                    
                    <a href="<c:url value="/user/urlsetstatus?urlId=${ url.id }&category=3" />">
                    <c:if test="${url.archive}">已归档</c:if>
                    <c:if test="${!url.archive}">未归档</c:if>
                    </a>||
                    
                    
                    <a href="<c:url value="/user/urlsetstatus?urlId=${ url.id }&category=2" />">
                    <c:if test="${url.ventilate}">已公开</c:if>
                    <c:if test="${!url.ventilate}">未公开</c:if>
                    </a>||
                    
                    
                    <a href="<c:url value="/user/urlsetstatus?urlId=${ url.id }&category=1" />">
                    <c:if test="${url.favorite}">已收藏</c:if>
                    <c:if test="${!url.favorite}">未收藏</c:if>
                    </a>||
                    
                    <a href="<c:url value="/user/urldelete?urlId=${ url.id }" />">
                                        删除
                    </a>||
                    <c:if test="${fn:length(url.tags) > 0}">
                        <c:forEach items="${url.tags}" var="tag">
                        <a href="<c:url value="/user/home?tag=${tag}" />">
                        <c:out value="${tag}" /></a>&nbsp
                        </c:forEach>
                        </c:if>
                    <br/>
                    <br/>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <i>空</i>
            </c:otherwise>
        </c:choose>
    </body>
</html>
