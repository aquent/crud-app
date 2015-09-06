<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Company Listing</title>
    </head>
    <body>
        <h1>Company Listing</h1>     
        <p><a href="${pageContext.request.contextPath}/company/create">Create New Company</a></p>
        <c:choose>
            <c:when test="${fn:length(companies) gt 0}">
                <table>
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email Address</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${companies}" var="company">
                            <tr>
                                <td>${company.name}</td>
                                <td>${company.website}</td>
                                <td>${company.phoneNumber}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/company/view/${company.companyId}">View Company</a>
                                    <a href="${pageContext.request.contextPath}/company/edit/${company.companyId}">Edit Company</a>
                                    <a href="${pageContext.request.contextPath}/company/delete/${company.companyId}">Delete Company</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No results found.</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
