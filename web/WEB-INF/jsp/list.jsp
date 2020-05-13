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
<section>
    <table border="2" cellpadding="8" cellspacing="0">
        <tr>
            <th>Name</th>
            <th>UUID</th>
            <th>Email</th>
            <th>Email</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <tr>
                <td><a href="resume?uuid=${resume.getUuid()}"> ${resume.getFullName()} </a></td>
                <td>${resume.getUuid()}</td>
                <td></td>
                <td></td>
                    <%--                <td>${resume.getContact(ContactType.EMAIL)}</td>--%>
            </tr>
        </c:forEach>


        <%--        <%--%>
        <%--            for (Resume resume : (List<Resume>) request.getAttribute("resumes")) {--%>
        <%--        %>--%>
        <%--        <tr>--%>
        <%--            <td><a href="resume?uuid=<%=resume.getUuid()%>"> <%=resume.getFullName()%></a></td>--%>
        <%--            <td><%=resume.getContact(ContactType.EMAIL)%></td>--%>
        <%--            &lt;%&ndash;                <td>${resume.getContact(ContactType.EMAIL)}</td>&ndash;%&gt;--%>
        <%--        </tr>--%>
        <%--        <%--%>
        <%--            }--%>
        <%--        %>--%>
    </table>
</section>
<br>
<br>
</body>
</html>
