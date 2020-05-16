<%@ page import="com.javaops.model.TextSection" %>
<%@ page import="com.javaops.model.ListSection" %>
<%@ page import="com.javaops.model.OrganizationSection" %>
<%@ page import="com.javaops.util.DateUtil" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.javaops.model.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <p>
    <table>
        <tbody>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.javaops.model.SectionType, com.javaops.model.Section>"/>
            <c:set var="type" value="<%=sectionEntry.getKey().toString()%>"/>
            <c:choose>
                <c:when test="${type == 'PERSONAL' || type == 'OBJECTIVE'}">
                    <tr>
                        <td>
                            <h3><%=sectionEntry.getKey().getTitle()%>
                            </h3>
                        </td>
                        <td>
                            <%=((TextSection) sectionEntry.getValue()).getContent()%>
                            <hr>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                    <tr>
                        <td>
                            <h3><%=sectionEntry.getKey().getTitle()%>
                            </h3>
                        </td>
                        <td>
                            <ul>
                                <c:forEach var="item" items="<%=((ListSection) sectionEntry.getValue()).getItems()%>">
                                    <li>
                                            ${item}
                                    </li>
                                </c:forEach>
                            </ul>
                            <hr>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                    <tr>
                        <td>
                            <h3><%=sectionEntry.getKey().getTitle()%>
                            </h3>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <c:forEach var="organisation"
                               items="<%=((OrganizationSection) sectionEntry.getValue()).getOrganizations()%>">
                        <jsp:useBean id="organisation"
                                     type="com.javaops.model.Organization"/>
                        <tr>
                            <td>
                                <h4>${organisation.homePage.toHtml()}</h4>
                            </td>
                            <td>
                            </td>
                        </tr>
                        <c:forEach var="position" items="<%=organisation.getPositions()%>">
                            <jsp:useBean id="position"
                                         type="com.javaops.model.Organization.Position"/>
                            <tr>
                            <td>
                                <%=DateUtil.toStringPeriod(position.getStartDate(), position.getEndDate())%>
                            </td>
                            <td>
                                    ${position.positionName}<br>
                                    ${position.responsibility}<br>
                            </td>
                        </c:forEach>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </tbody>
    </table>
    </p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
