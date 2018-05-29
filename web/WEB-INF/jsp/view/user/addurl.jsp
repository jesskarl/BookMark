
<!DOCTYPE html>
<html>
    <head>
        <title>Add A URL</title>
    </head>
    <body>
        <h2>Add A URL</h2>
        <h5><c:if test="${info != null}"><c:out value="${info}" /></c:if></h5>
        [<a href="<c:url value="/user/home" />">返回 to Home</a>]<br />
        <br />
        <form:form method="post" modelAttribute="urlForm">
            <form:label path="url">URL:</form:label><br />
            <form:input path="url" type="string" /><br />
            <br />
            <form:label path="title">Title:</form:label><br />
            <form:input path="title" type="string" /><br />
            <br />
            <form:label path="tags">Tags:</form:label><br />
            <form:input path="tags" type="string" /><br />
            <br />
            <input type="submit" value="提交" />
        </form:form>
    </body>
</html>
