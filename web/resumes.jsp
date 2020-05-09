<%--
  Created by IntelliJ IDEA.
  User: plvas
  Date: 07.05.2020
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Resumes</title>
</head>
<body>
    <header>Resumes</header>
    <br>
    <br>
    <table border="2">
        <tr >
            <th>UUID</th>
            <th>FullName</th>
        </tr>
        <c:forEach items="${resumes}" var ="resume">
            <tr>
                <td>${resume.getUuid()}</td>
                <td>${resume.getFullName()}</td>
            </tr>
        </c:forEach>
<%--        <tr>--%>
<%--            <td>${uuid}</td>--%>
<%--            <td>${fullName}</td>--%>
<%--        </tr>--%>
    </table>
    <br>
    <br>
</body>
</html>
