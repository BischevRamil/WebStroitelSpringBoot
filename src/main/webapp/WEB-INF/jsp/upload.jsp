<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>UpLoad file to Cloud Object Yandex</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
    <h2>Object Storage Yandex <br> Только для залогинившихся пользователей.</h2>
    <a href="/">Главная</a>
</div>

<div>
    <table>
        <thead>
        <th>File=Shared link</th>
        </thead>
    </table>
    <c:forEach items="${items}" var="item">
        <tr>
            <td><c:out value="${item}" /></td>
            <br/>
            <br/>
        </tr>

    </c:forEach>

</div>
</body>
</html>