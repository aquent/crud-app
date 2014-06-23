<%-- 
    Document   : list
    Created on : Apr 22, 2011, 2:25:22 PM
    Author     : FMilens
--%>

<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <c:set var="title" value="Person Listing"/>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <body>
      <div class="container-fluid">
        <h1>${title}</h1>     
        <p><a href="${context}/person/create"><img src="${context}/resources/images/add-icon.png" alt="create"></a></p>
        <c:choose>
            <c:when test="${fn:length(persons) gt 0}">
              <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email Address</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${persons}" var="person">
                            <tr>
                                <td>${person.firstName}</td>
                                <td>${person.lastName}</td>
                                <td>${person.emailAddress}</td>
                                <td>
                                    <a href="${context}/person/edit/${person.personId}"><img src="${context}/resources/images/edit-icon.png" alt="edit"></a>
                                    <a href="${context}/person/delete/${person.personId}"><img src="${context}/resources/images/delete-icon.png" alt="delete"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
              </div>
            </c:when>
            <c:otherwise>
                <p>No results found.</p>
            </c:otherwise>
        </c:choose>
      </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
