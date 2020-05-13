<%@ page import="com.javaops.model.ContactType"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Resumes</title>
</head>
<body>
.<br>
<jsp:include page="fragments/header.jsp"/>
<br>
<section>
    <table border="2" cellpadding="8" cellspacing="0">
        <tr>
            <th>Name</th>
            <th>UUID</th>
            <th>Email</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.javaops.model.Resume"/>
            <tr>
                <td><a href="resume?uuid=${resume.uuid}"> ${resume.fullName} </a></td>
                <td>${resume.uuid}</td>
                <td>${resume.getContact(ContactType.EMAIL)}</td>
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
<jsp:include page="fragments/footer.jsp"/>
<br>
</body>
</html>
