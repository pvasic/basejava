<%@ page import="com.javaops.model.ContactType" %>
<%@ page import="com.javaops.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.javaops.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=50 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <hr>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:choose>
                <c:when test="${type == 'PERSONAL' || type == 'OBJECTIVE'}">
                    <dl>
                        <h3>${type.title}</h3>
                        <dd><input type="text" name="${type.name()}" size=80
                                   value="${resume.getSection(type).getContent()}"></dd>
                    </dl>
                </c:when>
                <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                    <dl>
                        <h3>${type.title}</h3>
                        <dd>
                            <c:forEach var="item" items="${resume.getSection(type).getItems()}">
                                <input type="text" name="${type.name()}" size=80 value="${item}"><br>
                            </c:forEach>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                    <h3>${type.title}</h3>
                    <c:forEach var="organization" items="${resume.getSection(type).getOrganizations()}">
                        <dl>
                            <c:set var="homePage" value="${organization.getHomePage()}"/>
                            <h4>Название компании</h4>
                            <dd><input type="text" name="${type.name()}" size=50
                                       value="${homePage.getName()}"></dd><br>
                            <dt>URL</dt>
                            <dd><input type="text" name="${type.name()}" size=50
                                       value="${homePage.getUrl()}"></dd>
                        </dl>
                        <c:forEach var="positions" items="${organization.getPositions()}">
                            <table>
                                <ul>
                                    <li>
                                        <dt>Дата старта</dt>
                                        <dd><input type="text"  name="${type.name()}" size=50 value="${positions.getStartDate()}"></dd><br>
                                        <dt>Дата окончания</dt>
                                        <dd><input type="text"  name="${type.name()}" size=50 value="${positions.getEndDate()}"></dd><br>
                                        <dt>Должность</dt>
                                        <dd><input type="text"  name="${type.name()}" size=50 value="${positions.getPositionName()}"></dd><br>
                                        <dt>Обязанности</dt>
                                        <dd><input type="text"  name="${type.name()}" size=50 value="${positions.getResponsibility()}"></dd><br>
                                    </li>
                                </ul>
                            </table>

                        </c:forEach>
                    </c:forEach>

                </c:when>
            </c:choose>
            <hr>
        </c:forEach>


        <%--        <input type="text" name="section" size=30 value="1"><br/>--%>
        <%--        <input type="text" name="section" size=30 value="2"><br/>--%>
        <%--        <input type="text" name="section" size=30 value="3"><br/>--%>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
