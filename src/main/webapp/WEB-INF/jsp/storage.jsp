<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>UpLoad file to Cloud Object Yandex</title>
</head>
<body>
<div>
    <h2>Object Storage Yandex <br> Только для залогинившихся пользователей.</h2>
    <a href="/">Главная</a>
</div>

<div>
    <h2>File Upload:</h2>
    <h3>Select a file to upload:</h3> <br/>
    <form action="${contextPath}/uploadFile" method = "POST" enctype = "multipart/form-data"/>
        <input type = "file" name = "file"/>
        <br/>
        <input type = "submit" value = "Submit"/>
</div>
</body>
</html>